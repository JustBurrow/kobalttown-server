package kr.lul.kobalttown.web.security;

import kr.lul.kobalttown.domain.account.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

import static java.util.Arrays.asList;
import static kr.lul.kobalttown.util.Asserts.notNull;

/**
 * @author justburrow
 * @since 2017. 8. 7.
 */
public class AuthUser implements UserDetails {
  private long    id;
  private String  username;
  private String  password;
  private boolean enabled;

  public AuthUser() {
  }

  public AuthUser(Account account) {
    notNull(account, "account");

    this.id = account.getId();
    this.username = account.getEmail();
    this.password = account.getPassword();
    this.enabled = account.isEnable();
  }

  public long getId() {
    return this.id;
  }

  @Override
  public String getUsername() {
    return this.username;
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
        .append("{id=").append(this.id)
        .append(", username='").append(this.username).append('\'')
        .append(", password=[ PROTECTED ]")
        .append(", enabled=").append(this.enabled)
        .append('}').toString();
  }
}
