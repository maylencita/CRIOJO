# BusHornetQImpl

This is the project for the networking part of the Criojo language. It provides
an implementation of BusSpec with HornetQ.

## HornetQ
HornetQ is an open source asynchronous messaging project from JBoss. It is an
example of Message Oriented Middleware. HornetQ is an open source project to
build a multi-protocol, embeddable, very high performance, clustered,
asynchronous messaging system.

The HornetQ source code is available at:

https://github.com/hornetq/hornetq

## Implementation
This project provides two implementations of BusConnector. The first is
BusConnectorRemoteHornetQ and the second is BusConnectorLocalHornetQ.

### BusConnectorRemoteHornetQ
BusConnectorRemoteHornetQ connect to an existing server. The server would be a
HornetQ server with acceptors using Netty implementation. With this connector,
the server configuration is in charge of administrator.

### BusConnectorLocalHornetQ
This connector comes with an implementation of HornetQ server. If no server
start on BusConnectorLocalHornetQ.getHost with given port, the connector start
a new HornetQ server. To connect to bus, the HornetQ server configuration will
specifying acceptors using Netty implementation.

## Source
The project's source code is hosted at:

https://github.com/maylencita/CRIOJO

## Installation

To install simply use Maven :

```% mvn install```

## Contact

Do not hesitate to contact the Criojo team with the issue tracker, or by
private mail (soon).

