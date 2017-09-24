package kr.lul.kobalttown.ms.account.web.controller;

import kr.lul.kobalttown.ms.account.web.controller.req.EditBasicReq;
import kr.lul.kobalttown.ms.account.web.controller.req.EditPasswordReq;
import kr.lul.kobalttown.support.security.AuthUser;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static kr.lul.kobalttown.domain.account.AccountActivateCode.CODE_PATTERN;

/**
 * TODO 활성화 하지 않은 계정의 활성화 코드 신규 발급.
 *
 * @author justburrow
 * @since 2017. 9. 13.
 */
@RequestMapping("/accounts")
public interface AccountController {
  /**
   * 프로필 정보 보기.
   *
   * @param user
   * @param model
   * @return
   */
  @GetMapping
  String profile(final AuthUser user, final Model model);

  /**
   * 계정 활성화 코드를 사용해 계정을 활성화(<code>#enabled</code>를 {@code true}로 변경)하고 로그인 페이지로 안내한다.
   *
   * @param code
   * @param model
   * @return
   */
  @GetMapping("/activate/{code:" + CODE_PATTERN + "}")
  String activate(@PathVariable(name = "code") final String code, final Model model);

  /**
   * 기본 설정 변경하기.
   *
   * @param basicReq
   * @param model
   * @return
   */
  @PatchMapping("/settings")
  String settings(final AuthUser user, final EditBasicReq basicReq, final BindingResult binding, final Model model);

  /**
   * 설정 변경 폼 보기.
   *
   * @param model
   * @return
   */
  @GetMapping("/settings")
  String settings(final AuthUser user, final Model model);

  /**
   * 비밀번호 변경하기.
   *
   * @param passwordReq
   * @param model
   * @return
   */
  @PatchMapping("/settings/password")
  String password(
      final AuthUser user, final EditPasswordReq passwordReq, final BindingResult binding, final Model model);
}
