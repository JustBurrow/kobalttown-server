package kr.lul.kobalttown.ms.account.web.controller;

import kr.lul.kobalttown.business.account.exception.IllegalAccountActivateCodeException;
import kr.lul.kobalttown.business.exception.DataNotExistException;
import kr.lul.kobalttown.ms.account.borderline.AccountBorderline;
import kr.lul.kobalttown.ms.account.borderline.dto.AccountDto;
import kr.lul.kobalttown.ms.account.web.controller.req.EditBasicReq;
import kr.lul.kobalttown.ms.account.web.controller.req.EditPasswordReq;
import kr.lul.kobalttown.support.security.AuthUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

  @Override
  public String profile(final AuthUser user, final Model model) {
    if (log.isTraceEnabled()) {
      log.trace(String.format("profile args : user=%s, model=%s", user, model));
    }

    AccountDto account = this.accountBorderline.read(user.getId()).val();

    model.addAttribute("account", account);

    if (log.isTraceEnabled()) {
      log.trace(String.format("profile result : model=%s", model));
    }
    return "accounts/profile";
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
      log.trace(String.format("formSettings result : model=%s", model));
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

    if (binding.hasErrors()) {
      return formSettings(user, model);
    }

    return "redirect:/accounts";
  }
}
