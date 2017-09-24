package kr.lul.kobalttown.ms.account.web.controller.req;

import kr.lul.kobalttown.domain.account.Account;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author justburrow
 * @since 2017. 9. 24.
 */
@Data
public class EditBasicReq {
  @NotNull
  @Size(min = 1, max = Account.NAME_MAX_LENGTH)
  private String name;
}
