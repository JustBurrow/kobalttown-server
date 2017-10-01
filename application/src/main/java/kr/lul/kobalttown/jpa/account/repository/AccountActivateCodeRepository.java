package kr.lul.kobalttown.jpa.account.repository;

import kr.lul.kobalttown.domain.account.Account;
import kr.lul.kobalttown.domain.account.AccountCodeType;
import kr.lul.kobalttown.jpa.account.entity.AbstractAccountCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author justburrow
 * @since 2017. 9. 6.
 */
@Repository
public interface AccountActivateCodeRepository extends JpaRepository<AbstractAccountCode, Long> {
  AbstractAccountCode findOneByAccount(Account account);

  long countByTypeAndCode(AccountCodeType type, String code);

  AbstractAccountCode findOneByTypeAndCode(AccountCodeType type, String code);

  boolean existsByTypeAndAccount(AccountCodeType type, Account account);

  void deleteByTypeAndAccount(AccountCodeType type, Account account);
}
