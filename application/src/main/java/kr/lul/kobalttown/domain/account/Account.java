package kr.lul.kobalttown.domain.account;

/**
 * @author justburrow
 * @since 2017. 8. 5.
 */
public interface Account extends Updatable {
  long getId();

  String getEmail();

  String getName();

  boolean isEnable();
}
