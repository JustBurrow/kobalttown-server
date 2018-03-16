package kr.lul.kobalttown.support.security;

import kr.lul.kobalttown.account.domain.AccountPrincipal;
import kr.lul.kobalttown.account.jpa.repository.AccountPrincipalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static java.lang.String.format;
import static kr.lul.kobalttown.util.Asserts.notNull;

/**
 * @author justburrow
 * @since 2017. 8. 6.
 */
public class AuthServiceImpl implements AuthService {
  private static final Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);

  private AccountPrincipalRepository accountPrincipalRepository;

  public void setAccountPrincipalRepository(AccountPrincipalRepository repository) {
    if (log.isTraceEnabled()) {
      log.trace(String.format("setAccountPrincipalRepository args : repository=%s", repository));
    }
    notNull(repository, "repository");

    this.accountPrincipalRepository = repository;
  }

  @Override
  public AuthUser loadUserByUsername(String userKey) throws UsernameNotFoundException {
    if (log.isTraceEnabled()) {
      log.trace(format("args : userKey=%s", userKey));
    }

    AccountPrincipal principal = this.accountPrincipalRepository.findOneByPublicKey(userKey);
    if (null == principal) {
      UsernameNotFoundException e = new UsernameNotFoundException(userKey);
      log.info(format("account does not exist. userKey=%s", userKey), e);
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

  @Override
  public void logoutCurrent() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (!authentication.isAuthenticated()) {
      throw new IllegalStateException("not authenticated session.");
    }

    authentication.setAuthenticated(false);
  }
}
