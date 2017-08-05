package kr.lul.kobalttown.util;

/**
 * 단정 실패를 처리하기 위한 예외.
 *
 * @author just.burrow@lul.kr
 * @see Asserts
 */
public class AssertionException extends AssertionError {
  public AssertionException() {
  }

  public AssertionException(Object detailMessage) {
    super(detailMessage);
  }

  public AssertionException(boolean detailMessage) {
    super(detailMessage);
  }

  public AssertionException(char detailMessage) {
    super(detailMessage);
  }

  public AssertionException(int detailMessage) {
    super(detailMessage);
  }

  public AssertionException(long detailMessage) {
    super(detailMessage);
  }

  public AssertionException(float detailMessage) {
    super(detailMessage);
  }

  public AssertionException(double detailMessage) {
    super(detailMessage);
  }

  public AssertionException(String message, Throwable cause) {
    super(message, cause);
  }
}
