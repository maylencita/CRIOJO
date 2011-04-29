/*
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 21/03/11
 * Time: 10:46
 */
package fr.emn.criojo.bpmn;

import fr.emn.criojo.parser.tree.{CHRToken,^}
import xml.Node
import xml.XML._
import collection.mutable.HashMap
import org.antlr.runtime.tree.DOTTreeGenerator
import java.io._

class BPMNParser(bpmnDoc: Node){
  import Tokens._
  val bpmnProcess = bpmnDoc \\ "process"
  val operations = HashMap[String, Node]()
  val messages = HashMap[String, Node]()
  var dataObjects = List[^]()
//  val bpmnDoc = loadFile(this.getClass.getClassLoader.getResource("fr/emn/criojo/bpmn/BookProcess_DF.xml").getFile)

//  println("Process: " + bpmnProcess)
//  println("children: " + bpmnProcess.child)

  var nodeMap = HashMap[String,Node]()
  var actList:List[^] = List()

  def definitions: ^ = {
    for(m <- bpmnDoc \\ "message"){
      messages.put(m \\ "@id" text, m)
    }
    for(o <- bpmnDoc \\ "interface" flatMap(i => i \\ "operation")){
      operations.put(o \\ "@id" text, o)
    }
    ^(BPMN, bpmnProcess.map(process(_)).toList)
  }

  def process(elem:Node): ^ = {
//    for(node <- bpmnProcess.child){
//      node attribute("id") match{
//        case Some(seq) => nodeMap.put(seq.head.text, node)
//        case _ =>
//      }
//    }
    ^(PROCESS, elem.child.foldLeft(List[^]()){(l,c) =>
      parseElem(c) match{case null => l; case tree => l :+ tree}
      } ++ dataObjects)
  }

  private def parseElem(doc: Node): ^ = doc match{
    case child @ <startEvent>{_*}</startEvent> => start(child)
    case child @ <endEvent>{_*}</endEvent> => end(child)
    case child @ <boundaryEvent>{_*}</boundaryEvent> => error(child)
    case child @ <serviceTask>{_*}</serviceTask> => service(child)
    case child @ <receiveTask>{_*}</receiveTask> => receive(child)
    case child @ <sendTask>{_*}</sendTask> => send(child)
    case child @ <exclusiveGateway>{_*}</exclusiveGateway> => exclusive(child)
    case child @ <parallelGateway>{_*}</parallelGateway> => parallel(child)
    case child @ <ioSpecification>{_*}</ioSpecification> => ioSpec(child)
    case child @ <dataObject>{_*}</dataObject> => dataObject(child \ "@id" text, child \ "@itemSubjectRef" text)
    case _ => null
  }

  def ioSpec(elem: Node): ^ = {
    elem \\ "dataInput" match{
      case List() =>
      case lst => dataObjects ++= lst.map(d => dataObject(d \"@id" text, d \"@itemSubjectRef" text))
    }
    elem \\ "dataOutput" match{
      case List() =>
      case lst => dataObjects ++= lst.map(d => dataObject(d \"@id" text, d \"@itemSubjectRef" text))
    }
    null
  }

  def start(elem: Node): ^ = {
    elem \\ "ioSpecification" match{case Seq() => ; case sq => ioSpec(sq.head)}
    ^(START, List(id(elem \ "@id" head), ioData(true,elem), ioData(false,elem), next(elem)).filter(
     n => n.token != EOF
    ))
//    ^(START,
//      ^(NEXT, elem \ "outgoing" map(o => findNextId(o.text)) map (id => parseOption(nodeMap.get(id))) toList
//      ))
  }

  def ioData(in:Boolean, elem:Node): ^ = {
    val tag = if (in) "dataInputAssociation" else "dataOutputAssociation"
    val tok = if (in) DATA_INPUT else DATA_OUTPUT
    elem \\ tag match{
      case lst if(! lst.isEmpty) =>
        val da = lst.head
        ^(tok,
        ^(SOURCE_DO, id(da \ "sourceRef" text)) :: ^(TARGET_DO, id(da \ "targetRef" text)) :: assignment(da))
      case _ => ^(EOF)
    }
  }

