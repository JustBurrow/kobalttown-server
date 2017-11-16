package kr.lul.kobalttown.jpa;

import kr.lul.kobalttown.util.Anchor;

/**
 * @author justburrow
 * @since 2017. 9. 17.
 */
public abstract class JpaLayerAnchor implements Anchor {
  public static final Package PACKAGE      = JpaLayerAnchor.class.getPackage();
  public static final String  PACKAGE_NAME = PACKAGE.getName();

  protected JpaLayerAnchor() {
    throw new UnsupportedOperationException();
  }
}
