package kr.lul.kobalttown.service.account;

import it.ozimov.springboot.mail.service.EmailService;
import kr.lul.kobalttown.dao.account.AccountDao;
import kr.lul.kobalttown.domain.account.Account;
import kr.lul.kobalttown.domain.account.AccountPrincipal;
import kr.lul.kobalttown.jpa.entity.AccountEntity;
import kr.lul.kobalttown.jpa.entity.AccountPrincipalEmailEntity;
import kr.lul.kobalttown.service.account.params.CreateAccountParams;
import kr.lul.kobalttown.service.message.MessageService;
import kr.lul.kobalttown.service.message.exception.MessageException;
import kr.lul.kobalttown.service.message.params.EmailAddress;
import kr.lul.kobalttown.service.message.params.TemplateEmailMessageParams;
import kr.lul.kobalttown.service.message.params.TemplateMessageParams;
import kr.lul.kobalttown.util.Maps;
import kr.lul.kobalttown.util.TimeProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static java.lang.String.format;
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
  private AccountDao     accountDao;
  @Autowired
  private MessageService messageService;

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
    bcrypt(params.getPassword(), "params.password");

    // save account
    Account account = new AccountEntity(params.getEmail(), params.getName());
    account = this.accountDao.insert(account);

    AccountPrincipal principal = new AccountPrincipalEmailEntity(account, params.getEmail(), params.getPassword());
    principal = this.accountDao.insert(principal);

    // TODO account activate code 생성.
    UUID code = UUID.randomUUID();

    // send email
    try {
      TemplateMessageParams msg = new TemplateEmailMessageParams(
          new EmailAddress(accountActivateSender, accountActivateName),
          // new EmailAddress("just.burrow@lul.kr", account.getName()),
          new EmailAddress(account.getEmail(), account.getName()),
          accountActivateSubject, accountActivateTemplate,
          Maps.<String, Object>hashmap("account", account)
              .put("host", accountActivateHost).put("code", code).build());
      messageService.send(msg);
    } catch (MessageException e) {
      log.error(format("fail to send account activate code : account=%s", account), e);
    }

    if (log.isTraceEnabled()) {
      log.trace(format("result : account=%s", account));
    }
    return account;
  }
}
