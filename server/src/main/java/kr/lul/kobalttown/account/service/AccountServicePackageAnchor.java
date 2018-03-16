package kr.lul.kobalttown.account.service;

import kr.lul.kobalttown.util.Anchor;

/**
 * @author justburrow
 * @since 2017. 8. 6.
 */
public class AccountServicePackageAnchor implements Anchor {
  public static final Package PACKAGE      = AccountServicePackageAnchor.class.getPackage();
  public static final String  PACKAGE_NAME = PACKAGE.getName();

  protected AccountServicePackageAnchor() {
    throw new UnsupportedOperationException();
  }
}
