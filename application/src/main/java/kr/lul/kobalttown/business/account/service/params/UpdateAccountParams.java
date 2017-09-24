package kr.lul.kobalttown.business.account.service.params;

import kr.lul.kobalttown.domain.account.Account;
import kr.lul.kobalttown.support.service.OperatorParams;
import lombok.Data;

/**
 * @author justburrow
 * @since 2017. 9. 24.
 */
@Data
public class UpdateAccountParams extends OperatorParams {
  private String name;

  public UpdateAccountParams(Account operator) {
    super(operator);
  }
}
