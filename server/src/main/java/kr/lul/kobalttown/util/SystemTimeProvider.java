package kr.lul.kobalttown.util;

import java.time.Instant;
import java.time.ZoneId;

/**
 * @author justburrow
 * @since 2017. 8. 26.
 */
public class SystemTimeProvider implements TimeProvider {
  @Override
  public Instant now() {
    return Instant.now();
  }

  @Override
  public ZoneId zoneId() {
    return ZoneId.systemDefault();
  }
}
