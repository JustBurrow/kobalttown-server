package kr.lul.kobalttown.support.web;

import kr.lul.kobalttown.support.security.AuthUser;
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
      log.trace(format("supportsParameter args : parameter=%s", parameter));
    }

    boolean result = AuthUser.class.equals(parameter.getParameterType());
    result &= "operator".equals(parameter.getParameterName());

    if (log.isTraceEnabled()) {
      log.trace(format("supportsParameter return : %b", result));
    }
    return result;
  }

  @Override
  public Object resolveArgument(MethodParameter param, ModelAndViewContainer mav, NativeWebRequest req, WebDataBinderFactory binder) throws Exception {
    if (log.isTraceEnabled()) {
      log.trace(format("resolveArgument args : param=%s, mav=%s, req=%s, binder=%s", param, mav, req, binder));
    }

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication instanceof AnonymousAuthenticationToken) {
      if (log.isTraceEnabled()) {
        log.trace("resolveArgument return : null");
      }
      return null;
    }

    Object principal = authentication.getPrincipal();
    if (log.isTraceEnabled()) {
      log.trace(format("principal=%s", principal));
    }

    if (principal instanceof AuthUser) {
      return principal;
    } else {
      if (log.isInfoEnabled()) {
        log.info(format("principal type : principal=%s, authentication=%s", principal, authentication));
      }
      return null;
    }
  }
}
