package kr.lul.kobalttown.ms.account.web.controller;

import kr.lul.kobalttown.business.account.exception.IllegalAccountActivateCodeException;
import kr.lul.kobalttown.business.exception.DataNotExistException;
import kr.lul.kobalttown.domain.account.AccountPrincipalType;
import kr.lul.kobalttown.ms.account.borderline.AccountBorderline;
import kr.lul.kobalttown.ms.account.borderline.cmd.UpdateAccountBasicCmd;
import kr.lul.kobalttown.ms.account.borderline.cmd.UpdatePasswordCmd;
import kr.lul.kobalttown.ms.account.borderline.dto.AccountDto;
import kr.lul.kobalttown.ms.account.web.controller.req.EditBasicReq;
import kr.lul.kobalttown.ms.account.web.controller.req.EditPasswordReq;
import kr.lul.kobalttown.ms.account.web.controller.req.IssueActivateCodeReq;
import kr.lul.kobalttown.ms.account.web.controller.req.ResetAccountReq;
import kr.lul.kobalttown.support.security.AuthService;
import kr.lul.kobalttown.support.security.AuthUser;
import kr.lul.kobalttown.util.PropertyException;
import kr.lul.kobalttown.util.PropertyException.CauseProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;

import static java.lang.String.format;

/**
 * @author justburrow
 * @since 2017. 9. 13.
 */
@Controller class AccountControllerImpl implements AccountController {
  private static final Logger log = LoggerFactory.getLogger(AccountController.class);

  @Autowired
  private AccountBorderline accountBorderline;
  @Autowired
  private AuthService       authService;

  @Override
  public String profile(final AuthUser user, final Model model) {
    if (log.isTraceEnabled()) {
      log.trace(format("profile args : user=%s, model=%s", user, model));
    }

    AccountDto account = this.accountBorderline.read(user.getId()).val();

    model.addAttribute("account", account);

    if (log.isTraceEnabled()) {
      log.trace(format("profile result : model=%s", model));
    }
    return "accounts/profile";
  }

  @Override
  public String issue(final Model model) {
    if (log.isTraceEnabled()) {
      log.trace(format("issue args : model=%s", model));
    }
    // TODO
    return "accounts/activate-issue";
  }

  @Override
  public String issue(
      @ModelAttribute("issueReq") @Valid final IssueActivateCodeReq issueReq, final BindingResult binding,
      final Model model) {
    if (log.isTraceEnabled()) {
      log.trace(format("issue args : issueReq=%s, binding=%s, model=%s", issueReq, binding, model));
    }

    // TODO

    return null;
  }

  @Override
  public String activate(@PathVariable("code") final String code, final Model model) {
    if (log.isTraceEnabled()) {
      log.trace(format("activate args : code='%s', model=%s", code, model));
    }

    String template;
    try {
      AccountDto account = this.accountBorderline.activate(code).val();

      model.addAttribute("account", account);
      template = "accounts/activate-success";
    } catch (IllegalAccountActivateCodeException | DataNotExistException e) {
      model.addAttribute("error", e.getMessage());
      template = "accounts/activate-fail";
    }

    if (log.isTraceEnabled()) {
      log.trace(format("activate result : model=%s", model));
    }
    return template;
  }

  @Override
  public String reset(final Model model) {
    if (log.isTraceEnabled()) {
      log.trace(format("reset args : model=%s", model));
    }

    // TODO
    return "accounts/reset";
  }

  @Override
  public String reset(
      @ModelAttribute("resetReq") @Valid final ResetAccountReq resetReq, final BindingResult binding,
      final Model model) {
    if (log.isTraceEnabled()) {
      log.trace(format("reset args : resetReq=%s, binding=%s, model=%s", resetReq, binding, model));
    }
    // TODO
    return null;
  }

  @Override
  public String settings(final AuthUser user, final Model model) {
    if (log.isTraceEnabled()) {
      log.trace(format("settings args : model=%s", model));
    }

    return formSettings(user, model);
  }

  @Override
  public String settings(
      final AuthUser user,
      @ModelAttribute("basicReq") @Valid final EditBasicReq basicReq, final BindingResult binding,
      final Model model) {
    if (log.isTraceEnabled()) {
      log.trace(format("settings args : user=%s, basicReq=%s, binding=%s, model=%s", user, basicReq, binding, model));
    }

    if (binding.hasErrors()) {
      return formSettings(user, model);
    }

    UpdateAccountBasicCmd cmd = new UpdateAccountBasicCmd(user);
    cmd.setName(basicReq.getName());
    try {
      AccountDto account = this.accountBorderline.update(cmd).val();
    } catch (PropertyException e) {
      for (CauseProperty p : e.getProperties()) {
        binding.addError(new FieldError("basicReq", p.getName(), p.getMessage()));
      }
      return formSettings(user, model);
    }

    if (log.isTraceEnabled()) {
      log.trace(format("setting result : model=%s", model));
    }
    return "redirect:/accounts";
  }

  private String formSettings(final AuthUser user, final Model model) {
    AccountDto account = this.accountBorderline.read(user.getId()).val();

    EditBasicReq basicReq;
    if (model.containsAttribute("basicReq")) {
      basicReq = (EditBasicReq) model.asMap().get("basicReq");
      basicReq.setName(account.getName());
    } else {
      basicReq = new EditBasicReq();
      basicReq.setName(account.getName());
      model.addAttribute("basicReq", basicReq);
    }

    if (model.containsAttribute("passwordReq")) {
      ((EditPasswordReq) model.asMap().get("passwordReq")).init();
    } else {
      model.addAttribute("passwordReq", new EditPasswordReq());
    }

    if (log.isTraceEnabled()) {
      log.trace(format("formSettings result : model=%s", model));
    }
    return "accounts/settings";
  }

  @Override
  public String password(
      final AuthUser user,
      @ModelAttribute("passwordReq") @Valid final EditPasswordReq passwordReq, final BindingResult binding,
      final Model model) {
    if (log.isTraceEnabled()) {
      log.trace(format("password args : user=%s, passwordReq=%s, binding=%s, model=%s",
                       user, passwordReq, binding, model));
    }

    if (!passwordReq.getPassword().equals(passwordReq.getConfirm())) {
      binding.addError(new FieldError("passwordReq", "confirm", "비밀번호가 일치하지 않습니다."));
    }
    if (binding.hasErrors()) {
      return formSettings(user, model);
    }

    UpdatePasswordCmd cmd = new UpdatePasswordCmd(user);
    cmd.setType(AccountPrincipalType.EMAIL_PASSWORD);
    cmd.setPublickKey(user.getEmail());
    cmd.setCurrent(passwordReq.getCurrent());
    cmd.setPassword(passwordReq.getPassword());

    AccountDto account = this.accountBorderline.update(cmd).val();

    this.authService.logoutCurrent();

    if (log.isTraceEnabled()) {
      log.trace(format("password result : account=%s, model=%s", account, model));
    }
    return "redirect:/";
  }
}
