package kr.lul.kobalttown.ms.account.web.configuration;

import kr.lul.kobalttown.jpa.account.repository.AccountPrincipalEmailRepository;
import kr.lul.kobalttown.support.security.AuthUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static java.lang.String.format;

/**
 * @author justburrow
 * @since 2017. 8. 6.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
  private static final Logger log = LoggerFactory.getLogger(WebSecurityConfiguration.class);

  @Autowired
  private AccountPrincipalEmailRepository accountPrincipalEmailRepository;

  @Bean
  public PasswordEncoder passwordEncoder() {
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    if (log.isTraceEnabled()) {
      log.trace(format("passwordEncoder=%s", passwordEncoder));
    }
    return passwordEncoder;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    AuthUserService userService = new AuthUserService();
    userService.setAccountPrincipalEmailRepository(this.accountPrincipalEmailRepository);
    auth.userDetailsService(userService)
        .passwordEncoder(passwordEncoder());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    if (log.isTraceEnabled()) {
      log.trace("start http security configuration.");
    }

    http.formLogin()
        .usernameParameter("email")
        .passwordParameter("password")
        .permitAll();
    http.logout()
        .logoutSuccessUrl("/")
        .permitAll();
    http.authorizeRequests()
        .antMatchers("/", "/css/**", "/js/**").permitAll()
        .antMatchers("/signup/**", "/accounts/activate/*").anonymous()
        .anyRequest().authenticated();

    if (log.isTraceEnabled()) {
      log.trace(format("end http security configuration."));
    }
  }
}
