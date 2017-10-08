package kr.lul.kobalttown.business.account.service.params;

import lombok.Data;
import lombok.NonNull;

/**
 * @author justburrow
 * @since 2017. 10. 8.
 */
@Data
public class IssueAccountActivateCodeParams {
  @NonNull
  private String email;

  public IssueAccountActivateCodeParams() {
  }
}
