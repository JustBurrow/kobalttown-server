package kr.lul.kobalttown.account.domain;

import kr.lul.kobalttown.util.SimpleString;

/**
 * @author justburrow
 * @since 2017. 8. 5.
 */
public interface Account extends Updatable, SimpleString {
  int    EMAIL_MAX_LENGTH = 255;
  int    NAME_MAX_LENGTH  = 128;
  String NAME_PATTERN     = ".{1," + NAME_MAX_LENGTH + "}";

  long getId();

  String getEmail();

  String getName();

  void setName(String name);

  boolean isEnable();

  void enable();
}
