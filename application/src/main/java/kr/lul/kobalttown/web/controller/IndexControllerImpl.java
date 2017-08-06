package kr.lul.kobalttown.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
  public String index(final Model model) {
    if (log.isTraceEnabled()) {
      log.trace(format("args : model=%s", model));
    }
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (log.isTraceEnabled()) {
      log.trace(format("authentication=%s", authentication));
    }
    if (authentication instanceof AnonymousAuthenticationToken) {
      return "index";
    }

    model.addAttribute("username", authentication.getName());

    return "dashboard";
  }
}
