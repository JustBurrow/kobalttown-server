package kr.lul.kobalttown.ms.account.web.controller;

import kr.lul.kobalttown.ms.account.web.controller.req.*;
import kr.lul.kobalttown.support.security.AuthUser;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static kr.lul.kobalttown.domain.account.AccountCode.CODE_PATTERN;

/**
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
   * 계정 활성화 코드 재발급 폼.
   *
   * @param model
   * @return
   */
  @GetMapping("/activate")
  String issueAcivate(final Model model);

  /**
   * 계정 활성화 코드를 새로 생성해 발송한다.
   *
   * @param issueReq
   * @param binding
   * @param model
   * @return
   */
  @PostMapping("/activate")
  String issueAcivate(
      @ModelAttribute("issueReq") @Valid final IssueActivateCodeReq issueReq, final BindingResult binding,
      final Model model);

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
   * 계정 재설정 코드 요청.
   *
   * @param model
   * @return
   * @@since 2017. 9. 25.
   */
  @GetMapping("/reset")
  String issueResetCode(final Model model);

  /**
   * 계정을 재설정한다.
   *
   * @param issueReq
   * @param binding
   * @param model
   * @return
   * @@since 2017. 9. 25.
   */
  @PostMapping("/reset")
  String issueResetCode(
      @ModelAttribute("issueReq") @Valid final IssueAccountResetCodeReq issueReq, final BindingResult binding,
      final Model model);

  /**
   * 계정 재설정 폼.
   *
   * @param code
   * @param model
   * @return
   * @@since 2017. 9. 25.
   */
  @GetMapping("/reset/{code:" + CODE_PATTERN + "}")
  String reset(@PathVariable("code") final String code, final Model model);

  @PatchMapping("/reset/{code:" + CODE_PATTERN + "}")
  String reset(
      @PathVariable("code") String code, @ModelAttribute @Valid ResetAccountReq req, BindingResult binding,
      Model model);

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
