package kr.lul.kobalttown.service.account;

import it.ozimov.springboot.mail.model.Email;
import it.ozimov.springboot.mail.service.EmailService;
import it.ozimov.springboot.mail.service.exception.CannotSendEmailException;
import kr.lul.kobalttown.dao.account.AccountDao;
import kr.lul.kobalttown.domain.account.Account;
import kr.lul.kobalttown.domain.account.AccountPrincipal;
import kr.lul.kobalttown.jpa.entity.AccountEntity;
import kr.lul.kobalttown.jpa.entity.AccountPrincipalEmailEntity;
import kr.lul.kobalttown.service.account.params.CreateAccountParams;
import kr.lul.kobalttown.util.TimeProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static it.ozimov.springboot.mail.model.defaultimpl.DefaultEmail.builder;
import static java.lang.String.format;
import static java.util.Arrays.asList;
import static kr.lul.kobalttown.util.Asserts.*;

/**
 * @author justburrow
 * @since 2017. 8. 6.
 */
@Service class AccountServiceImpl implements AccountService {
  private static final Logger log = LoggerFactory.getLogger(AccountService.class);

  @Value("${kobalttown.lul.kr.email.account-activate-code.address}")
  private String accountActivateSender;
  @Value("${kobalttown.lul.kr.email.account-activate-code.name}")
  private String accountActivateName;
  @Value("${kobalttown.lul.kr.email.account-activate-code.subject}")
  private String accountActivateSubject;
  @Value("${kobalttown.lul.kr.email.account-activate-code.template}")
  private String accountActivateTemplate;
  @Value("${kobalttown.lul.kr.email.account-activate-code.host}")
  private String accountActivateHost;

  @Autowired
  private AccountDao   accountDao;
  @Autowired
  private EmailService emailService;
  @Autowired
  private TimeProvider timeProvider;

  @Override
  public Account create(CreateAccountParams params) {
    if (log.isTraceEnabled()) {
      log.trace(format("args : params=%s", params));
    }
    notNull(params, "params");
    hasLength(params.getEmail(), "params.email");
    notNull(params.getPassword(), "params.password");
    matches(params.getPassword(), "\\A\\$2a?\\$\\d\\d\\$[./0-9A-Za-z]{53}", "params.password");

    // save account
    Account account = new AccountEntity(params.getEmail(), params.getName());
    account = this.accountDao.insert(account);

    AccountPrincipal principal = new AccountPrincipalEmailEntity(account, params.getEmail(), params.getPassword());
    principal = this.accountDao.insert(principal);

    // send email
    try {
      Email email = builder()
          .from(new InternetAddress(accountActivateSender, accountActivateName))
          .to(asList(new InternetAddress(account.getEmail(), account.getName())))
          .subject(accountActivateSubject)
          .body("")
          .encoding("UTF-8")
          .build();

      Map<String, Object> model = new HashMap<>();
      model.put("account", account);
      model.put("host", accountActivateHost);
      model.put("code", UUID.randomUUID());

      MimeMessage mimeMessage = emailService.send(email, accountActivateTemplate, model);
      if (log.isTraceEnabled()) {
        log.trace(format("account activate code mail : mimeMessage=%s", mimeMessage));
      }
    } catch (UnsupportedEncodingException | CannotSendEmailException e) {
      log.error("fail to send email.", e);
      throw new RuntimeException(e);
    }

    if (log.isTraceEnabled()) {
      log.trace(format("result : account=%s", account));
    }
    return account;
  }
}
