package kr.lul.kobalttown.ms.account.web.controller.req;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author justburrow
 * @since 2017. 9. 24.
 */
@Data
public class IssueAccountResetCodeReq {
  @NotNull
  private String email;
}
