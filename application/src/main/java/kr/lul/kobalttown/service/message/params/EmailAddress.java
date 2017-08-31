package kr.lul.kobalttown.service.message.params;

import static kr.lul.kobalttown.util.Asserts.hasLength;

/**
 * @author justburrow
 * @since 2017. 8. 31.
 */
public class EmailAddress implements Address<String> {
  private String email;
  private String name;

  public EmailAddress(String email, String name) {
    hasLength(email, "email");
    hasLength(name, "name");

    this.email = email;
    this.name = name;
  }

  /**
   * E-Mail 주소.
   *
   * @return
   */
  public String email() {
    return key();
  }

  @Override
  public String key() {
    return email;
  }

  @Override
  public String name() {
    return name;
  }

  @Override
  public String toString() {
    return String.format("%s<%s>", name, email);
  }
}
