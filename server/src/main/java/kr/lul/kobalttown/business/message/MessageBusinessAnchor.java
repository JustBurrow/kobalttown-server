package kr.lul.kobalttown.business.message;

import kr.lul.kobalttown.util.Anchor;

/**
 * @author justburrow
 * @since 2017. 9. 17.
 */
public abstract class MessageBusinessAnchor implements Anchor {
  public static final Package PACKAGE      = MessageBusinessAnchor.class.getPackage();
  public static final String  PACKAGE_NAME = PACKAGE.getName();

  protected MessageBusinessAnchor() {
    throw new UnsupportedOperationException();
  }
}
