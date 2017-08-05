package kr.lul.kobalttown.jpa;

import kr.lul.kobalttown.util.Anchor;

/**
 * @author justburrow
 * @since 2017. 8. 5.
 */
public abstract class JpaPackageAnchor implements Anchor {
  public static final Package PACKAGE      = JpaPackageAnchor.class.getPackage();
  public static final String  PACKAGE_NAME = PACKAGE.getName();

  protected JpaPackageAnchor() {
    throw new UnsupportedOperationException();
  }
}
