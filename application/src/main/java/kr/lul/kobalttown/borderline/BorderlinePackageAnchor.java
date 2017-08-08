package kr.lul.kobalttown.borderline;

import kr.lul.kobalttown.util.Anchor;

/**
 * @author justburrow
 * @since 2017. 8. 7.
 */
public class BorderlinePackageAnchor implements Anchor {
  public static final Package PACKAGE      = BorderlinePackageAnchor.class.getPackage();
  public static final String  PACKAGE_NAME = PACKAGE.getName();

  protected BorderlinePackageAnchor() {
    throw new UnsupportedOperationException();
  }
}
