package kr.lul.kobalttown.web.security;

import kr.lul.kobalttown.domain.account.AccountPrincipal;
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
  private String  email;
  private String  name;
  private String  password;
  private boolean enabled;

  public AuthUser() {
  }

  public AuthUser(AccountPrincipal principal) {
    notNull(principal, "principal");

    this.id = principal.getAccount().getId();
    this.email = principal.getPublicKey();
    name = principal.getAccount().getName();
    this.password = principal.getPrivateKey();
    this.enabled = principal.getAccount().isEnable();
  }

  public long getId() {
    return this.id;
  }

  public String getEmail() {
    return email;
  }

  public String getName() {
    return name;
  }

  @Override
  public String getUsername() {
    return this.email;
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
        .append(", username='").append(this.getUsername()).append('\'')
        .append(", name='").append(name).append('\'')
        .append(", password=[ PROTECTED ]")
        .append(", enabled=").append(this.enabled)
        .append('}').toString();
  }
}
