/*
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 24/03/11
 * Time: 14:28
 */
package fr.emn.criojo.bpmn;

import fr.emn.criojo.parser.tree.{CHRToken,CHRTreeTokens,^}
import xml.Node
import xml.XML._
import collection.mutable.{Map,ListMap}
import org.antlr.runtime.tree.DOTTreeGenerator
import java.io._

class Bpmn2Criojo(bpmnProcess: Node) {
  val tokenNames:Array[String] = Array[String] (
      "<invalid>", "<EOR>", "<DOWN>", "<UP>", "SCRIPT", "ATOM", "VARS", "RULE", "HEAD", "BODY", "MULTI", "DECLARATION", "EMPTYLIST", "PUBLIC", "PRIVATE", "REQUIRED", "PROCESS", "GUARD", "EMPTY", "INT_ATOM", "STR_ATOM", "NULL_ATOM", "LPAREN", "SEMI", "RPAREN", "COLON", "COMMA", "UNDEF", "R_ID", "ARROBAS", "TILDE", "BAR", "BANG", "RARROW", "LBRACK", "RBRACK", "IMARK", "ABS", "EQ", "NOT", "NU", "TRUE", "FALSE", "NULL", "V_ID", "INT", "STRING", "LT", "LTEQ", "PLUS", "MINUS", "LCURL", "RCURL", "POINT", "SLASH", "COMMENT", "WS", "ESC_SEQ", "CHAR", "EXPONENT", "HEX_DIGIT", "UNICODE_ESC", "OCTAL_ESC", "'provided'", "'required'", "'local'", "'Null'", "'T'"
  )

  val Criojo = new CHRTreeTokens(tokenNames)
  val Bpmn = Tokens

  val parser = new BPMNParser(bpmnProcess)
  val bpmnTree = parser definitions

  val nodeMap:Map[String, ^] = ListMap() ++ bpmnTree.childList.map(c => (id(c), c))

  def getCriojoProcess: ^ = bpmnTree match{
    case ^(Bpmn.PROCESS, pelems) =>
      ^(Criojo.PROCESS,
        ^(Criojo.DECLARATION, Nil),
        ^(Criojo.SCRIPT, pelems.foldLeft(List[^]())((l,e) =>
          rule(e) match{case null => l; case rlst => l ++ rlst}
        )))
    case _ => ^(Bpmn.EOF, Nil)
  }

  def rule(tree: ^): List[^] ={
    val body = atomList(tree.getFirstChildWithType(Bpmn.NEXT.getType) match{
      case nTree if(nTree != null) =>
        val list = nTree.asInstanceOf[^].childList
        list
      case _ => List()
    })
    if (body.isEmpty && tree.token != Bpmn.X)
      null
    else tree match{
      case ^(Bpmn.RECEIVE, id :: _) =>
        ^(Criojo.RULE, ^(Criojo.HEAD, atom(id.getText, List())), ^(Criojo.BODY, body)) :: Nil
      case ^(Bpmn.SERVICE, id :: _) =>
        ^(Criojo.RULE, ^(Criojo.HEAD, atom(id.getText, List())), ^(Criojo.BODY, body)) :: Nil
      case ^(Bpmn.PARALLEL, id :: ^(Bpmn.PREVIOUS, plst) :: _) =>
        ^(Criojo.RULE, ^(Criojo.HEAD, plst.map(p=>atom(id.getText,List()))), ^(Criojo.BODY, body)) :: Nil
      case ^(Bpmn.X,  id :: gates) =>
        gates.map(gatedRule(id.getText,_))
      case _ => null
    }
  }

  def gatedRule(id: String, gate: ^): ^ = gate match{
    case ^(Bpmn.OUT, ^(Bpmn.CONDITION, expr::_) :: next :: _) =>
      ^(Criojo.RULE, ^(Criojo.HEAD, atom(id, List())), ^(Criojo.BODY, atomList(next.childList)), ^(Criojo.GUARD, expr))
    case _ => null
  }

  def atom(relName:String, vlst:List[String]): ^ = ^(Criojo.ATOM, relation(relName), varList(vlst))

  def atom(tree: ^): ^ = tree match{
    case ^(Bpmn.SERVICE, id :: ^(Bpmn.NEXT, nlst) :: _) =>
      ^(Criojo.ATOM, relation(id.getText), ^(Criojo.VARS, Nil))
    case ^(Bpmn.RECEIVE, id :: ^(Bpmn.NEXT, nlst) :: _) =>
      ^(Criojo.ATOM, relation(id.getText), ^(Criojo.VARS, Nil))
    case ^(Bpmn.SEND, id :: _) =>
      ^(Criojo.ATOM, relation(id.getText), ^(Criojo.VARS, Nil))
    case ^(Bpmn.X, id :: _) =>
      ^(Criojo.ATOM, relation(id.getText), ^(Criojo.VARS, Nil))
    case ^(Bpmn.PARALLEL, id :: _) =>
      ^(Criojo.ATOM, relation(id.getText), ^(Criojo.VARS, Nil))
    case _ => null
  }

