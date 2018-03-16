package kr.lul.kobalttown.account.borderline;

import kr.lul.kobalttown.util.Anchor;

/**
 * @author justburrow
 * @since 2017. 8. 7.
 */
public class AccountBorderlinePackageAnchor implements Anchor {
  public static final Package PACKAGE      = AccountBorderlinePackageAnchor.class.getPackage();
  public static final String  PACKAGE_NAME = PACKAGE.getName();

  protected AccountBorderlinePackageAnchor() {
    throw new UnsupportedOperationException();
  }
}
