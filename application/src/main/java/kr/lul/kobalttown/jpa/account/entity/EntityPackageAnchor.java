package kr.lul.kobalttown.jpa.account.entity;

import kr.lul.kobalttown.util.Anchor;

/**
 * @author justburrow
 * @since 2017. 8. 5.
 */
public abstract class EntityPackageAnchor implements Anchor {
  public static final Package PACKAGE      = EntityPackageAnchor.class.getPackage();
  public static final String  PACKAGE_NAME = PACKAGE.getName();

  protected EntityPackageAnchor() {
    throw new UnsupportedOperationException();
  }
}
