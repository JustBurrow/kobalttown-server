package kr.lul.kobalttown.exception;

/**
 * @author justburrow
 * @since 2017. 9. 13.
 */
public class DataNotExistException extends RuntimeException {
  public DataNotExistException() {
  }

  public DataNotExistException(String message) {
    super(message);
  }

  public DataNotExistException(String message, Throwable cause) {
    super(message, cause);
  }

  public DataNotExistException(Throwable cause) {
    super(cause);
  }
}
