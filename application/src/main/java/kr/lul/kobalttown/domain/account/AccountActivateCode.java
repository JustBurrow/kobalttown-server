package kr.lul.kobalttown.domain.account;

import java.time.Instant;

/**
 * 계정 활성화 코드.
 *
 * @author justburrow
 * @since 2017. 9. 6.
 */
public interface AccountActivateCode extends Updatable {
  int  CODE_LENGTH = 60;
  long TTL         = 3600000L;  // 60 * 60 * 1000

  long getId();

  Account getAccount();

  String getCode();

  Instant getExpire();

  default boolean isUsed() {
    return null != getUsed();
  }

  Instant getUsed();
}
