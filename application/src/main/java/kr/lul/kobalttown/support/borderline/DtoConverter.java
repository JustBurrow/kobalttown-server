package kr.lul.kobalttown.support.borderline;

import java.util.Set;

/**
 * DO를 DTO로 변환한다.
 *
 * @param <S> 도메인 오브젝트 타입
 * @author justburrow
 * @since 2017. 9. 23.
 */
public interface DtoConverter<S> {
  /**
   * 컨버터가 지원하는 DTO 타입.
   *
   * @return
   */
  Set<Class<?>> supportTypes();

  /**
   * DO => DTO
   *
   * @param source 도메인 오브젝트
   * @param type   DTO 타입
   * @param <D>    DTO
   * @return
   */
  <D> D convert(S source, Class<D> type);
}
