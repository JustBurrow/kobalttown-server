package kr.lul.kobalttown.domain.account;

import kr.lul.kobalttown.util.SimpleString;

/**
 * @author justburrow
 * @since 2017. 8. 5.
 */
public interface Account extends Updatable, SimpleString {
  int EMAIL_MAX_LENGTH = 255;
  int NAME_MAX_LENGTH  = 128;

  long getId();

  String getEmail();

  String getName();

  boolean isEnable();

  void enable();
}
