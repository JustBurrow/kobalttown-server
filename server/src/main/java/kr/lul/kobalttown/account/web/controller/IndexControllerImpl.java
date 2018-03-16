package kr.lul.kobalttown.account.web.controller;

import kr.lul.kobalttown.account.borderline.AccountBorderline;
import kr.lul.kobalttown.account.borderline.cmd.CreateAccountCmd;
import kr.lul.kobalttown.account.web.controller.req.SignupReq;
import kr.lul.kobalttown.support.security.AuthUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import static java.lang.String.format;

/**
 * @author justburrow
 * @since 2017. 8. 5.
 */
@SuppressWarnings("SpringAutowiredFieldsWarningInspection") @Controller
class IndexControllerImpl implements IndexController {
  private static final Logger log = LoggerFactory.getLogger(IndexController.class);

  @Autowired
  private PasswordEncoder   passwordEncoder;
  @Autowired
  private AccountBorderline accountBorderline;

  @Override
  public String index(final AuthUser operator, final Model model) {
    if (log.isTraceEnabled()) {
      log.trace(format("index args : operator=%s, model=%s", operator, model));
    }

    String template = null == operator
        ? indexGuest(model)
        : indexOperator(operator, model);

    if (log.isTraceEnabled()) {
      log.trace(format("index result : template=%s, model=%s", template, model));
    }
    return template;
  }

  private String indexGuest(final Model model) {
    return "index";
  }

  private String indexOperator(final AuthUser operator, final Model model) {
    return "dashboard";
  }

  @Override
  public String signup(SignupReq req, final BindingResult binding, final Model model) {
    if (log.isTraceEnabled()) {
      log.trace(format("args : req=%s, model=%s", req, model));
    }

    if (!req.getPassword().equals(req.getConfirm())) {
      binding.addError(new FieldError("signup", "confirm", "password confirm does not match."));
      return "index";
    }

    CreateAccountCmd cmd = new CreateAccountCmd(
        req.getEmail(), req.getName(), this.passwordEncoder.encode(req.getPassword()));
    this.accountBorderline.create(cmd);

    return "redirect:/";
  }
}
