/**
 * Created with IntelliJ IDEA.
 * User: jonathan
 * Date: 6/19/12
 * Time: 11:12 AM
 * To change this template use File | Settings | File Templates.
 */

function Connector() {
    this.channelListenerMap = [];
    this.connReceiveHandler = null
    this.conn = null
    this.connectionEvent = null
    this.disconnectionEvent = null

    this.connect = function(url, name) {
        this.conn = new BusConnectorHQSW(url, name, this.connConnected,
            this.connReceiveHandler, this.connDebug);
    }

    this.connConnected = function() {
        if(this.connectionEvent) {
            this.connectionEvent()
        }
        else
        if(connector.connectionEvent) {
            connector.connectionEvent()
        }
    }

    this.connReceiveHandler = function (message) {
        var messageAsObject = JSON.parse(message);
        var channelId = messageAsObject.channel.split(".").slice(-1)[0];

        if(this.channelListenerMap && this.channelListenerMap[channelId]) {
            this.channelListenerMap[channelId](messageAsObject);
        }
        else
        if(connector.channelListenerMap && connector.channelListenerMap[channelId]) {
            connector.channelListenerMap[channelId](messageAsObject);
        }
    }
};

Connector.prototype.init = function() {
    if (!("WebSocket" in window)) {
        console.error("Websockets NOT supported");
        return;
    }

    this.channelListenerMap = new Object();
},

Connector.prototype.send = function(to, from, channel, args) {
    var message = JSON.stringify({to:to, from:from, channel:channel, args:args})
    this.conn.send(message, to);
},

Connector.prototype.connDebug = function(msg) {
    //console.log('[DEBUG] ' + msg);
},

Connector.prototype.addConnectorEvent = function(event, f) {
    if(event=="connect")
        this.connectionEvent = f
    if(event=="disconnect")
        this.disconnectionEvent = f
},

Connector.prototype.setChannelListener = function(channel, f) {

    if(this.channelListenerMap) {
        this.channelListenerMap[channel] = f
    }
    else
    if(connector.channelListenerMap)
    {
        connector.channelListenerMap[channel] = f
    }
}
