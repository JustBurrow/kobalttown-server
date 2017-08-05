package kr.lul.kobalttown.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

/**
 * @author justburrow
 * @since 2017. 8. 5.
 */
@Controller class IndexControllerImpl implements IndexController {
  @Override
  public String index(Model model) {
    return "index";
  }
}
