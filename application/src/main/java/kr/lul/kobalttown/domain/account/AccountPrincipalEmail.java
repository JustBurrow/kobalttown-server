package kr.lul.kobalttown.domain.account;

/**
 * @author justburrow
 * @since 2017. 8. 19.
 */
public interface AccountPrincipalEmail extends AccountPrincipal {
  @Override
  default AccountPrincipalType getType() {
    return AccountPrincipalType.EMAIL_PASSWORD;
  }

  /**
   * @return email address.
   * @see AccountPrincipal#getPublicKey() alias
   */
  default String getEmail() {
    return getPublicKey();
  }

  /**
   * @return hashed password.
   * @see AccountPrincipal#getPrivateKey() alias
   */
  default String getPassword() {
    return getPrivateKey();
  }
}
