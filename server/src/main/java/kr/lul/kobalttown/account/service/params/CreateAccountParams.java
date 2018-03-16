package kr.lul.kobalttown.account.service.params;

import lombok.Data;

/**
 * @author justburrow
 * @since 2017. 8. 6.
 */
@Data
public class CreateAccountParams {
  private String email;
  private String name;
  private String password;

  public CreateAccountParams() {
  }

  public CreateAccountParams(String email, String name, String password) {
    this.email = email;
    this.name = name;
    this.password = password;
  }
}
