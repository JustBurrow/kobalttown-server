package kr.lul.kobalttown.account.web.controller.req;

import lombok.Data;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;

/**
 * @author justburrow
 * @since 2017. 10. 9.
 */
@Data
public class ResetAccountReq {
  @NotNull
  @Email
  private String email;
  @NotNull
  private String password;
  @NotNull
  private String confirm;

  public ResetAccountReq() {
  }
}
