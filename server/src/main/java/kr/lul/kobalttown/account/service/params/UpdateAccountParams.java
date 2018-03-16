package kr.lul.kobalttown.account.service.params;

import kr.lul.kobalttown.account.domain.Account;
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
