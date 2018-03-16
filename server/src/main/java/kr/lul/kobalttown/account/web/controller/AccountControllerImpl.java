package kr.lul.kobalttown.account.web.controller;

import kr.lul.kobalttown.account.borderline.AccountBorderline;
import kr.lul.kobalttown.account.borderline.cmd.*;
import kr.lul.kobalttown.account.borderline.dto.AccountDto;
import kr.lul.kobalttown.account.domain.AccountCodeType;
import kr.lul.kobalttown.account.domain.AccountPrincipalType;
import kr.lul.kobalttown.account.exception.AccountStateException;
import kr.lul.kobalttown.account.exception.IllegalAccountActivateCodeException;
import kr.lul.kobalttown.account.web.controller.req.*;
import kr.lul.kobalttown.exception.DataNotExistException;
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
import static kr.lul.kobalttown.util.Asserts.notNull;

/**
 * TODO 에러 핸들러.
 *
 * @author justburrow
 * @since 2017. 9. 13.
 */
@SuppressWarnings("SpringAutowiredFieldsWarningInspection") @Controller
class AccountControllerImpl implements AccountController {
  private static final Logger log = LoggerFactory.getLogger(AccountController.class);

  @Autowired
  private AccountBorderline accountBorderline;
  @Autowired
  private AuthService       authService;

  private String doSettingsForm(final AuthUser user, final Model model) {
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
      log.trace(format("doSettingsForm result : model=%s", model));
    }
    return "accounts/settings";
  }

  private void doResetForm(String code, Model model) throws PropertyException {
    ReadAccountCodeCmd cmd = new ReadAccountCodeCmd();
    cmd.setType(AccountCodeType.RESET);
    cmd.setCode(code);

    AccountDto account = this.accountBorderline.read(cmd).val();
    if (log.isTraceEnabled()) {
      log.trace(format("account=%s", account));
    }

    model.addAttribute("code", code);
    if (!model.containsAttribute("resetReq")) {
      model.addAttribute("resetReq", new ResetAccountReq());
    }
  }

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
  public String issueAcivate(final Model model) {
    if (log.isTraceEnabled()) {
      log.trace(format("issueAcivate args : model=%s", model));
    }

    return "accounts/activate-issue";
  }

  @Override
  public String issueAcivate(final IssueActivateCodeReq issueReq, final BindingResult binding, final Model model) {
    if (log.isTraceEnabled()) {
      log.trace(format("issueAcivate args : issueReq=%s, binding=%s, model=%s", issueReq, binding, model));
    }
    notNull(issueReq, "issueReq");
    notNull(binding, "binding");
    notNull(model, "model");

    IssueAccountActivateCode cmd = new IssueAccountActivateCode();
    cmd.setEmail(issueReq.getEmail());
    AccountDto account;
    try {
      account = this.accountBorderline.issue(cmd).val();
    } catch (AccountStateException e) {
      return "accounts/activate-issue-fail";
    }

    return "accounts/activate-issued";
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
  public String issueResetCode(final Model model) {
    if (log.isTraceEnabled()) {
      log.trace(format("issueResetCode args : model=%s", model));
    }

    return "accounts/reset-issue";
  }

  @Override
  public String issueResetCode(IssueAccountResetCodeReq issueReq, BindingResult binding, Model model) {
    if (log.isTraceEnabled()) {
      log.trace(format("issueResetCode args : issueReq=%s, binding=%s, model=%s", issueReq, binding, model));
    }

    IssueAccountResetCodeCmd cmd = new IssueAccountResetCodeCmd();
    cmd.setEmail(issueReq.getEmail());
    this.accountBorderline.issue(cmd);

    return "accounts/reset-issue-success";
  }

  @Override
  public String reset(@PathVariable("code") final String code, final Model model) {
    if (log.isTraceEnabled()) {
      log.trace(format("reset args : code='%s', model=%s", code, model));
    }

    try {
      doResetForm(code, model);
      if (log.isTraceEnabled()) {
        log.trace(format("reset result : model=%s", model));
      }
      return "accounts/reset";
    } catch (Exception e) {
      log.warn("can not reset account.", e);
      return "accounts/reset-disable";
    }
  }

  @Override
  public String reset(@PathVariable("code") String code, ResetAccountReq req, BindingResult binding, Model model) {
    if (log.isTraceEnabled()) {
      log.trace(format("reset args : code='%s', req=%s, binding=%s, model=%s", code, req, binding, model));
    }

    if (null != req.getPassword() && !req.getPassword().equals(req.getConfirm())) {
      binding.addError(new FieldError("resetReq", "confirm", "재입력한 비밀번호가 다릅니다."));
    }

    if (!binding.hasErrors()) {
      ResetAccountCmd cmd = new ResetAccountCmd();
      cmd.setEmail(req.getEmail());
      cmd.setCode(code);
      cmd.setPassword(req.getPassword());

      try {
        AccountDto account = this.accountBorderline.reset(cmd).val();
      } catch (PropertyException e) {
        log.warn(format("fail to reset account : req=%s", req), e);

        for (CauseProperty p : e.getProperties()) {
          binding.addError(new FieldError("resetReq", p.getName(), p.getMessage()));
        }
      } catch (DataNotExistException e) {
        // TODO DataNotExistException 은 일반적인 경우의 예외이기 때문에 좀 더 특수한 예외를 사용할 필요가 있다.
        return "accounts/reset-fail";
      }
    }

    String template;
    if (binding.hasErrors()) {
      req.setPassword(null);
      req.setConfirm(null);

      try {
        doResetForm(code, model);
        template = "accounts/reset";
      } catch (PropertyException e) {
        log.warn(format("fail to reset account : code='%s', req=%s", code, req), e);
        template = "accounts/reset-disable";
      }
    } else {
      template = "accounts/reset-success";
    }

    if (log.isTraceEnabled()) {
      log.trace(format("reset result : binding=%s, model=%s", binding, model));
    }
    return template;
  }

  @Override
  public String settings(final AuthUser user, final Model model) {
    if (log.isTraceEnabled()) {
      log.trace(format("settings args : model=%s", model));
    }

    return doSettingsForm(user, model);
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
      return doSettingsForm(user, model);
    }

    UpdateAccountBasicCmd cmd = new UpdateAccountBasicCmd(user);
    cmd.setName(basicReq.getName());
    try {
      AccountDto account = this.accountBorderline.update(cmd).val();
    } catch (PropertyException e) {
      for (CauseProperty p : e.getProperties()) {
        binding.addError(new FieldError("basicReq", p.getName(), p.getMessage()));
      }
      return doSettingsForm(user, model);
    }

    if (log.isTraceEnabled()) {
      log.trace(format("setting result : model=%s", model));
    }
    return "redirect:/accounts";
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
      return doSettingsForm(user, model);
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