  def assignment(elem:Node): List[^] = {
    for{as <- elem \ "assignment"} yield
      ^(ASSIGNMENT, ^(FROM, id(as \\ "from" text)), ^(TO, id(as \\ "to" text)))
  }.toList

  def parseOption(op:Option[Node]): ^ = op match{
    case Some(node) => parseElem(node)
    case _ => null
  }

  def service(elem: Node): ^ = {
    elem \\ "ioSpecification" match{case Seq() => ; case sq => ioSpec(sq.head)}
    ^(SERVICE, List(
      id(elem \ "@id" head), operation(elem \ "@operationRef" text), ioData(true,elem), ioData(false,elem),  next(elem)).filter(
      _.token != EOF
      ))
  }

  def operation(opId: String): ^ = {
    operations.get(opId) match{
      case Some(op) => ^(OPERATION, id(op \ "@id" head))
      case _ => ^(EOF)
    }
  }

  def exclusive(elem: Node): ^ = {
    ^(X, id(elem \ "@id" head) :: gates(elem))
  }

  def parallel(elem: Node): ^ = {
    ^(PARALLEL, id(elem \ "@id" head), prev(elem), next(elem))
  }

  def error(elem:Node): ^ = {
    ^(ERROR, id(elem \ "errorEventDefinition" \ "@errorRef" head),
      ^(SOURCE, id(elem \ "@attachedToRef" head)),
      next(elem))
  }

  def end(elem:Node): ^ = {
    ^(END, id(elem \ "@id" head))
  }

  def receive(elem:Node): ^ = {
    ^(RECEIVE, id(elem \ "@id" head), next(elem))
  }

  def send(elem:Node): ^ = {
    ^(SEND, id(elem \ "@id" head))
  }

  def dataObject(doId:String, itemRef:String): ^ = {
    val sufix = (itemRef split ':') match{case Array(pr,sf) => sf; case _ => itemRef}
    bpmnDoc \\ "itemDefinition" find(_.\("@id").text == sufix) match{
      case Some(itdf) => ^(DATAOBJECT, ^(NAME, id(doId)),
        ^(COMPLEX_TYPE, getSchema(itdf \\ "@structureRef" text)))
      case _ => ^(DATAOBJECT, ^(NAME, id(doId)))
    }
  }

  def getSchema(ref:String): List[^] = {
    def elemList(elem:Node): List[^] = {
      elem.child.foldLeft(List[^]()){
        case (lst, e @ <element>{_*}</element>) =>
          lst :+ ^(ELEM, ^(NAME, id(e \ "@name" text)), ^(TYPE, id(e \ "@type" text)))
        case (lst, _) => lst
      }.toList
    }

    val prefix = (ref splitAt ref.indexOf(":"))._1
    val sufix = (ref splitAt ref.indexOf(":")+1)._2
    val namespace = bpmnDoc.getNamespace(prefix)
    if (namespace == "http://www.w3.org/2001/XMLSchema-instance"){
      List[^]()
    }else{
      val schemaLoc = bpmnDoc \\ "import" find(_.\("@namespace").text == namespace) match{
        case Some(imp) => imp \\ "@location" text
        case _ => ""
      }
      if(! schemaLoc.isEmpty){
        val schemaDoc = loadFile(schemaLoc)
        (schemaDoc \\ "element").find(_.\("@name").text ==sufix) match{
          case Some(el) =>
            el.child.flatMap{
              case ct @ <complexType>{_*}</complexType> => elemList(ct)
              case _ => List[^]()
            }.toList
          case _ =>  List[^]()
        }
      }else{
         List[^]()
      }
    }
  }

  def gates(elem:Node): List[^] = (elem \ "outgoing").foldLeft(List[^]()){
    (l,o) => condition(o) match{
      case null => l
      case tree => l :+ ^(OUT, tree, ^(NEXT, id(findNextId(o.text))))
    }
  }

  def condition(seqFlow:Node): ^ = {
    bpmnProcess \ "sequenceFlow" find(_.\("@id").text == seqFlow.text ) match{
      case Some(sf) => ^(CONDITION, expr((sf \ "conditionExpression").head.text))
      case _ => null
    }
  }

