package kr.lul.kobalttown.account.domain;

/**
 * @author justburrow
 * @since 2017. 8. 18.
 */
public interface AccountPrincipal extends Creatable {
  long getId();

  AccountPrincipalType getType();

  Account getAccount();

  String getPublicKey();

  String getPrivateKey();
}
