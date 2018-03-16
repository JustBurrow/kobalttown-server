package kr.lul.kobalttown.account.borderline.cmd;

import lombok.Data;

/**
 * @author justburrow
 * @since 2017. 8. 7.
 */
@Data
public class CreateAccountCmd {
  private String email;
  private String name;
  private String password;

  public CreateAccountCmd() {
  }

  public CreateAccountCmd(String email, String name, String password) {
    this.email = email;
    this.name = name;
    this.password = password;
  }
}
