package kr.lul.kobalttown.business.configuration;

import kr.lul.kobalttown.util.Anchor;

/**
 * @author justburrow
 * @since 2017. 9. 17.
 */
public abstract class BusinessConfigurationPackageAnchor implements Anchor {
  public static final Package PACKAGE      = BusinessConfigurationPackageAnchor.class.getPackage();
  public static final String  PACKAGE_NAME = PACKAGE.getName();

  protected BusinessConfigurationPackageAnchor() {
    throw new UnsupportedOperationException();
  }
}
