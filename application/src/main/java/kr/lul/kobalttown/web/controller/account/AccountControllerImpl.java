package kr.lul.kobalttown.web.controller.account;

import kr.lul.kobalttown.borderline.account.AccountBorderline;
import kr.lul.kobalttown.borderline.account.dto.AccountDto;
import kr.lul.kobalttown.service.DataNotExistException;
import kr.lul.kobalttown.service.account.IllegalAccountActivateCodeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author justburrow
 * @since 2017. 9. 13.
 */
@Controller class AccountControllerImpl implements AccountController {
  private static final Logger log = LoggerFactory.getLogger(AccountController.class);

  @Autowired
  private AccountBorderline accountBorderline;

  @Override
  public String activate(@PathVariable("code") final String code, final Model model) {
    if (log.isTraceEnabled()) {
      log.trace(String.format("activate args : code='%s', model=%s", code, model));
    }

    String template;
    try {
      AccountDto account = this.accountBorderline.activate(code);

      model.addAttribute("account", account);
      template = "accounts/activate-success";
    } catch (IllegalAccountActivateCodeException | DataNotExistException e) {
      model.addAttribute("error", e.getMessage());
      template = "accounts/activate-fail";
    }

    if (log.isTraceEnabled()) {
      log.trace(String.format("activate result : model=%s", model));
    }
    return template;
  }
}
