package kr.lul.kobalttown.account.service.params;

import lombok.Data;
import lombok.NonNull;

/**
 * @author justburrow
 * @since 2017. 9. 28.
 */
@Data
public class IssueAccountResetCodeParams {
  @NonNull
  private String email;

  public IssueAccountResetCodeParams() {
  }
}
