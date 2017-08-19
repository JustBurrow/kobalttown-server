package kr.lul.kobalttown.web.security;

import kr.lul.kobalttown.domain.account.AccountPrincipal;
import kr.lul.kobalttown.jpa.repository.AccountPrincipalEmailRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

/**
 * @author justburrow
 * @since 2017. 8. 6.
 */
@Service
public class AuthUserService implements UserDetailsService {
  private static final Logger log = LoggerFactory.getLogger(AuthUserService.class);

  @Autowired
  private AccountPrincipalEmailRepository accountPrincipalEmailRepository;

  @Override
  public AuthUser loadUserByUsername(String userKey) throws UsernameNotFoundException {
    if (log.isTraceEnabled()) {
      log.trace(format("args : userKey=%s", userKey));
    }

    AccountPrincipal principal = this.accountPrincipalEmailRepository.findOneByEmail(userKey);
    if (null == principal) {
      UsernameNotFoundException e = new UsernameNotFoundException(userKey);
      log.trace(format("account does not exist. userKey=%s", userKey), e);
      throw e;
    }

    AuthUser user;
    try {
      user = new AuthUser(principal);
    } catch (Exception e) {
      log.warn("Could not build AuthUser(UserDetails).", e);
      throw new UsernameNotFoundException("Could not build AuthUser(UserDetails).", e);
    }
    if (log.isTraceEnabled()) {
      log.trace(format("return : user=%s", user));
    }
    return user;
  }
}
