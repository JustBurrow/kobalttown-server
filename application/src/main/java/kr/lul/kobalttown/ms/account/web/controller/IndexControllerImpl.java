package kr.lul.kobalttown.ms.account.web.controller;

import kr.lul.kobalttown.ms.account.borderline.AccountBorderline;
import kr.lul.kobalttown.ms.account.borderline.cmd.CreateAccountCmd;
import kr.lul.kobalttown.ms.account.borderline.dto.AccountDto;
import kr.lul.kobalttown.ms.account.web.controller.req.SignupReq;
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
@Controller class IndexControllerImpl implements IndexController {
  private static final Logger log = LoggerFactory.getLogger(IndexController.class);

  @Autowired
  private PasswordEncoder   passwordEncoder;
  @Autowired
  private AccountBorderline accountBorderline;

  @Override
  public String index(final AccountDto currentAccount, final Model model) {
    if (log.isTraceEnabled()) {
      log.trace(format("args : currentAccount=%s, model=%s", currentAccount, model));
    }

    if (null == currentAccount) {
      return "index";
    }

    model.addAttribute("currentAccount", currentAccount);
    if (log.isTraceEnabled()) {
      log.trace(format("result : model=%s", model));
    }
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

    CreateAccountCmd cmd = new CreateAccountCmd(req.getEmail(), req.getName(),
                                                this.passwordEncoder.encode(req.getPassword()));
    this.accountBorderline.create(cmd);

    return "redirect:/";
  }
}
