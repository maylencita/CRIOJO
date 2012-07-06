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
 * \param onConnect   callBack function call after connect to Acceptor succeed
 *                    and subscribe to correct queue.
 * \param onReceive   callback function call after receive message.
 * \param debug       debug function to print debug message.
 * \param login       The user login (may could access to
 *                    BusConnectorHQSW.MANAGEMENT_QUEUE, default is guest).
 * \param passcode    The user passcode (may could access to
 *                    BusConnectorHQSW.MANAGEMENT_QUEUE, default is guest).
 */
function BusConnectorHQSW(url, name, onConnect, onReceive, debug, login,
    passcode) {
  var _this = this;
  var onReceive = onReceive || false;
  var onConnect = onConnect || false;
  var debug = debug || false;
  var login = login || 'guest';
  var passcode = passcode || 'guest';

  //! Number of attemp subscription on QUEUE + name.
  var subscribeAttempt = 0;

  //! Time before new subscription on QUEUE + name.
  var subscribeWaitTime = BusConnectorHQSW.SUBSCRIPTION_TIME +
    (BusConnectorHQSW.SUBSCRIPTION_TIME / 5.);

  //! Timer will launch subscription on QUEUE + name (get with setTimeout).
  var subscribeTimer = false;

  //! Error message on subscription to QUEUE + name.
  var subscribeError = new RegExp('message: Address '
      + BusConnectorHQSW.QUEUE + name + ' has not been deployed');

  //! Stomp over WebSocket connector on HornetQ.
  this.stomp = new Stomp.client(url);

  //! Test if connector is disconnected (or not).
  this.disconnected = true;

  //! Successive Lost connection Number.
  this.lostConnection = 0;

  //! Set debug mode.
  this.stomp.debug = function(debugMsg) {
    if (debugMsg.startsWith('<<< ERROR')) {
      if (subscribeError.test(debugMsg)) {
        // ERROR on subscribtion to dedicated queue.
        // Try to reconnect after BusConnectorHQSW.SUBSCRIPTION_TIME;
        if (subscribeAttempt >= BusConnectorHQSW.SUBSCRIPTION_MAX_ATTEMPT) {
          if (debug) { debug(debugMsg); }
        } else {
          clearTimeout(subscribeTimer);
          subscribeTimer = setTimeout(function() { _this.subscribe() },
              BusConnectorHQSW.SUBSCRIPTION_TIME);
        }
      } else {
        if (debug) { debug(debugMsg); }
      }
    } else if (debugMsg.startsWith('Whoops! Lost connection')
        && _this.lostConnection < 1) {
      // Disconnected after time-out from server.
      // Re-connect to server acceptor.
      _this.lostConnection ++;
      setTimeout(function() {
        _this.stomp.connect(login, passcode, function() {
          _this.lostConnection = 0;
          _this.stomp.subscribe(BusConnectorHQSW.QUEUE + name, function(frame) {
            if (onReceive) { onReceive(frame.body); }
          });
        });
      }, 1);
    } else {
      // Forward error to user
      if (debug) { debug(debugMsg); }
    }
  }

  /*!
   * Returns the connector name would be unique on Bus.
   *
   * \return The connector name.
   */
  this.getName = function() {
    return name;
  }

  /*!
   * Subscribe to dedicated QUEUE + name queue.
   *
   * The connection is successfully when connector success to connect on 
   * dedicated queue. This method use specific message SUBSCRIPTION_ACK sends to
   * itself to ensure success subscription. If subscription is ok, the user
   * method onConnect is call.
   */
  this.subscribe = function() {
    subscribeAttempt ++;

    this.stomp.subscribe(BusConnectorHQSW.QUEUE + name, function(frame) {
      if (frame.body == BusConnectorHQSW.SUBSCRIPTION_ACK) {
        _this.disconnected = false;
        onConnect();
      } else {
        if (onReceive) { onReceive(frame.body); }
      }
    });

    // After subscription, send ack subscription to launch onConnect
    // This command will be executed exclusively if subscription works well.
    setTimeout(function() {
      _this.send(BusConnectorHQSW.SUBSCRIPTION_ACK, name);
    }, subscribeWaitTime);
  }

  // ## MAIN -- Connect on Bus ##
  this.stomp.connect(login, passcode, function(frame) {
    // Once connected, ask to create new Queue.
    var createQueueHeader = {
      'reply-to'          : BusConnectorHQSW.QUEUE + 'debug',
      '_HQ_ResourceName'  : 'core.server',
      '_HQ_OperationName' : 'deployQueue'
    };

    var queueName = BusConnectorHQSW.QUEUE + name;
    var createQueueText = '["' + queueName + '", "' + queueName + '", ""]';

    _this.stomp.send(BusConnectorHQSW.MANAGEMENT_QUEUE, createQueueHeader,
      createQueueText);

    // Subscribe to new Queue.
    _this.subscribe();
  });
}

/*!
 * Sends message using Bus to a specific recipient.
 *
 * \param message Message to send.
 * \param recipientName The recipient name.
 */
BusConnectorHQSW.prototype.send = function(message, recipientName) {
  this.stomp.send(BusConnectorHQSW.QUEUE + recipientName, {foo:1}, message);
}

//! Disconnect the connector from Bus.
BusConnectorHQSW.prototype.disconnect = function() {
  this.lostConnection ++;
  this.stomp.disconnect();
  this.disconnected = true;
}

/*!
 * Tests if connector is disconnected from bus.
 *
 * \return  \c true if connector is disconnected, \c false otherwise.
 */
BusConnectorHQSW.prototype.isDisconected = function() {
  return this.disconnected;
}

