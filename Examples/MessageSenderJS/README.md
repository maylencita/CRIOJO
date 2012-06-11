## MessageSenderJS

MessageSenderJS is simply app to test BusConnector over Stomp and WebSocket.

This app offer a web interface to connect on Bus and send message over the Bus.

Before launch app, it's highly recommended to understand MessageSender example.

## Compile

Ensure you already launch ```mvn install``` on CRIOJO main directory.

Next, ```mvn compile```.

## Run example

The example first start a Bus Node implemented with HornetQ. This bus node have
a specific queue name jms.queue.hornetq.management. As explain in HornetQ user
manual
(http://docs.jboss.org/hornetq/2.2.14.Final/user-manual/en/html/management.html)
the Management Queue allows user to modify node configuration (create new
resources, inspect these resources and interact with it) -- the page
http://203.157.7.46/jmx-console/HtmlAdaptor?action=inspectMBean&name=org.hornetq%3Amodule%3DCore%2Ctype%3DServer
references all management methods with parameters. With the Management Queue we
could use stomp connector and ask to create new queue (a dedicated queue to 
reach connector).

Once Bus node started, this example start a jetty server. The MessageSender web
interface is accessible from http://localhost:8080/message-sender/. With
MessageSender web interface it's possible to reach MessageSender java
implementation. MessageSenderJs use Stomp over WebSocket. The connector
implementation is specified in BusConnectorHQSW.js. This library required
stomp-websocket from jmesnil (https://github.com/jmesnil/stomp-websocket).

To launch example simply execute
```mvn exec:java -Dexec.mainClass=fr.emn.app.criojo.network.JettyLauncher```

