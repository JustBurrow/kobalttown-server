package kr.lul.kobalttown.service.message;

import kr.lul.kobalttown.service.message.exception.MessageException;
import kr.lul.kobalttown.service.message.params.TemplateMessageParams;

/**
 * @author justburrow
 * @since 2017. 8. 31.
 */
public interface MessageService {
  /**
   * 메시지를 전송한다.
   *
   * @param message
   */
  void send(TemplateMessageParams message) throws MessageException;
}
