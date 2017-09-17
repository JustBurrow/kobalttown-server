package kr.lul.kobalttown.ms.account;

import kr.lul.kobalttown.util.Anchor;

/**
 * @author justburrow
 * @since 2017. 9. 17.
 */
public abstract class AccountMicroserviceAnchor implements Anchor {
  public static final Package PACKAGE      = AccountMicroserviceAnchor.class.getPackage();
  public static final String  PACKAGE_NAME = PACKAGE.getName();

  protected AccountMicroserviceAnchor() {
    throw new UnsupportedOperationException();
  }
}
