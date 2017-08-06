package kr.lul.kobalttown.web.security;

import kr.lul.kobalttown.jpa.entity.AccountEntity;
import kr.lul.kobalttown.jpa.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.lang.String.format;
import static java.util.Arrays.asList;

/**
 * @author justburrow
 * @since 2017. 8. 6.
 */
@Service class UserServiceImpl implements UserService {
  private static final Logger log = LoggerFactory.getLogger(UserService.class);

  @Autowired
  private AccountRepository accountRepository;

  @Override
  public UserDetails loadUserByUsername(String userKey) throws UsernameNotFoundException {
    if (log.isTraceEnabled()) {
      log.trace(format("args : userKey=%s", userKey));
    }
    AccountEntity account = this.accountRepository.findOneByEmail(userKey);
    if (null == account) {
      UsernameNotFoundException e = new UsernameNotFoundException(userKey);
      log.trace(format("account does not exist. userKey=%s", userKey), e);
      throw e;
    }

    User user = new User(account.getEmail(), account.getPassword(), asList(new SimpleGrantedAuthority("ROLE_USER")));
    if (log.isTraceEnabled()) {
      log.trace(format("return : user=%s", user));
    }
    return user;
  }
}
