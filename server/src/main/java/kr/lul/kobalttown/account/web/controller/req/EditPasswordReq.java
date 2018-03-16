package kr.lul.kobalttown.account.web.controller.req;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author justburrow
 * @since 2017. 9. 24.
 */
@Data
public class EditPasswordReq {
  @NotNull
  @Size(min = 4)
  private String current;
  @NotNull
  @Size(min = 4)
  private String password;
  @NotNull
  @Size(min = 4)
  private String confirm;

  public void init() {
    this.current = null;
    this.password = null;
    this.confirm = null;
  }
}
