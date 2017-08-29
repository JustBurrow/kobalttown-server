package kr.lul.kobalttown.web.controller.root;

import kr.lul.kobalttown.borderline.account.AccountBorderline;
import kr.lul.kobalttown.borderline.account.dto.AccountDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import static java.lang.String.format;

/**
 * @author justburrow
 * @since 2017. 8. 5.
 */
@Controller class IndexControllerImpl implements IndexController {
  private static final Logger log = LoggerFactory.getLogger(IndexController.class);

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

    if (log.isTraceEnabled()) {
      log.trace(format("result : model=%s", model));
    }
    return "dashboard";
  }
}
