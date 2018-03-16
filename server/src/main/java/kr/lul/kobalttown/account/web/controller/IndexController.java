package kr.lul.kobalttown.account.web.controller;

import kr.lul.kobalttown.account.web.controller.req.SignupReq;
import kr.lul.kobalttown.support.security.AuthUser;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author justburrow
 * @since 2017. 8. 5.
 */
@RequestMapping
public interface IndexController {
  @GetMapping
  String index(final AuthUser operator, final Model model);

  @PostMapping("/signup")
  String signup(SignupReq req, final BindingResult binding, final Model model);
}
