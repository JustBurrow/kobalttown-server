package kr.lul.kobalttown.util;

/**
 * @author justburrow
 * @since 2017. 8. 19.
 */
public class IllegalConfigurationException extends RuntimeException {
  public IllegalConfigurationException() {
    super();
  }

  public IllegalConfigurationException(String message) {
    super(message);
  }

  public IllegalConfigurationException(String message, Throwable cause) {
    super(message, cause);
  }

  public IllegalConfigurationException(Throwable cause) {
    super(cause);
  }

  protected IllegalConfigurationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
