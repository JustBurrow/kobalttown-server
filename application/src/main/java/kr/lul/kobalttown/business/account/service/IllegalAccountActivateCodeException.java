package kr.lul.kobalttown.business.account.service;

/**
 * TODO 패키지 이동.
 *
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
