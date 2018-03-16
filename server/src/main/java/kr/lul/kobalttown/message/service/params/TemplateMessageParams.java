package kr.lul.kobalttown.message.service.params;

import java.util.Map;

/**
 * @param <K> 송신자/수신자 주소 키 타입.
 * @author justburrow
 * @since 2017. 8. 31.
 */
public interface TemplateMessageParams<K> {
  Address<K> from();

  Address<K> to();

  String title();

  String template();

  Map<String, Object> model();
}
