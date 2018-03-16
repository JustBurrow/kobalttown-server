package kr.lul.kobalttown.account.borderline.cmd;

import lombok.Data;
import lombok.NonNull;

/**
 * @author justburrow
 * @since 2017. 9. 28.
 */
@Data
public class IssueAccountResetCodeCmd {
  @NonNull
  private String email;

  public IssueAccountResetCodeCmd() {
  }
}
