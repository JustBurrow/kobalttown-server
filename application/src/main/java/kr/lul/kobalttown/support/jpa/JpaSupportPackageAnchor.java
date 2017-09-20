package kr.lul.kobalttown.support.jpa;

import kr.lul.kobalttown.util.Anchor;

/**
 * @author justburrow
 * @since 2017. 9. 17.
 */
public abstract class JpaSupportPackageAnchor implements Anchor {
  public static final Package PACKAGE      = JpaSupportPackageAnchor.class.getPackage();
  public static final String  PACKAGE_NAME = PACKAGE.getName();

  protected JpaSupportPackageAnchor() {
    throw new UnsupportedOperationException();
  }
}
