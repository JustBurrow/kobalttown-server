package kr.lul.kobalttown.web.controller.root;

import kr.lul.kobalttown.web.controller.root.req.SignupReq;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author justburrow
 * @since 2017. 8. 6.
 */
@RequestMapping("/signup")
public interface SignupController {
  @PostMapping
  String signup(SignupReq req, final BindingResult binding, final Model model);
}
