package kr.lul.kobalttown.borderline.account.cmd;

import lombok.Data;

/**
 * @author justburrow
 * @since 2017. 8. 7.
 */
@Data
public class CreateAccountCmd {
  private String email;
  private String password;

  public CreateAccountCmd() {
  }

  public CreateAccountCmd(String email, String password) {
    this.email = email;
    this.password = password;
  }
}
