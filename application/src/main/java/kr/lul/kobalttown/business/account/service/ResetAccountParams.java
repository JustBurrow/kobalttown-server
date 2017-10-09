package kr.lul.kobalttown.business.account.service;

import kr.lul.kobalttown.domain.account.Account;
import lombok.Data;
import lombok.NonNull;

/**
 * @author justburrow
 * @since 2017. 10. 9.
 */
@Data
public class ResetAccountParams {
  @NonNull
  private Account account;
  @NonNull
  private String  code;
  @NonNull
  private String  password;

  public ResetAccountParams() {
  }
}
