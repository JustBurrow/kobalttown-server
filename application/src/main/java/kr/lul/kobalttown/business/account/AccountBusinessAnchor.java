package kr.lul.kobalttown.business.account;

import kr.lul.kobalttown.util.Anchor;

/**
 * @author justburrow
 * @since 2017. 9. 17.
 */
public abstract class AccountBusinessAnchor implements Anchor {
  public static final Package PACKAGE      = AccountBusinessAnchor.class.getPackage();
  public static final String  PACKAGE_NAME = PACKAGE.getName();

  protected AccountBusinessAnchor() {
    throw new UnsupportedOperationException();
  }
}
