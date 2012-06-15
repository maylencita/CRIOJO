// ******************************************************************** Utils **
if (typeof String.prototype.startsWith != 'function') {
  String.prototype.startsWith = function(str) {
    return this.slice(0, str.length) == str;
  };
}

/*!
 * Returns true if all elements of array respect condition.
 *
 * \exemple ['foo', 'foo'].forall(function(str) { return (str == 'foo'); });
 * \param   testFunction Function tacking array element in parameter and return
 *          true, or false.
 * \return  \c true if all array elements respect testFunction confition,
 *          \c false otherwise.
 */
if (typeof Array.prototype.forall != 'function') {
  Array.prototype.forall = function(testFunction) {
    for (var i = 0; i < this.length; ++i ) {
      if (!testFunction(this[i])) { return false; }
    }
    return true;
  };
}

/*!
 *+ from: http://phpjs.org
 *+ original by: Kevin van Zonneveld (http://kevin.vanzonneveld.net)
 *+ revised by: Kankrelune (http://www.webfaktory.info/)
 *% note 1: Uses an internal counter (in php_js global) to avoid collision
 *
 * example 1: uniqid();
 * returns 1: 'a30285b160c14'
 * example 2: uniqid('foo');
 * returns 2: 'fooa30285b1cd361'
 * example 3: uniqid('bar', true);
 * returns 3: 'bara20285b23dfd1.31879087'
 */
function uniqid (prefix, more_entropy) {
  if (typeof prefix == 'undefined') {
    prefix = "";
  }

  var retId;
  var formatSeed = function (seed, reqWidth) {
    seed = parseInt(seed, 10).toString(16); 
    if (reqWidth < seed.length) { 
      return seed.slice(seed.length - reqWidth);
    }
    if (reqWidth > seed.length) { 
      return Array(1 + (reqWidth - seed.length)).join('0') + seed;
    }
    return seed;
  };

  // BEGIN REDUNDANT
  if (!this.php_js) {
    this.php_js = {};
  }
  // END REDUNDANT
  if (!this.php_js.uniqidSeed) { 
    this.php_js.uniqidSeed = Math.floor(Math.random() * 0x75bcd15);
  }
  this.php_js.uniqidSeed++;

  retId = prefix; 
  retId += formatSeed(parseInt(new Date().getTime() / 1000, 10), 8);
  retId += formatSeed(this.php_js.uniqidSeed, 5); 
  if (more_entropy) {
    // for more entropy we add a float lower to 10
    retId += (Math.random() * 10).toFixed(8).toString();
  }

  return retId;
}

function Subscriber() {}

//! Store all subscribers.
Subscriber.SUBSCRIBERS = [];

//! Callback function call after all subscriber subscribe is acknoledge.
Subscriber.connectCallback = function() {};

//! Test if all subscriber subscribe successfully to launch connectCallback.
Subscriber.tryLaunchConnectCallback = function() {
  console.log("Try launch onConnect");
  if (Subscriber.SUBSCRIBERS.forall(function(sm) { return sm.isAck; })) {
    Subscriber.connectCallback();
  }
}

/*!
 * \class   Subscriber
 */
function Subscriber(queueName, connector, subscribeMaxAttempt,
    subscribeTime, debugCallback, receiveCallback) {
  var _this = this;

  //! The Queue adresse to subscribe.
  this.queueName = queueName;

  //! Max attempt for subscription.
  this.subscribeMaxAttempt = subscribeMaxAttempt;

  //! Laps time between subscription attempt (in ms).
  this.subscribeTime = subscribeTime;

  //! Acknoledgment subscription message.
  //! FIXME: have a better ack message. This message is forbiden to emit on
  //! network, riqque of trouble during receive message process.
  this.subscribeAckMessage = '##---## Ack of ' + queueName + ' - ' + uniqid()
    + ' ##---##';

  //! Number of attemp subscription on queue.
  this.subscribeAttempt = 0;

  //! Time before new subscription on queue.
  this.subscribeWaitTime = this.subscribeTime + (this.subscribeTime / 5.);

  //! Timer will launch subscription on queue (get with setTimeout).
  this.subscribeTimer = false;

  //! Error message on subscription to queue.
  this.subscribeError = new RegExp('message: Address '
      + queueName + ' has not been deployed');

  //! Defined if subscription is do
  this.isAck = false;

  //! Debug function to print debug message.
  this.debugCallback = debugCallback || false;

  //! Callback function call after receive message.
  this.receiveCallback = receiveCallback || false;

  //! The bus connector which try to subscribe.
  this.connector = connector;

  // ## MAIN -- Connect on Bus ##
  Subscriber.SUBSCRIBERS.push(this);
}

/*!
 * Subscribe to queueName.
 *
 * The connection is successfully when connector success to connect on 
 * dedicated queue. This method use specific message subscribeAckMessage sends to
 * itself to ensure success subscription. When all subscription are ok, the
 * methode connectCallback is call.
 */
Subscriber.prototype.subscribe = function() {
  var _this = this;
  this.subscribeAttempt ++;

  this.connector.stomp.subscribe(this.queueName, function(frame) {
    // When a ack message is received and current not ack subscription,
    // the subscription is ack and we try to launch connectCallback;
    if (frame.body == _this.subscribeAckMessage
        && !_this.isAck) {
      _this.isAck = true;
      Subscriber.tryLaunchConnectCallback();
    } else if (!frame.body.startsWith('##---## Ack of ')) {
      if (_this.receiveCallback) { _this.receiveCallback(frame.body); }
    }
  });

  // After subscription, send ack subscription to launch onConnect
  // This command will be executed exclusively if subscription works well.
  setTimeout(function() {
    var queueName = (_this.queueName.startsWith(BusConnectorHQSW.BROADCAST)) 
        ? BusConnectorHQSW.BROADCAST : _this.queueName;
    _this.connector.stomp.send(queueName, {foo:1}, _this.subscribeAckMessage);
  }, this.subscribeWaitTime);
}

