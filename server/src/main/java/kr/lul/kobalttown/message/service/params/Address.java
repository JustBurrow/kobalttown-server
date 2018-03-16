package kr.lul.kobalttown.message.service.params;

/**
 * 메시지의 송/수신자의 주소 정보.
 *
 * @param <K> 주소 키 타입.
 * @author justburrow
 * @since 2017. 8. 31.
 */
public interface Address<K> {
  /**
   * @return
   */
  K key();

  /**
   * @return
   */
  String name();
}
