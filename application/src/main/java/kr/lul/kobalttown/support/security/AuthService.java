package kr.lul.kobalttown.support.security;

import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author justburrow
 * @since 2017. 9. 24.
 */
public interface AuthService extends UserDetailsService {
  /**
   * 현재 스레드의 유저를 로그아웃 시킨다.
   */
  void logoutCurrent();
}
