package kr.lul.kobalttown.business.account.service;

import kr.lul.kobalttown.business.account.dao.AccountDao;
import kr.lul.kobalttown.business.account.exception.AccountStateException;
import kr.lul.kobalttown.business.account.exception.IllegalAccountActivateCodeException;
import kr.lul.kobalttown.business.account.service.params.CreateAccountParams;
import kr.lul.kobalttown.business.account.service.params.IssueAccountResetCodeParams;
import kr.lul.kobalttown.business.account.service.params.UpdateAccountParams;
import kr.lul.kobalttown.business.account.service.params.UpdatePrincipalParams;
import kr.lul.kobalttown.business.exception.DataNotExistException;
import kr.lul.kobalttown.business.message.exception.MessageException;
import kr.lul.kobalttown.business.message.service.MessageService;
import kr.lul.kobalttown.business.message.service.params.EmailAddress;
import kr.lul.kobalttown.business.message.service.params.TemplateEmailMessageParams;
import kr.lul.kobalttown.business.message.service.params.TemplateMessageParams;
import kr.lul.kobalttown.domain.account.Account;
import kr.lul.kobalttown.domain.account.AccountCode;
import kr.lul.kobalttown.domain.account.AccountCodeReset;
import kr.lul.kobalttown.domain.account.AccountPrincipal;
import kr.lul.kobalttown.jpa.account.entity.AccountEntity;
import kr.lul.kobalttown.jpa.account.entity.AccountPrincipalEmailEntity;
import kr.lul.kobalttown.util.Maps;
import kr.lul.kobalttown.util.PropertyException;
import kr.lul.kobalttown.util.PropertyException.CauseProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static java.lang.String.format;
import static kr.lul.kobalttown.domain.account.AccountCode.CODE_PATTERN;
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


  @Value("${kobalttown.lul.kr.email.account-reset-code.address}")
  private String accountResetSender;
  @Value("${kobalttown.lul.kr.email.account-reset-code.name}")
  private String accountResetName;
  @Value("${kobalttown.lul.kr.email.account-reset-code.subject}")
  private String accountResetSubject;
  @Value("${kobalttown.lul.kr.email.account-reset-code.template}")
  private String accountResetTemplate;
  @Value("${kobalttown.lul.kr.email.account-reset-code.host}")
  private String accountResetHost;

  @Autowired
  private AccountCodeService accountCodeService;
  @Autowired
  private AccountDao         accountDao;
  @Autowired
  private MessageService     messageService;
  @Autowired
  private PasswordEncoder    passwordEncoder;

  @Override
  public Account create(CreateAccountParams params) {
    if (log.isTraceEnabled()) {
      log.trace(format("args : params=%s", params));
    }
    notNull(params, "params");

    // save account
    Account account = new AccountEntity(params.getEmail(), params.getName());
    account = this.accountDao.insert(account);

    // save login info
    AccountPrincipal principal = new AccountPrincipalEmailEntity(account, params.getEmail(), params.getPassword());
    principal = this.accountDao.insert(principal);

    // save activate code
    AccountCode activateCode = this.accountCodeService.createAcitivateCode(account);

    // send email
    try {
      TemplateMessageParams msg = new TemplateEmailMessageParams(
          new EmailAddress(this.accountActivateSender, this.accountActivateName),
          new EmailAddress(account.getEmail(), account.getName()),
          this.accountActivateSubject, this.accountActivateTemplate,
          Maps.<String, Object>hashmap("account", account)
              .put("host", this.accountActivateHost)
              .put("code", activateCode.getCode())
              .put("expire", activateCode.getExpire())
              .build());
      this.messageService.send(msg);
    } catch (MessageException e) {
      log.error(format("fail to send account activate code : account=%s", account), e);
    }

    if (log.isTraceEnabled()) {
      log.trace(format("result : account=%s", account));
    }
    return account;
  }

  @Override
  public Account read(long id) {
    if (log.isTraceEnabled()) {
      log.trace(format("read args : id=%d", id));
    }
    positive(id, "id");

    Account account = this.accountDao.select(id);

    if (log.isTraceEnabled()) {
      log.trace(format("read return : %s", account));
    }
    return account;
  }

  @Override
  public Account activate(String code) throws IllegalAccountActivateCodeException {
    if (log.isTraceEnabled()) {
      log.trace(format("activate args : code='%s'", code));
    }

    matches(code, CODE_PATTERN, "code");

    AccountCode activateCode = this.accountCodeService.readActivateCode(code);
    if (null == activateCode) {
      throw new DataNotExistException(format("account activate code : %s", code));
    } else if (activateCode.isUsed()) {
      throw new IllegalAccountActivateCodeException(code, format("account activate code : %s", activateCode));
    }

    Account account = activateCode.use();

    if (log.isTraceEnabled()) {
      log.trace(format("activate return : %s", account));
    }
    return account;
  }

  @Override
  public Account update(UpdateAccountParams params) throws PropertyException {
    if (log.isTraceEnabled()) {
      log.trace(format("update args : params=%s", params));
    }

    notNull(params, "params");

    Account account = params.getOperator();
    notNull(account, "cmd.operator");
    positive(account.getId(), "cmd.operator.id");

    if (!account.isEnable()) {
      throw new AccountStateException("account is not enable.");
    }

    if (null != params.getName() && !account.getName().equals(params.getName())) {
      if (null != this.accountDao.select(params.getName())) {
        if (log.isInfoEnabled()) {
          log.info(format("account name duplicated : name='%s'", params.getName()));
        }
        throw new PropertyException("name duplicated.", new CauseProperty("name", params.getName()));
      }
      account.setName(params.getName());
    }

    return account;
  }

  @Override
  public Account update(UpdatePrincipalParams params) {
    if (log.isTraceEnabled()) {
      log.trace(format("update args : params=%s", params));
    }

    notNull(params, "params");

    // validation
    AccountPrincipal old = this.accountDao.selectPrincipal(params.getType(), params.getOperator());
    if (null == old) {
      throw new DataNotExistException(
          format("principal does not exist : account=%s", params.getOperator().toSimpleString()));
      // } else if (!old.getAccount().isEnable()) { // TODO 테스트용 계정 유틸리티에 임의의 활성화된 계정을 만드는 유틸리티 추가.
      //   throw new AccountStateException("account is disable.");
    } else if (!old.getPublicKey().equals(params.getPublicKey())) {
      throw new IllegalArgumentException(format("public key does not match : expected=%s", params.getPublicKey()));
    } else if (!this.passwordEncoder.matches(params.getOldPrivateKey(), old.getPrivateKey())) {
      throw new IllegalArgumentException("private key does not match.");
    }

    // 삭제 & 저장
    this.accountDao.delete(old);
    AccountPrincipal principal = new AccountPrincipalEmailEntity(
        params.getOperator(), params.getPublicKey(), this.passwordEncoder.encode(params.getNewPrivateKey()));
    principal = this.accountDao.insert(principal);

    if (log.isTraceEnabled()) {
      log.trace(format("update result : principal=%s", principal));
    }
    return principal.getAccount();
  }

  /**
   * @param params
   * @return
   * @@since 2017. 9. 28.
   */
  @Override
  public Account issue(IssueAccountResetCodeParams params) {
    if (log.isTraceEnabled()) {
      log.trace(format("issue args : params=%s", params));
    }

    notNull(params, "params");

    Account account = this.accountDao.selectEmail(params.getEmail());
    if (null == account) {
      throw new DataNotExistException(format("no account for email : %s", params.getEmail()));
    }

    AccountCodeReset acr = this.accountCodeService.createReset(account);

    // send email
    try {
      TemplateMessageParams msg = new TemplateEmailMessageParams(
          new EmailAddress(this.accountResetSender, this.accountResetName),
          new EmailAddress(account.getEmail(), account.getName()),
          this.accountResetSubject, this.accountResetTemplate,
          Maps.<String, Object>hashmap("account", account)
              .put("host", this.accountResetHost)
              .put("code", acr.getCode())
              .put("expire", acr.getExpire())
              .build());
      this.messageService.send(msg);
    } catch (MessageException e) {
      log.error(format("fail to send account activate code : account=%s", account), e);
    }


    if (log.isTraceEnabled()) {
      log.trace(format("issue return : %s", account));
    }
    return account;
  }
}
