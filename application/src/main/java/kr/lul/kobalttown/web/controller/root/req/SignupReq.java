package kr.lul.kobalttown.web.controller.root.req;

import lombok.Data;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author justburrow
 * @since 2017. 8. 6.
 */
@Data
public class SignupReq {
  @NotNull
  @Email
  private String email;
  @NotNull
  @Size(min = 1)
  private String password;
  @NotNull
  @Size(min = 1)
  private String confirm;

  public SignupReq() {
  }

  public SignupReq(String email, String password, String confirm) {
    this.email = email;
    this.password = password;
    this.confirm = confirm;
  }
}
