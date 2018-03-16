package kr.lul.kobalttown.account.domain;

import kr.lul.kobalttown.account.dao.AccountDaoPackageAnchor;
import kr.lul.kobalttown.util.Anchor;

/**
 * @author justburrow
 * @since 2017. 8. 18.
 */
public abstract class AccountDomanPackageAnchor implements Anchor {
  public static final Package PACKAGE      = AccountDaoPackageAnchor.class.getPackage();
  public static final String  PACKAGE_NAME = PACKAGE.getName();

  protected AccountDomanPackageAnchor() {
    throw new UnsupportedOperationException();
  }
}
