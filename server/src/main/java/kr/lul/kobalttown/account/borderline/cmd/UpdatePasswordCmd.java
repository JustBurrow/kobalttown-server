package kr.lul.kobalttown.account.borderline.cmd;

import kr.lul.kobalttown.account.domain.AccountPrincipalType;
import kr.lul.kobalttown.account.domain.AccountSimple;
import kr.lul.kobalttown.support.borderline.OperatorCmd;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * @author justburrow
 * @since 2017. 9. 24.
 */
@Getter
@Setter
public class UpdatePasswordCmd extends OperatorCmd {
  @NonNull
  private AccountPrincipalType type;
  @NonNull
  private String               publickKey;
  @NonNull
  private String               current;
  @NonNull
  private String               password;

  public UpdatePasswordCmd(AccountSimple operator) {
    super(operator);
  }

  @Override
  public String toString() {
    return new StringBuffer(UpdatePasswordCmd.class.getSimpleName())
        .append("{operator=").append(this.operator)
        .append(", type=").append(this.type)
        .append(", publicKey='").append(this.publickKey).append('\'')
        .append(", current=[ PROTECTED ], password=[ PROTECTED ]}").toString();
  }
}
