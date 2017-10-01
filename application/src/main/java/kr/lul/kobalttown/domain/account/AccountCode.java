package kr.lul.kobalttown.domain.account;

import java.time.Instant;

/**
 * 계정 활성화 코드. 계정당 최대 1개 존재한다.
 *
 * @author justburrow
 * @since 2017. 9. 6.
 */
public interface AccountCode extends Updatable {
  /**
   * 활성화 코드 길이. 고정.
   */
  int    CODE_LENGTH  = 60;
  /**
   * 활성화 코드 문자열의 정규표현식 패턴.
   *
   * @see #CODE_LENGTH
   */
  String CODE_PATTERN = "[\\da-zA-Z]{" + CODE_LENGTH + "}";
  /**
   * 활성화 코드의 수명. ms.
   */
  long   TTL          = 3600000L;  // 60 * 60 * 1000

  long getId();

  /**
   * 이 코드로 활성화 할 수 있는 계정.
   *
   * @return
   */
  Account getAccount();

  AccountCodeType getType();

  /**
   * 활성화 코드.
   *
   * @return
   * @see #CODE_LENGTH
   */
  String getCode();

  /**
   * 활성화 코드의 유효기간. 이 시각까지 사용할 수 있다. 미포함.
   *
   * @return
   * @see #TTL
   */
  Instant getExpire();

  /**
   * 코드를 사용해 계정을 활성화 했는지 여부.
   *
   * @return
   */
  default boolean isUsed() {
    return null != getUsed();
  }

  /**
   * 코드를 사용해 계정을 활성화한 시각.
   *
   * @return 코드 사용 시각. 사용하지 않았으면 {@code null}.
   */
  Instant getUsed();

  /**
   * 코드를 사용해 계정을 활성화하고, 해당 계정을 반환한다.
   *
   * @return
   */
  Account use();
}
