// ******************************************************************** Utils **
if (typeof String.prototype.startsWith != 'function') {
  String.prototype.startsWith = function (str){
    return this.slice(0, str.length) == str;
  };
}

// ********************************************************* BusConnectorHQSW **
function BusConnectorHQSW() {}

//! Queue name prefix.
BusConnectorHQSW.QUEUE = 'jms.queue.';

//! Management Queue name.
BusConnectorHQSW.MANAGEMENT_QUEUE = BusConnectorHQSW.QUEUE
    + 'hornetq.management';

//! Max attempt for subscription.
BusConnectorHQSW.SUBSCRIPTION_MAX_ATTEMPT = 10;

//! Laps time between subscription attempt (in ms).
BusConnectorHQSW.SUBSCRIPTION_TIME = 100;

//! Acknoledgment subscription message.
//! FIXME: have a better ack message. This message is forbiden to emit on
//! network, riqque of trouble during receive message process.
BusConnectorHQSW.SUBSCRIPTION_ACK = "(-- ack of subscription --)";

/*!
 * \class       BusConnectorHQSW
 * \implements  BusConnector
 * Connector on HornetQ Bus, with Stomp over Websocket and Management Queue.
 *
 * Connector that connect on Bus HornetQ. The bus HornetQ would have an
 * acceptor defines as Stomp over WebSocket, and a Management Queue defined as
 * jms.queue.hornetq.management accessible from login:passcode user.
 *
 * \param url         Url to HornetQ Stomp Over WebSocket Acceptor.
 * \param name        The connector name.
 * \param onConnect   callBack function call after connect to Acceptor succeed.
 * \param onReceive   callback function call after receive message.
 * \param debug       debug function to print debug message.
 * \param login       The user login (may could access to
 *                    BusConnectorHQSW.MANAGEMENT_QUEUE, default is guest).
 * \param passcode    The user passcode (may could access to
 *                    BusConnectorHQSW.MANAGEMENT_QUEUE, default is guest).
 */
function BusConnectorHQSW(url, name, onConnect, onReceive, debug, login,
    passcode) {
  var me = this;
  var onReceive = onReceive || false;
  var onConnect = onConnect || false;
  var debug = debug || false;
  var login = login || 'guest';
  var passcode = passcode || 'guest';
  var subscribeAttempt = 0;
  var subscribeWaitTime = BusConnectorHQSW.SUBSCRIPTION_TIME +
    (BusConnectorHQSW.SUBSCRIPTION_TIME / 5.);
  var subscribeTimer = false;
  var subscribeError = new RegExp('message: Address '
      + BusConnectorHQSW.QUEUE + name + ' has not been deployed');

  //! Stomp over WebSocket connector on HornetQ.
  this.stomp = Stomp.client(url);

  //! Set debug mode.
  this.stomp.debug = function(debugMsg) {
    if (debugMsg.startsWith("<<< ERROR")) {
      if (subscribeError.test(debugMsg)) {
        // ERROR on subscribtion to dedicated queue.
        // Try to reconnect after BusConnectorHQSW.SUBSCRIPTION_TIME;
        if (subscribeAttempt >= BusConnectorHQSW.SUBSCRIPTION_MAX_ATTEMPT) {
          if (debug) { debug(debugMsg); }
        } else {
          clearTimeout(subscribeTimer);
          subscribeTimer = setTimeout(function() { me.subscribe() },
              BusConnectorHQSW.SUBSCRIPTION_TIME);
        }
      } else {
        if (debug) { debug(debugMsg); }
      }
    } else {
      // Forward error to user
      if (debug) { debug(debugMsg); }
    }
  }

  //! Test if connector is disconnected (or not).
  this.disconnected = false;

  /*!
   * Returns the connector name would be unique on Bus.
   *
   * \return The connector name.
   */
  this.getName = function() {
    return name;
  }

  this.subscribe = function() {
    subscribeAttempt ++;

    me.stomp.subscribe(BusConnectorHQSW.QUEUE + name, function(frame) {
      if (frame.body == BusConnectorHQSW.SUBSCRIPTION_ACK) {
        onConnect();
      } else {
        if (onReceive) { onReceive(frame.body); }
      }
    });

    // After subscription, send ack subscription to launch onConnect
    // This command will be executed exclusively if subscription works well.
    setTimeout(function() {me.send(BusConnectorHQSW.SUBSCRIPTION_ACK, name);},
        this.subscribeWaitTime);
  }

  /* ## MAIN ## */

  //! Connect on Bus.
  this.stomp.connect(login, passcode, function(frame) {
    // Once connected, ask to create new Queue.
    var createQueueHeader = {
      'reply-to'          : BusConnectorHQSW.QUEUE + 'debug',
      '_HQ_ResourceName'  : 'core.server',
      '_HQ_OperationName' : 'deployQueue'
    };

    var queueName = BusConnectorHQSW.QUEUE + name;
    var createQueueText = '["' + queueName + '", "' + queueName + '", ""]';

    me.stomp.send(BusConnectorHQSW.MANAGEMENT_QUEUE, createQueueHeader,
      createQueueText);

    // Subscribe to new Queue.
    me.subscribe();
  });
}

/*!
 * Sends message using Bus to a specific recipient.
 *
 * \param message
 * \param recipientName
 */
BusConnectorHQSW.prototype.send = function(message, recipientName) {
  this.stomp.send(BusConnectorHQSW.QUEUE + recipientName, {foo:1}, message);
}

//! Disconnect the connector from Bus.
BusConnectorHQSW.prototype.disconnect = function() {
  var disconnected = this.disconnected;

  this.stomp.disconnect(function() {
    disconnected = true;
  });
}

/*!
 * Tests if connector is disconnected from bus.
 *
 * \return  \c true if connector is disconnected, \c false otherwise.
 */
BusConnectorHQSW.prototype.isDisconected = function() {
  return this.disconnected;
}

