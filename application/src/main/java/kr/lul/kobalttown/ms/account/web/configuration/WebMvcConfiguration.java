package kr.lul.kobalttown.ms.account.web.configuration;

import kr.lul.kobalttown.ms.account.configuration.Constants.Properties;
import kr.lul.kobalttown.ms.support.web.AuthUserArgumentResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * @author justburrow
 * @since 2017. 8. 5.
 */
@Configuration
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {
  @Value("${" + Properties.CSS_LOCATION + ":#{null}}")
  private String cssLocation;
  @Value("${" + Properties.JS_LOCATION + ":#{null}}")
  private String jsLocation;

  @Bean
  public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
    return new HiddenHttpMethodFilter();
  }

  /**
   * 프로젝트 로컬 레포지토리 혹은 JAR 파일 외부의 리소스를 사용하기 위해 핸들러를 추가한다.
   * <p>
   * 기본적으로 <code>CSS</code>, <code>JS</code>는 별도의 서버에서 처리하는 설계이지만,
   * 해당 설정이 없는 개발 환경 등에서 사용하기 위한 기능이다.
   *
   * @param registry
   */
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    // 외부 CSS 디렉토리가 설정되어 있을 때만 사용한다.
    // TODO 설정 경로가 심볼릭 링크일 경우의 처리
    if (null != this.cssLocation) {
      registry.addResourceHandler("/css/**").addResourceLocations(this.cssLocation);
    }

    // 외부 JS 디렉토리가 설정되어 있을 때만 사용한다.
    // TODO 설정 경로가 심볼릭 링크일 경우의 처리
    if (null != this.jsLocation) {
      registry.addResourceHandler("/js/**").addResourceLocations(this.jsLocation);
    }
  }

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
    resolvers.add(new AuthUserArgumentResolver());
  }
}
