package kr.lul.kobalttown.dao;

import kr.lul.kobalttown.util.Anchor;

/**
 * @author justburrow
 * @since 2017. 8. 8.
 */
public abstract class DaoPackageAnchor implements Anchor {
  public static final Package PACKAGE      = DaoPackageAnchor.class.getPackage();
  public static final String  PACKAGE_NAME = PACKAGE.getName();

  protected DaoPackageAnchor() {
    throw new UnsupportedOperationException();
  }
}
