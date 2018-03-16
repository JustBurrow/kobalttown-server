package kr.lul.kobalttown.account.dao;

import kr.lul.kobalttown.util.Anchor;

/**
 * @author justburrow
 * @since 2017. 8. 8.
 */
public abstract class AccountDaoPackageAnchor implements Anchor {
  public static final Package PACKAGE      = AccountDaoPackageAnchor.class.getPackage();
  public static final String  PACKAGE_NAME = PACKAGE.getName();

  protected AccountDaoPackageAnchor() {
    throw new UnsupportedOperationException();
  }
}
