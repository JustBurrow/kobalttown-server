package kr.lul.kobalttown.service;

import kr.lul.kobalttown.util.Anchor;

/**
 * @author justburrow
 * @since 2017. 8. 6.
 */
public class ServicePackageAnchor implements Anchor {
  public static final Package PACKAGE      = ServicePackageAnchor.class.getPackage();
  public static final String  PACKAGE_NAME = PACKAGE.getName();

  protected ServicePackageAnchor() {
    throw new UnsupportedOperationException();
  }
}
