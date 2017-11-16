package kr.lul.kobalttown.business;

import kr.lul.kobalttown.util.Anchor;

/**
 * @author justburrow
 * @since 2017. 9. 17.
 */
public abstract class BussinessLayerAnchor implements Anchor {
  public static final Package PACKAGE      = BussinessLayerAnchor.class.getPackage();
  public static final String  PACKAGE_NAME = PACKAGE.getName();

  protected BussinessLayerAnchor() {
    throw new UnsupportedOperationException();
  }
}
