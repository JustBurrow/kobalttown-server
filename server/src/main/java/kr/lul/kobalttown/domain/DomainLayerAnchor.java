package kr.lul.kobalttown.domain;

import kr.lul.kobalttown.util.Anchor;

/**
 * @author justburrow
 * @since 2017. 9. 17.
 */
public abstract class DomainLayerAnchor implements Anchor {
  public static final Package PACKAGE      = DomainLayerAnchor.class.getPackage();
  public static final String  PACKAGE_NAME = PACKAGE.getName();

  protected DomainLayerAnchor() {
  }
}
