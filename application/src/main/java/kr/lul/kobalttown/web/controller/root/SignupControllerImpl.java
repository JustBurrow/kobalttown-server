package kr.lul.kobalttown.web.controller.root;

import kr.lul.kobalttown.borderline.account.AccountBorderline;
import kr.lul.kobalttown.borderline.account.cmd.CreateAccountCmd;
import kr.lul.kobalttown.web.controller.root.req.SignupReq;
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
 * @since 2017. 8. 6.
 */
@Controller class SignupControllerImpl implements SignupController {
  private static final Logger log = LoggerFactory.getLogger(SignupController.class);

  @Autowired
  private PasswordEncoder   passwordEncoder;
  @Autowired
  private AccountBorderline accountBorderline;

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
                                                passwordEncoder.encode(req.getPassword()));
    accountBorderline.create(cmd);

    return "redirect:/";
  }
}
