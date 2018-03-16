package kr.lul.kobalttown.account.configuration;

import kr.lul.kobalttown.account.jpa.AccountJpaAnchor;
import kr.lul.kobalttown.account.jpa.repository.AccountRepositoryPackageAnchor;
import kr.lul.kobalttown.support.jpa.JpaSupportPackageAnchor;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author justburrow
 * @since 2017. 8. 5.
 */
@Configuration
@EnableJpaRepositories(basePackageClasses = {AccountRepositoryPackageAnchor.class})
@EnableTransactionManagement
public class JpaConfiguation {
  @Bean
  @ConfigurationProperties(prefix = "spring.datasource")
  public DataSource dataSource() {
    return DataSourceBuilder.create().build();
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();

    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
    factory.setDataSource(dataSource());
    factory.setPackagesToScan(JpaSupportPackageAnchor.PACKAGE_NAME, AccountJpaAnchor.PACKAGE_NAME);
    factory.setJpaVendorAdapter(adapter);

    return factory;
  }

  @Bean
  public PlatformTransactionManager transactionManager() {
    return new JpaTransactionManager(entityManagerFactory().getObject());
  }

  @Bean
  public HibernateExceptionTranslator hibernateExceptionTranslator() {
    return new HibernateExceptionTranslator();
  }
}
