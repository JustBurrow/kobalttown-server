package kr.lul.kobalttown.domain.account;

/**
 * @author justburrow
 * @since 2017. 10. 1.
 */
public interface AccountCodeActivate extends AccountCode {
  @Override
  default AccountCodeType getType() {
    return AccountCodeType.ACTIVATE;
  }
}
