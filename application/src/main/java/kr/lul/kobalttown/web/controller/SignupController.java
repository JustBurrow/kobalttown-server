package kr.lul.kobalttown.web.controller;

import kr.lul.kobalttown.web.controller.req.SignupReq;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author justburrow
 * @since 2017. 8. 6.
 */
@RequestMapping("/signup")
public interface SignupController {
  @PostMapping
  String signup(@ModelAttribute SignupReq req, Model model);
}
