package kr.lul.kobalttown.business.account.service.params;

import kr.lul.kobalttown.domain.account.Account;
import kr.lul.kobalttown.domain.account.AccountPrincipalType;
import kr.lul.kobalttown.support.service.OperatorParams;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author justburrow
 * @since 2017. 9. 21.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UpdatePrincipalParams extends OperatorParams {
  private AccountPrincipalType type;
  private String               publicKey;
  private String               oldPrivateKey;
  private String               newPrivateKey;

  public UpdatePrincipalParams(Account operator) {
    super(operator);
  }
}
