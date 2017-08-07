package kr.lul.kobalttown.web.security;

import kr.lul.kobalttown.domain.Account;
import kr.lul.kobalttown.jpa.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

/**
 * @author justburrow
 * @since 2017. 8. 6.
 */
@Service class AuthUserServiceImpl implements AuthUserService {
  private static final Logger log = LoggerFactory.getLogger(AuthUserService.class);

  @Autowired
  private AccountRepository accountRepository;

  @Override
  public AuthUser loadUserByUsername(String userKey) throws UsernameNotFoundException {
    if (log.isTraceEnabled()) {
      log.trace(format("args : userKey=%s", userKey));
    }
    Account account = this.accountRepository.findOneByEmail(userKey);
    if (null == account) {
      UsernameNotFoundException e = new UsernameNotFoundException(userKey);
      log.trace(format("account does not exist. userKey=%s", userKey), e);
      throw e;
    }

    AuthUser user = new AuthUser(account);
    if (log.isTraceEnabled()) {
      log.trace(format("return : user=%s", user));
    }
    return user;
  }
}