  def id(elem: Node): ^ = {
    ^(new CHRToken(ID.getType,elem.text), Nil )
  }

  def id(value: String): ^ = {
    ^(new CHRToken(ID.getType,value), Nil )
  }

  def expr(value:String): ^ = {
    ^(new CHRToken(EXPR.getType,value), Nil)
  }

  def prev(elem: Node): ^ = {
    ^(PREVIOUS,
      elem \ "incoming" map(o => id(findPrevId(o.text))) toList)
  }

  def next(elem: Node): ^ = {
    ^(NEXT,
      elem \ "outgoing" map(o => id(findNextId(o.text))) toList)
  }

  private def findNextId(outflow_id: String): String =
    bpmnProcess \ "sequenceFlow" find(_.\("@id").text == outflow_id) match{
      case Some(sf) => sf \ "@targetRef" text
      case _ => ""
    }

  private def findPrevId(inflow_id: String): String =
    bpmnProcess \ "sequenceFlow" find(_.\("@id").text == inflow_id) match{
      case Some(sf) => sf \ "@sourceRef" text
      case _ => ""
    }

//  private def findNext(out_id: String): Node = {
//    val nextId = findNextId(out_id)
//    bpmnProcess child find(_.\("@id").text == nextId) match{
//      case Some(node) => node
//      case _ => null
//    }
//  }

}

import org.antlr.runtime.{Token,CommonToken}
object Tokens{
  var tokIndex = 0
  val EOF:Token = ""
  val ID:Token = "ID"
  val BPMN:Token = "BPMN"
  val PROCESS:Token = "PROCESS"
  val SERVICE:Token = "SERVICE"
  val TASK:Token = "TASK"
  val START:Token = "START"
  val END:Token = "END"
  val X:Token = "X"
  val OUT:Token = "OUT"
  val PARALLEL:Token = "PARALLEL"
  val NEXT:Token = "NEXT"
  val PREVIOUS:Token = "PREVIOUS"
  val ERROR:Token = "ERROR"
  val SOURCE:Token = "SOURCE"
  val CONDITION:Token = "CONDITION"
  val EXPR:Token = "EXPR"
  val RECEIVE:Token = "RECEIVE"
  val SEND:Token = "SEND"
  val OPERATION:Token = "OPERATION"
  val INPUT:Token = "INPUT"
  val OUTPUT:Token = "OUTPUT"
  val ELEM:Token = "ELEM"
  val NAME:Token = "NAME"
  val TYPE:Token = "TYPE"
  val COMPLEX_TYPE:Token = "COMPLEX_TYPE"
  val DATAOBJECT:Token = "DATAOBJECT"
  val DATA_INPUT:Token = "DATA_INPUT"
  val DATA_OUTPUT:Token = "DATA_OUTPUT"
  val SOURCE_DO:Token = "SOURCE_DO"
  val TARGET_DO:Token = "TARGET_DO"
  val ASSIGNMENT:Token = "ASSIGNMENT"
  val FROM:Token = "FROM"
  val TO:Token = "TO"

  private[this] def getToken(tokName: String): Token ={
    tokIndex += 1
		new CommonToken((tokIndex), tokName)
	}

  implicit def string2Token(tokName: String): Token = getToken(tokName)
}

object Parsing extends Application{
  val bpmnDoc = loadFile(this.getClass.getClassLoader.getResource("fr/emn/criojo/bpmn/BookProcess_DF.xml").getFile)

  val parser = new BPMNParser(bpmnDoc)
  val bpmnTree = parser.definitions
  println(bpmnTree toStringTree)
  println("dataObjects = " + parser.dataObjects)

    // GENERATE DOT AST
    val gen = new DOTTreeGenerator()
    val st = gen.toDOT(bpmnTree);
    val bout = new BufferedWriter(new FileWriter("BpmnProcess.dot"));
    bout.write(st.toString());
    bout.close();

    val rt = Runtime.getRuntime();
    val pr = rt
        .exec("/Applications/Graphviz.app/Contents/MacOS/Graphviz BpmnProcess.dot");

}