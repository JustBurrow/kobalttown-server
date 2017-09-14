package kr.lul.kobalttown.service.account;

/**
 * @author justburrow
 * @since 2017. 9. 13.
 */
public class IllegalAccountActivateCodeException extends Exception {
  private final String code;

  public IllegalAccountActivateCodeException(String code) {
    this.code = code;
  }

  public IllegalAccountActivateCodeException(String code, String message) {
    super(message);
    this.code = code;
  }

  public String getCode() {
    return this.code;
  }
}
