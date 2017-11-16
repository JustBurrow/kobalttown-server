package kr.lul.kobalttown.business.message.service;

import kr.lul.kobalttown.util.Anchor;

/**
 * @author justburrow
 * @since 2017. 9. 17.
 */
public class MessageServicePackageAnchor implements Anchor {
  public static final Package PACKAGE      = MessageServicePackageAnchor.class.getPackage();
  public static final String  PACKAGE_NAME = PACKAGE.getName();

  protected MessageServicePackageAnchor() {
    throw new UnsupportedOperationException();
  }
}
