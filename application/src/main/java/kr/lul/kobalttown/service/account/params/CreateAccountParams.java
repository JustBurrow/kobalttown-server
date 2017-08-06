package kr.lul.kobalttown.service.account.params;

import lombok.Data;

/**
 * @author justburrow
 * @since 2017. 8. 6.
 */
@Data
public class CreateAccountParams {
  private String email;
  private String password;

  public CreateAccountParams() {
  }

  public CreateAccountParams(String email, String password) {
    this.email = email;
    this.password = password;
  }
}
