package kr.lul.kobalttown.domain.account;

/**
 * @author justburrow
 * @since 2017. 8. 18.
 */
public interface AccountPrincipal extends Creatable {
  long getId();

  Account getAccount();

  String getPublicKey();

  String getPrivateKey();
}
