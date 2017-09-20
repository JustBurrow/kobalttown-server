package kr.lul.kobalttown.ms.account.web;

import kr.lul.kobalttown.business.account.AccountBusinessAnchor;
import kr.lul.kobalttown.business.configuration.BusinessConfigurationPackageAnchor;
import kr.lul.kobalttown.business.message.MessageBusinessAnchor;
import kr.lul.kobalttown.ms.account.AccountMicroserviceAnchor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import static java.lang.String.format;

/**
 * @author justburrow
 * @since 2017. 8. 5.
 */
@SpringBootApplication(scanBasePackageClasses = {
    BusinessConfigurationPackageAnchor.class,
    MessageBusinessAnchor.class,
    AccountBusinessAnchor.class,
    AccountMicroserviceAnchor.class
})
public class AccountMicroserviceLuncher {
  private static final Logger log = LoggerFactory.getLogger(AccountMicroserviceLuncher.class);

  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(AccountMicroserviceLuncher.class, args);

    if (log.isTraceEnabled()) {
      for (String name : context.getBeanDefinitionNames()) {
        log.trace(format("bean : %s=%s", name, context.getBean(name)));
      }
    }
  }
}
