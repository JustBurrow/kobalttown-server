package kr.lul.kobalttown.web.controller.root;

import kr.lul.kobalttown.service.account.AccountService;
import kr.lul.kobalttown.service.account.params.CreateAccountParams;
import kr.lul.kobalttown.web.controller.root.req.SignupReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import static java.lang.String.format;

/**
 * @author justburrow
 * @since 2017. 8. 6.
 */
@Controller class SignupControllerImpl implements SignupController {
  private static final Logger log = LoggerFactory.getLogger(SignupController.class);

  @Autowired
  private PasswordEncoder passwordEncoder;
  @Autowired
  private AccountService  accountService;

  @Override
  public String signup(SignupReq req, Model model) {
    if (log.isTraceEnabled()) {
      log.trace(format("args : req=%s, model=%s", req, model));
    }

    CreateAccountParams params =
        new CreateAccountParams(req.getEmail(), this.passwordEncoder.encode(req.getPassword()));
    this.accountService.create(params);

    return "redirect:/";
  }
}
