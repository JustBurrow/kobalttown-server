package kr.lul.kobalttown.account.jpa.repository;

import kr.lul.kobalttown.util.Anchor;

/**
 * @author justburrow
 * @since 2017. 8. 5.
 */
public abstract class AccountRepositoryPackageAnchor implements Anchor {
  public static final Package PACKAGE      = AccountRepositoryPackageAnchor.class.getPackage();
  public static final String  PACKAGE_NAME = PACKAGE.getName();

  protected AccountRepositoryPackageAnchor() {
    throw new UnsupportedOperationException();
  }
}
