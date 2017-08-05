package kr.lul.kobalttown.domain;

/**
 * @author justburrow
 * @since 2017. 8. 5.
 */
public interface Account extends Updatable {
  long getId();

  String getEmail();

  String getPassword();

  void setPassword(String password);

  boolean isEnable();
}
