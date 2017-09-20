package kr.lul.kobalttown.support.jpa.converter;

import kr.lul.kobalttown.util.Anchor;

/**
 * @author justburrow
 * @since 2017. 8. 5.
 */
public abstract class ConverterPackageAnchor implements Anchor {
  public static final Package PACKAGE      = ConverterPackageAnchor.class.getPackage();
  public static final String  PACKAGE_NAME = PACKAGE.getName();

  protected ConverterPackageAnchor() {
    throw new UnsupportedOperationException();
  }
}
