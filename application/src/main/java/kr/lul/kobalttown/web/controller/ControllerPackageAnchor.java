package kr.lul.kobalttown.web.controller;

import kr.lul.kobalttown.jpa.support.converter.ConverterPackageAnchor;
import kr.lul.kobalttown.util.Anchor;

/**
 * @author justburrow
 * @since 2017. 8. 9.
 */
public class ControllerPackageAnchor implements Anchor {
  public static final Package PACKAGE      = ConverterPackageAnchor.class.getPackage();
  public static final String  PACKAGE_NAME = PACKAGE.getName();

  protected ControllerPackageAnchor() {
    throw new UnsupportedOperationException();
  }
}
