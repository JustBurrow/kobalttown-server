package kr.lul.kobalttown.web.security;

import kr.lul.kobalttown.borderline.account.dto.AccountDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import static java.lang.String.format;

/**
 * @author justburrow
 * @since 2017. 8. 8.
 */
@Component
public class AuthUserArgumentResolver implements HandlerMethodArgumentResolver {
  private static final Logger log = LoggerFactory.getLogger(AuthUserArgumentResolver.class);

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    if (log.isTraceEnabled()) {
      log.trace(format("args : parameter=%s", parameter));
    }

    boolean result = AccountDto.class.equals(parameter.getParameterType());
    result &= "currentAccount".equals(parameter.getParameterName());

    if (log.isTraceEnabled()) {
      log.trace(format("result : support=%b", result));
    }
    return result;
  }

  @Override
  public Object resolveArgument(MethodParameter param, ModelAndViewContainer mav, NativeWebRequest req, WebDataBinderFactory binder) throws Exception {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication instanceof AnonymousAuthenticationToken) {
      if (log.isTraceEnabled()) {
        log.trace("return : null");
      }
      return null;
    }

    Object principal = authentication.getPrincipal();
    if (!(principal instanceof AuthUser)) {
      return null;
    }

    AuthUser   auth    = (AuthUser) principal;
    AccountDto account = new AccountDto(auth.getId(), auth.getUsername(), auth.isEnabled());
    if (log.isTraceEnabled()) {
      log.trace(format("result : principal=%s, account=%s", principal, account));
      log.trace(format("return : %s", account));
    }
    return account;
  }
}
