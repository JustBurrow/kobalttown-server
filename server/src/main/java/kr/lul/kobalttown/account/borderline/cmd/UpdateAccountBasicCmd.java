package kr.lul.kobalttown.account.borderline.cmd;

import kr.lul.kobalttown.account.domain.AccountSimple;
import kr.lul.kobalttown.support.borderline.OperatorCmd;
import lombok.Data;

/**
 * @author justburrow
 * @since 2017. 9. 24.
 */
@Data
public class UpdateAccountBasicCmd extends OperatorCmd {
  private String name;

  public UpdateAccountBasicCmd(AccountSimple operator) {
    super(operator);
  }
}
