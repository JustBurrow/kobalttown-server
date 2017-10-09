package kr.lul.kobalttown.ms.account.borderline.cmd;

import lombok.Data;
import lombok.NonNull;

/**
 * @author justburrow
 * @since 2017. 10. 9.
 */
@Data
public class ResetAccountCmd {
  @NonNull
  private String email;
  @NonNull
  private String code;
  @NonNull
  private String password;

  public ResetAccountCmd() {
  }

  @Override
  public String toString() {
    return new StringBuffer(ResetAccountCmd.class.getSimpleName())
        .append("{email='").append(this.email).append('\'')
        .append(", code='").append(this.code).append('\'')
        .append(", password=[ PROTECTED ]}").toString();
  }
}
