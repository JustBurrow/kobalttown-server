package kr.lul.kobalttown.account.borderline.cmd;

import lombok.Data;
import lombok.NonNull;

/**
 * @author justburrow
 * @since 2017. 10. 8.
 */
@Data
public class IssueAccountActivateCode {
  @NonNull
  private String email;

  public IssueAccountActivateCode() {
  }
}
