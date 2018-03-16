package kr.lul.kobalttown.account.web.controller.req;

import lombok.Data;
import lombok.NonNull;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;

/**
 * @author justburrow
 * @since 2017. 9. 24.
 */
@Data
public class IssueActivateCodeReq {
  @NonNull
  @NotNull
  @Email
  private String email;

  public IssueActivateCodeReq() {
  }
}
