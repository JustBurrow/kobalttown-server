package kr.lul.kobalttown.web.configuration;

import kr.lul.kobalttown.web.security.AuthUserService;
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
  private AuthUserService authUserService;

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
    auth.userDetailsService(this.authUserService)
        .passwordEncoder(passwordEncoder());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    if (log.isTraceEnabled()) {
      log.trace("start http security configuration.");
    }
    http.authorizeRequests()
        .antMatchers("/", "/css/**", "/js/**").permitAll()
        .antMatchers("/signup/**").anonymous()
        .anyRequest().authenticated();
    http.formLogin()
        .loginPage("/login")
        .usernameParameter("email")
        .passwordParameter("password")
        .defaultSuccessUrl("/")
        .failureUrl("/")
        .permitAll();
    http.logout()
        .logoutSuccessUrl("/")
        .permitAll();
    if (log.isTraceEnabled()) {
      log.trace(format("end http security configuration."));
    }
  }
}
