## MessageSenderJS

MessageSenderJS is simply app to test BusConnector over Stomp and WebSocket.

This app offer a web interface to connect on Bus and send message over the Bus.

Before launch app, it's highly recommended to understand MessageSender example.

## Compile

Ensure you already launch ```mvn install``` on CRIOJO main directory.

Next, ```mvn compile```.

## Run example

The example first start a Bus Node implemented with HornetQ. This bus node have
a specific queue name `jms.queue.hornetq.management`. As explain in HornetQ
[user manual](http://docs.jboss.org/hornetq/2.2.14.Final/user-manual/en/html/management.html)
the Management Queue allows user to modify node configuration (create new
resources, inspect these resources and interact with it). With the Management
Queue we could use stomp connector and ask to create new queue (a dedicated
queue to reach connector).

Once Bus node started, this example start a jetty server. The MessageSender web
interface is accessible from
[http://localhost:8080/message-sender/](http://localhost:8080/message-sender/).
With MessageSender web interface it's possible to reach MessageSender java
implementation. MessageSenderJs use Stomp over WebSocket. The connector
implementation is specified in BusConnectorHQSW.js. This library required
stomp-websocket from jmesnil (https://github.com/jmesnil/stomp-websocket).

To launch example simply execute
```mvn exec:java -Dexec.mainClass=fr.emn.app.criojo.network.AppLauncher```

## Know issue

Actually, HornetQ node use 2.2.18.Final version. According to issue
[HORNETQ-819](https://issues.jboss.org/browse/HORNETQ-819) from HornetQ issue
tracker, HornetQ does not support the latest WebSocket protocol. So example only
works on Safary 5.1 and other browser with old WebSocket implementation. This
issue will close with HornetQ 2.2.19.Final release.