//! Try to reconnect after subscribeTime
Subscriber.prototype.tryNew = function() {
  if (this.subscribeAttempt >= this.subscribeMaxAttempt) {
    if (this.debugCallback) { this.debugCallback(debugMsg); }
  } else {
    clearTimeout(this.subscribeTimer);
    this.subscribeTimer = setTimeout(this.subscribe, this.subscribeTime);
  }
}

// ********************************************************* BusConnectorHQSW **
function BusConnectorHQSW() {}

//! Personal Queue name prefix.
BusConnectorHQSW.PERSONAL = 'personal';

//! Broadcast Queue name prefix.
BusConnectorHQSW.BROADCAST = 'broadcast';

//! Management Queue name.
BusConnectorHQSW.MANAGEMENT_QUEUE = 'jms.queue.hornetq.management';

//! Max attempt for subscription.
BusConnectorHQSW.SUBSCRIPTION_MAX_ATTEMPT = 10;

//! Laps time between subscription attempt (in ms).
BusConnectorHQSW.SUBSCRIPTION_TIME = 100;

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
  var login = login || 'guest';
  var passcode = passcode || 'guest';

  //! Connector name.
  this.name = name;

  //! Stomp over WebSocket connector on HornetQ.
  this.stomp = new Stomp.client(url);

  //! Test if connector is disconnected (or not).
  this.disconnected = true;

  //! Subscriber of Personal Queue.
  var personalSubscriber = new Subscriber(this.getPersonalQueueName(),
      this, BusConnectorHQSW.SUBSCRIPTION_MAX_ATTEMPT,
      BusConnectorHQSW.SUBSCRIPTION_TIME, debug, onReceive);

  //! Subscriber of Broadcast Queue.
  var broadcastSubscriber = new Subscriber(this.getBroadcastQueueName(),
      this, BusConnectorHQSW.SUBSCRIPTION_MAX_ATTEMPT,
      BusConnectorHQSW.SUBSCRIPTION_TIME, debug, onReceive);

  //! Successive Lost connection Number.
  this.lostConnection = 0;

  //! Set debug mode.
  this.stomp.debug = function(debugMsg) {
    if (debugMsg.startsWith('<<< ERROR')) {
      if (personalSubscriber.subscribeError.test(debugMsg)) {
        personalSubscriber.tryNew();
      } else if (broadcastSubscriber.subscribeError.test(debugMsg)) {
        broadcastSubscriber.tryNew();
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
          _this.stomp.subscribe(_this.getBroadcastQueueName(), function(frame) {
            if (onReceive) { onReceive(frame.body); }
          });
          _this.stomp.subscribe(_this.getPersonalQueueName(), function(frame) {
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
   * Ask Bus Node to deploy new Queue.
   *
   * The Bus Node would be a HornetQ Bus Node implementation and this would
   * have a Management Queue named BusConnectorHQSW.MANAGEMENT_QUEUE.
   *
   * \param   address Adress of Queue.
   * \param   name  Name of Queue.
   */
  var deployQueue = function(address, name) {
    var header = {
      'reply-to'          : BusConnectorHQSW.PERSONAL + '.debug',
      '_HQ_ResourceName'  : 'core.server',
      '_HQ_OperationName' : 'deployQueue'
    };
    var request = '["' + address + '", "' + name + '", ""]';

    _this.stomp.send(BusConnectorHQSW.MANAGEMENT_QUEUE, header, request);
  }

  // ## MAIN -- Connect on Bus ##
  Subscriber.connectCallback = function() {
    _this.disconnected = false;
    if (onConnect) { onConnect(); }
  }

  this.stomp.connect(login, passcode, function(frame) {
    // Once connected, ask to create new Queues.
    deployQueue(BusConnectorHQSW.BROADCAST, _this.getBroadcastQueueName());
    deployQueue(_this.getPersonalQueueName(), _this.getPersonalQueueName());

    // Subscribe to new Queue.
    personalSubscriber.subscribe();
    broadcastSubscriber.subscribe();
  });
}

/*!
 * Sends message using Bus to a specific recipient.
 *
 * \param message Message to send.
 * \param recipientName The recipient name.
 */
BusConnectorHQSW.prototype.send = function(message, recipientName) {
  // If recipient is empty, the message is broadcasted over the network.
  var queueAddr = (recipientName.length > 0)
      ? BusConnectorHQSW.PERSONAL + '.' + recipientName
      : BusConnectorHQSW.BROADCAST;

  this.stomp.send(queueAddr, {foo:1}, message);
}

/*!
 * Sends message using Bus to a all recipient.
 *
 * \param message Message to send.
 */
BusConnectorHQSW.prototype.broadcast = function(message) {
  this.send(message, '');
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

/*!
 * Returns the connector name would be unique on Bus.
 *
 * \return The connector name.
 */
BusConnectorHQSW.prototype.getName = function() {
  return this.name;
}

/*!
 * Returns the queue name were using to store received msg of bus connector.
 *
 * \return The HornetQ queue name.
 */
BusConnectorHQSW.prototype.getPersonalQueueName = function() {
  return BusConnectorHQSW.PERSONAL + '.' + this.getName();
}

/*!
 * Returns the queue name were using to store broadcasted msg of bus connector.
 * 
 * \return The HornetQ queue name.
 */
BusConnectorHQSW.prototype.getBroadcastQueueName = function() {
  return BusConnectorHQSW.BROADCAST + '.' + this.getName();
}

