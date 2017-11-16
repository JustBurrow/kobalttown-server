package kr.lul.kobalttown.business.account.exception;

/**
 * @author justburrow
 * @since 2017. 9. 21.
 */
public class AccountStateException extends IllegalStateException {
  public AccountStateException() {
  }

  public AccountStateException(String message) {
    super(message);
  }

  public AccountStateException(String message, Throwable cause) {
    super(message, cause);
  }

  public AccountStateException(Throwable cause) {
    super(cause);
  }
}
