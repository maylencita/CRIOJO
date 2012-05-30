package fr.emn.criojo.network;

/**
 * BusConnectorException is exception class over BusConnector.
 * 
 * BusConnectorException defined many error codes to specify error type.
 */
public class BusConnectorException extends Exception {
  private static final long serialVersionUID = -9169810841395408704L;
  private int code;

  /**
   * Internal error which prevented Bus to perform.
   */
  public static final int INTERNAL_ERROR = 0;

  /**
   * Bus connector is closed.
   */
  public static final int CLOSED = 1;
  
  /**
   * Bus connector not connected.
   */
  public static final int NOT_CONNECTED = 2;

  /**
   * Default constructor of Bus error. The code is BusException.INTERNAL_ERROR.
   */
  public BusConnectorException() {
    super();
    code = INTERNAL_ERROR;
  }

  /**
   * Constructor of Bus error with msg. The code is BusException.INTERNAL_ERROR.
   */
  public BusConnectorException(String msg) {
    super(msg);
    code = INTERNAL_ERROR;
  }

  /**
   * Constructor of Bus error with given code and optional msg.
   */
  public BusConnectorException(int code, String msg) {
    super(getMsgFromCode(code, msg));
    this.code = code;
  }

  /**
   * Returns the error code.
   * 
   * @return Error code.
   */
  public int getCode() {
    return code;
  }

  /**
   * For a give code, returns defined message.
   * 
   * Complement parameter differ from code. Next, list complement element.
   * <ul>
   * <li>INTERNAL_ERROR: additional message</li>
   * <li>CLOSED: bus name</li>
   * <li>NOT_CONNECTED: additional message</li>
   * </ul>
   * @param code
   *          The code.
   * @param complement
   *          The complement to defined message.
   * @return The defined message adding with complement.
   */
  private static String getMsgFromCode(int code, String complement) {
    String errorMsg;

    switch (code) {
    case INTERNAL_ERROR:
      errorMsg = "[INTERNAL_ERROR] " + complement;
      break;
    case CLOSED:
      errorMsg = "[CLOSED] Bus connector " + complement + " is closed";
      break;
    case NOT_CONNECTED:
      errorMsg = "[NOT_CONNECTED] Bus connector is not connected " + complement;
      break;
    default:
      errorMsg = "[" + code + "] " + complement;
    }

    return errorMsg;
  }
}

