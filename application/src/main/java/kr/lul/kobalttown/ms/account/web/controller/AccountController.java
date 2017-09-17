package kr.lul.kobalttown.ms.account.web.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static kr.lul.kobalttown.domain.account.AccountActivateCode.CODE_PATTERN;

/**
 * @author justburrow
 * @since 2017. 9. 13.
 */
@RequestMapping("/accounts")
public interface AccountController {
  /**
   * 계정 활성화 코드를 사용해 계정을 활성화(<code>#enabled</code>를 {@code true}로 변경)하고 로그인 페이지로 안내한다.
   *
   * @param code
   * @param model
   * @return
   */
  @GetMapping("/activate/{code:" + CODE_PATTERN + "}")
  String activate(@PathVariable(name = "code") final String code, final Model model);
}
