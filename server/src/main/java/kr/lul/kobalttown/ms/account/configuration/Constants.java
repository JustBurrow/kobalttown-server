package kr.lul.kobalttown.ms.account.configuration;

/**
 * @author justburrow
 * @since 2017. 8. 5.
 */
public abstract class Constants {
  public abstract class Properties {
    public static final String PREFIX = "kobalttown.lul.kr";

    public static final String GROUP_RESOURCE = PREFIX + ".resource";
    public static final String CSS_LOCATION   = GROUP_RESOURCE + ".css.location";
    public static final String JS_LOCATION    = GROUP_RESOURCE + ".js.location";

    protected Properties() {
      throw new UnsupportedOperationException();
    }
  }

  protected Constants() {
    throw new UnsupportedOperationException();
  }
}
