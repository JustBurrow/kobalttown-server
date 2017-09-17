package kr.lul.kobalttown.ms.account.web;

/**
 * @author justburrow
 * @since 2017. 9. 17.
 */
public abstract class AccountWebPackageAnchor {
  public static final Package PACKAGE      = AccountWebPackageAnchor.class.getPackage();
  public static final String  PACKAGE_NAME = PACKAGE.getName();

  protected AccountWebPackageAnchor() {
    throw new UnsupportedOperationException();
  }
}
