package kr.lul.kobalttown.util;

import static kr.lul.kobalttown.util.RandomUtil.R;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

/**
 * @author justburrow
 * @since 2017. 8. 5.
 */
public abstract class EmailUtils {
  public static String random() {
    StringBuffer sb = new StringBuffer();

    sb.append(randomAlphabetic(1))
      .append(randomAlphanumeric(1, 5));
    for (int i = 5; i > 0 && R.bool(); i--) {
      sb.append('.').append(randomAlphanumeric(2, 7));
    }
    sb.append('@').append(randomAlphabetic(1)).append(randomAlphanumeric(2, 5));
    for (int i = 5; i > 0 && R.bool(); i--) {
      sb.append('.').append(randomAlphanumeric(2, 5));
    }

    return sb.toString().toLowerCase();
  }

  protected EmailUtils() {
    throw new UnsupportedOperationException();
  }
}
