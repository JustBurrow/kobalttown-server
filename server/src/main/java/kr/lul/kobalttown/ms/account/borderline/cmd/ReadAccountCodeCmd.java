package kr.lul.kobalttown.ms.account.borderline.cmd;

import kr.lul.kobalttown.domain.account.AccountCodeType;
import lombok.Data;
import lombok.NonNull;

/**
 * @author justburrow
 * @since 2017. 10. 9.
 */
@Data
public class ReadAccountCodeCmd {
  @NonNull
  private AccountCodeType type;
  @NonNull
  private String          code;

  public ReadAccountCodeCmd() {
  }
}
