package kr.lul.kobalttown.web.controller;

import kr.lul.kobalttown.web.security.AuthUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import static java.lang.String.format;

/**
 * @author justburrow
 * @since 2017. 8. 5.
 */
@Controller class IndexControllerImpl implements IndexController {
  private static final Logger log = LoggerFactory.getLogger(IndexController.class);

  @Override
  public String index(AuthUser user, final Model model) {
    if (log.isTraceEnabled()) {
      log.trace(format("args : model=%s", model));
    }

    if (null == user) {
      return "index";
    }

    model.addAttribute("currentUser", user);

    if (log.isTraceEnabled()) {
      log.trace(format("result : model=%s", model));
    }
    return "dashboard";
  }
}
