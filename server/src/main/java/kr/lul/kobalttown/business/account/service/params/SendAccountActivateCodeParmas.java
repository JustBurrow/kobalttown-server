package kr.lul.kobalttown.business.account.service.params;

import kr.lul.kobalttown.domain.account.Account;
import lombok.Data;

/**
 * @author justburrow
 * @since 2017. 8. 26.
 */
@Data
public class SendAccountActivateCodeParmas {
  private Account account;
  private String  code;

  public SendAccountActivateCodeParmas() {
  }

  public SendAccountActivateCodeParmas(Account account) {
    this.account = account;
  }

  public SendAccountActivateCodeParmas(Account account, String code) {
    this(account);
    this.code = code;
  }
}
