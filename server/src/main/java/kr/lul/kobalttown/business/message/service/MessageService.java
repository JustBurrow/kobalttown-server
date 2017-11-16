package kr.lul.kobalttown.business.message.service;

import kr.lul.kobalttown.business.message.exception.MessageException;
import kr.lul.kobalttown.business.message.service.params.TemplateMessageParams;

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
