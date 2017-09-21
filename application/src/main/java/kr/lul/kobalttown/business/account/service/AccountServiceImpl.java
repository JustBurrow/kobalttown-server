package kr.lul.kobalttown.business.account.service;

import kr.lul.kobalttown.business.account.dao.AccountDao;
import kr.lul.kobalttown.business.account.service.params.CreateAccountParams;
import kr.lul.kobalttown.business.account.service.params.UpdatePrincipalParams;
import kr.lul.kobalttown.business.exception.DataNotExistException;
import kr.lul.kobalttown.business.message.exception.MessageException;
import kr.lul.kobalttown.business.message.service.MessageService;
import kr.lul.kobalttown.business.message.service.params.EmailAddress;
import kr.lul.kobalttown.business.message.service.params.TemplateEmailMessageParams;
import kr.lul.kobalttown.business.message.service.params.TemplateMessageParams;
import kr.lul.kobalttown.domain.account.Account;
import kr.lul.kobalttown.domain.account.AccountActivateCode;
import kr.lul.kobalttown.domain.account.AccountPrincipal;
import kr.lul.kobalttown.jpa.account.entity.AccountEntity;
import kr.lul.kobalttown.jpa.account.entity.AccountPrincipalEmailEntity;
import kr.lul.kobalttown.util.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    hasLength(params.getEmail(), "params.email");
    notNull(params.getPassword(), "params.password");
    bcrypt(params.getPassword(), "params.password");

    // save account
    Account account = new AccountEntity(params.getEmail(), params.getName());
    account = this.accountDao.insert(account);

    // save login info
    AccountPrincipal principal = new AccountPrincipalEmailEntity(account, params.getEmail(), params.getPassword());
    principal = this.accountDao.insert(principal);

    // save activate code
    AccountActivateCode activateCode = this.accountCodeService.createAcitivateCode(account);

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
  public Account activate(String code) throws IllegalAccountActivateCodeException {
    if (log.isTraceEnabled()) {
      log.trace(format("activate args : code='%s'", code));
    }

    AccountActivateCode activateCode = this.accountCodeService.readActivateCode(code);
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
  public Account update(UpdatePrincipalParams params) {
    if (log.isTraceEnabled()) {
      log.trace(format("update args : params=%s", params));
    }

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
    AccountPrincipal principal = new AccountPrincipalEmailEntity(params.getOperator(), params.getPublicKey(),
                                                                 params.getNewPrivateKey());
    principal = this.accountDao.insert(principal);

    if (log.isTraceEnabled()) {
      log.trace(String.format("update result : principal=%s", principal));
    }
    return principal.getAccount();
  }
}
