package kr.lul.kobalttown.web.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author justburrow
 * @since 2017. 8. 5.
 */
@RequestMapping
public interface IndexController {
  @GetMapping
  String index(Model model);
}
