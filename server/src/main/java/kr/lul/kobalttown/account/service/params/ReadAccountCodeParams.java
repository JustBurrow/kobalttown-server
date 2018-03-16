package kr.lul.kobalttown.account.service.params;

import kr.lul.kobalttown.account.domain.AccountCodeType;
import lombok.Data;
import lombok.NonNull;

/**
 * @author justburrow
 * @since 2017. 10. 9.
 */
@Data
public class ReadAccountCodeParams {
  @NonNull
  private AccountCodeType type;
  @NonNull
  private String          code;

  public ReadAccountCodeParams() {
  }
}
