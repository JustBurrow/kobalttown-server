package kr.lul.kobalttown.ms.account.borderline.cmd;

import kr.lul.kobalttown.domain.account.AccountSimple;
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