  def varList(vlst:List[String]): ^ = ^(Criojo.VARS, vlst.map(variable(_)))

  def variable(name:String): ^ = ^(new CHRToken(Criojo.V_ID.getType,name), Nil )

  def relation(name:String): ^ = ^(new CHRToken(Criojo.R_ID.getType,name), Nil )

  def id(tree: ^): String = tree match{
    case ^(tok, id::_) => id.getText
    case _ => ""
  }

//  def body(idlst:List[^]): List[^] = idlst.foldLeft(List[^]()){(l,id) =>
//    nodeMap get(id.getText) match{
//      case Some(node) =>
//        val a = bodyPart(node)
//        if(a == null) l else l :+ atom(node)
//      case _ => l
//    }
//  }

  def atomList(idlst:List[^]): List[^] = idlst.foldLeft(List[^]()){(l,id) =>
    nodeMap get(id.getText) match{
      case Some(node) =>
        val a = atom(node)
        if(a == null) l else l :+ atom(node)
      case _ => l
    }
  }
}

object TestinTransformation extends Application{
  val tokenNames:Array[String] = Array[String] (
      "<invalid>", "<EOR>", "<DOWN>", "<UP>", "SCRIPT", "ATOM", "VARS", "RULE", "HEAD", "BODY", "MULTI", "DECLARATION", "EMPTYLIST", "PUBLIC", "PRIVATE", "REQUIRED", "PROCESS", "GUARD", "EMPTY", "INT_ATOM", "STR_ATOM", "NULL_ATOM", "LPAREN", "SEMI", "RPAREN", "COLON", "COMMA", "UNDEF", "R_ID", "ARROBAS", "TILDE", "BAR", "BANG", "RARROW", "LBRACK", "RBRACK", "IMARK", "ABS", "EQ", "NOT", "NU", "TRUE", "FALSE", "NULL", "V_ID", "INT", "STRING", "LT", "LTEQ", "PLUS", "MINUS", "LCURL", "RCURL", "POINT", "SLASH", "COMMENT", "WS", "ESC_SEQ", "CHAR", "EXPONENT", "HEX_DIGIT", "UNICODE_ESC", "OCTAL_ESC", "'provided'", "'required'", "'local'", "'Null'", "'T'"
  )

  val Criojo = new CHRTreeTokens(tokenNames)

  val bpmnDoc = loadFile(this.getClass.getClassLoader.getResource("fr/emn/criojo/bpmn/BookProcess_DF.xml").getFile)
  val toCriojo = new Bpmn2Criojo(bpmnDoc)
  val criojoProcess = toCriojo.getCriojoProcess

  override def main(args:Array[String]){
//    generateGraph(new BPMNParser(bpmnDoc) process)

    println("Map: " + toCriojo.nodeMap)
    println("Criojo Tree: " + criojoProcess.toStringTree)

//    generateGraph(criojoProcess)
    println("Criojo script: \n" + printScript(criojoProcess))
  }

  def generateGraph(tree: ^){
    // GENERATE DOT AST
    val gen = new DOTTreeGenerator()
    val st = gen.toDOT(tree);
    val bout = new BufferedWriter(new FileWriter("CriojoProcess.dot"));
    bout.write(st.toString());
    bout.close();

    val rt = Runtime.getRuntime();
    val pr = rt
        .exec("/Applications/Graphviz.app/Contents/MacOS/Graphviz CriojoProcess.dot");
  }

  def printScript(tree: ^):String = tree match{
    case ^(Criojo.PROCESS, declr :: ^(Criojo.SCRIPT, rlst) :: _ ) => rlst.map(printNode(_)).mkString("","\n","")
    case _ => "not a script"
  }

  def printNode(tree: ^):String = tree match{
    case ^(Criojo.RULE, h :: b :: Nil) => printList(h) + "=>" + printList(b)
    case ^(Criojo.RULE, h :: b :: g :: Nil) => printList(h) + "=>" + "[" + printNode(g) + "]?" + printList(b)
    case ^(Criojo.GUARD, rlst) =>
      rlst.head.getText
      //rlst.foldLeft(""){(str,node) => str + printNode(node) + " "}
    case _ => ""
  }

  def printList(tree: ^):String = tree match{
    case ^(_, alst) => alst.map{
      case ^(Criojo.ATOM, rname :: ^(Criojo.VARS, vlst) :: _) => rname.getText
      case _ => ""}.mkString("",",","")
    case _ => ""
  }
}