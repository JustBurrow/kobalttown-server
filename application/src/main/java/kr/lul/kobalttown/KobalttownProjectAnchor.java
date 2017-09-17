package kr.lul.kobalttown;

import kr.lul.kobalttown.util.Anchor;

/**
 * @author justburrow
 * @since 2017. 9. 17.
 */
public class KobalttownProjectAnchor implements Anchor {
  public static final Package PACKAGE      = KobalttownProjectAnchor.class.getPackage();
  public static final String  PACKAGE_NAME = PACKAGE.getName();

  protected KobalttownProjectAnchor() {
    throw new UnsupportedOperationException();
  }
}
