package kr.lul.kobalttown.business.account.service;

import kr.lul.kobalttown.business.account.service.params.CreateAccountParams;
import kr.lul.kobalttown.domain.account.Account;
import kr.lul.kobalttown.util.EmailUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

import static java.lang.String.format;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

/**
 * @author justburrow
 * @since 2017. 9. 6.
 */
public abstract class AccountServiceTestUtils {
  private static final Logger log = LoggerFactory.getLogger(AccountServiceTestUtils.class);

  public static Account random(PasswordEncoder passwordEncoder, AccountService service) {
    return random(passwordEncoder, service, randomAlphanumeric(4, 30));
  }

  public static Account random(PasswordEncoder passwordEncoder, AccountService service, String password) {
    if (log.isTraceEnabled()) {
      log.trace(format("random args : passwordEncoder=%s, service=%s, password=%s",
                       passwordEncoder, service, password));
    }

    String email;
    do {
      email = EmailUtils.random();
    } while (Account.EMAIL_MAX_LENGTH < email.length());
    String name = randomAlphanumeric(2, Account.NAME_MAX_LENGTH + 1);

    Account account = service.create(new CreateAccountParams(email, name, passwordEncoder.encode(password)));

    if (log.isTraceEnabled()) {
      log.trace(format("random return : %s", account));
    }
    return account;
  }

  protected AccountServiceTestUtils() {
    throw new UnsupportedOperationException();
  }
}
