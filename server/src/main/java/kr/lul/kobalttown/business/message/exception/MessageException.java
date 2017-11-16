package kr.lul.kobalttown.business.message.exception;

/**
 * @author justburrow
 * @since 2017. 9. 1.
 */
public class MessageException extends Exception {
  public MessageException() {
  }

  public MessageException(String message) {
    super(message);
  }

  public MessageException(String message, Throwable cause) {
    super(message, cause);
  }

  public MessageException(Throwable cause) {
    super(cause);
  }
}
