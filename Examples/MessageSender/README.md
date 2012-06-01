# MessageSender
MessageSender is simply app use to test BusConnector.

This app offer an interface to connect on Bus with different implementation of
BusConnector and send message over the bus.

## Compile

Ensure you already launch ```mvn install``` on CRIOJO main directory.

Next, ```mvn compile```.

## LocalConnector

The LocalConnector try to connect on node on localhost. If no node defined, the
local connector use informations give by user to start a new node and connect
on it.

<pre><code>
% mvn exec:java -Dexec.mainClass=fr.emn.app.criojo.network.MessageSender

Choose kind of connector. Here LocalHornetQ is choose that start Bus Node
HornetQ and connect on it
% 0 - quit; 1 - LocalHornetQ; 2 - RemoteHornetQ: 1

Next, defined how to connect on bus, here I choose to connect on port 5445
and the connector name is foo. This will start a Bus Node implementing in
HornetQ with acceptor on 5445.
% * Defined connector of first MessageSender *
% Url to connect (empty to quit): 5445:foo

Finally, start a second connector. This connector no start a Bus Node because
Bus Node already start.
% * Defined connector of second MessageSender *
% Url to connect (empty to quit): 5445:bar

Now use the two windows to send message to each other.
</code></pre>

## RemoteConnector

The RemoteConnector try to connect on already existing node. For this example,
a HornetQ cluster is already deployed with two nodes. One HornetQ server run
on 10.0.0.1 and next on 10.0.0.2.

<pre><code>
% mvn exec:java -Dexec.mainClass=fr.emn.app.criojo.network.MessageSender

Choose kind of connector. Here RemoteHornetQ is choose to connect on existing
Bus Node.
% 0 - quit; 1 - LocalHornetQ; 2 - RemoteHornetQ: 2

Next, defined how to connect on bus. Here connect on 10.0.0.1 that have a
Netty Acceptor on 5445. For this example, the connector name is foo.
% * Defined connector of first MessageSender *
% Url to connect (empty to quit): 10.0.0.1:5445:foo

Finally, start a second connector. Here connect on 10.0.0.2 that have a 
Netty Acceptor on 5445. For this example, the connector name is bar.
% * Defined connector of second MessageSender *
% Url to connect (empty to quit): 10.0.0.2:5445:bar

Now use the two windows to send message to each other.
</code></pre>

