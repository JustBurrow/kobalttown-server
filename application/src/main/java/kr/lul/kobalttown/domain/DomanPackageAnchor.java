package kr.lul.kobalttown.domain;

import kr.lul.kobalttown.dao.DaoPackageAnchor;
import kr.lul.kobalttown.util.Anchor;

/**
 * @author justburrow
 * @since 2017. 8. 18.
 */
public abstract class DomanPackageAnchor implements Anchor {
  public static final Package PACKAGE      = DaoPackageAnchor.class.getPackage();
  public static final String  PACKAGE_NAME = PACKAGE.getName();

  protected DomanPackageAnchor() {
    throw new UnsupportedOperationException();
  }
}
