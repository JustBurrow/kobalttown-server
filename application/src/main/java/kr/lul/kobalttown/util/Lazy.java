package kr.lul.kobalttown.util;

/**
 * {@link #val()} 실행 시점으로 로직 연기하기. 트랜잭션, 엔티티 관리자 등, 스프링프레임워크에서 자동으로 설정하는 값을 얻어내야 할 때 사용한다.
 * 주로 lambda 형태로 사용.
 *
 * @author justburrow
 * @since 2017. 9. 23.
 */
public interface Lazy<V> {
  V val();
}
