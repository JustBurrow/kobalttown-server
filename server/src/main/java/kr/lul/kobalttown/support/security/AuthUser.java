package kr.lul.kobalttown.support.security;

import kr.lul.kobalttown.account.domain.AccountPrincipal;
import kr.lul.kobalttown.account.domain.AccountSimple;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

import static java.util.Arrays.asList;

/**
 * @author justburrow
 * @since 2017. 8. 7.
 */
public class AuthUser extends AccountSimple implements UserDetails {
  private String  password;
  private boolean enabled;

  public AuthUser(AccountPrincipal principal) {
    super(principal.getAccount());

    this.password = principal.getPrivateKey();
    this.enabled = principal.getAccount().isEnable();
  }

  @Override
  public String getUsername() {
    return getEmail();
  }

  @Override
  public String getPassword() {
    return this.password;
  }

  @Override
  public boolean isEnabled() {
    return this.enabled;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return asList(new SimpleGrantedAuthority("ROLE_USER"));
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public String toString() {
    return new StringBuffer(AuthUser.class.getSimpleName())
        .append("{accoun=").append(toSimpleString())
        .append(", password=[ PROTECTED ], enabled=").append(this.enabled)
        .append('}').toString();
  }
}
