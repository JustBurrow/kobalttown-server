package kr.lul.kobalttown.util;

import java.time.*;

/**
 * {@link java.time} 패키지 유틸리티.
 *
 * @author justburrow
 * @since 2017. 8. 26.
 */
public interface TimeProvider {
  /**
   * 현재 시각.
   *
   * @return
   */
  Instant now();

  /**
   * 기준 시간대.
   *
   * @return
   */
  ZoneId zoneId();

  default ZonedDateTime zonedDateTime() {
    return now().atZone(zoneId());
  }

  default ZoneOffset zoneOffset() {
    return zonedDateTime().getOffset();
  }

  default OffsetDateTime offsetDateTime() {
    return now().atZone(zoneId()).toOffsetDateTime();
  }

  default OffsetTime offsetTime() {
    return offsetDateTime().toOffsetTime();
  }

  default LocalDate localDate() {
    return zonedDateTime().toLocalDate();
  }

  default LocalTime localTime() {
    return zonedDateTime().toLocalTime();
  }

  default LocalDateTime localDateTime() {
    return zonedDateTime().toLocalDateTime();
  }
}
