package kr.lul.kobalttown.jpa.account;

import kr.lul.kobalttown.util.Anchor;

/**
 * @author justburrow
 * @since 2017. 8. 5.
 */
public abstract class AccountJpaAnchor implements Anchor {
  public static final Package PACKAGE      = AccountJpaAnchor.class.getPackage();
  public static final String  PACKAGE_NAME = PACKAGE.getName();

  protected AccountJpaAnchor() {
    throw new UnsupportedOperationException();
  }
}
