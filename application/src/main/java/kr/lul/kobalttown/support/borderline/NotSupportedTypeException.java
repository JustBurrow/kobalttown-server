package kr.lul.kobalttown.support.borderline;

/**
 * @author justburrow
 * @since 2017. 9. 23.
 */
public class NotSupportedTypeException extends RuntimeException {
  public NotSupportedTypeException() {
  }

  public NotSupportedTypeException(String message) {
    super(message);
  }

  public NotSupportedTypeException(String message, Throwable cause) {
    super(message, cause);
  }

  public NotSupportedTypeException(Throwable cause) {
    super(cause);
  }
}
