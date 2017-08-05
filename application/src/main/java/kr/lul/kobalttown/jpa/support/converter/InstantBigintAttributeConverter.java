package kr.lul.kobalttown.jpa.support.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.Instant;

/**
 * {@link Instant} 오브젝트를 <code>BIGINT(20)</code> 컬럼에 저장할 수 있도록 UTC milliseconds로 상호 변환.
 *
 * @author Just Burrow
 * @since 2016. 8. 17.
 */
@Converter(autoApply = true)
public class InstantBigintAttributeConverter implements AttributeConverter<Instant, Long> {
  @Override
  public Long convertToDatabaseColumn(Instant instant) {
    return null == instant ? null : instant.toEpochMilli();
  }

  @Override
  public Instant convertToEntityAttribute(Long utc) {
    return null == utc ? null : Instant.ofEpochMilli(utc);
  }
}
