package kr.lul.kobalttown.support.jpa.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.Instant;

import static java.time.Instant.ofEpochMilli;

/**
 * {@link Instant} 오브젝트를 <code>BIGINT</code> 컬럼에 저장할 수 있도록 UTC milliseconds로 상호 변환.
 *
 * @author Just Burrow
 * @since 2016. 8. 17.
 */
@Converter(autoApply = true)
public class InstantBigintAttributeConverter implements AttributeConverter<Instant, Long> {
  @Override
  public Long convertToDatabaseColumn(Instant instant) {
    if (null == instant) return null;
    else if (Instant.MIN.equals(instant)) return Long.MIN_VALUE;
    else if (Instant.MAX.equals(instant)) return Long.MAX_VALUE;
    else return instant.toEpochMilli();
  }

  @Override
  public Instant convertToEntityAttribute(Long utc) {
    if (null == utc) return null;
    else if (Long.MIN_VALUE == utc) return Instant.MIN;
    else if (Long.MAX_VALUE == utc) return Instant.MAX;
    else return ofEpochMilli(utc);
  }
}
