package kr.lul.kobalttown.web.security;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author justburrow
 * @since 2017. 8. 6.
 */
public interface AuthUserService extends UserDetailsService {
  @Override
  AuthUser loadUserByUsername(String s) throws UsernameNotFoundException;
}
