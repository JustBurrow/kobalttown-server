package kr.lul.kobalttown.web.controller.root;

import kr.lul.kobalttown.borderline.account.dto.AccountDto;
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
  String index(final AccountDto currentAccount, final Model model);
}
