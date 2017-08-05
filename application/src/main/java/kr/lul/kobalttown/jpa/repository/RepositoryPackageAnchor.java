package kr.lul.kobalttown.jpa.repository;

import kr.lul.kobalttown.util.Anchor;

/**
 * @author justburrow
 * @since 2017. 8. 5.
 */
public abstract class RepositoryPackageAnchor implements Anchor {
  public static final Package PACKAGE      = RepositoryPackageAnchor.class.getPackage();
  public static final String  PACKAGE_NAME = PACKAGE.getName();

  protected RepositoryPackageAnchor() {
    throw new UnsupportedOperationException();
  }
}
