package kr.lul.kobalttown.account.jpa.repository;

import kr.lul.kobalttown.account.domain.Account;
import kr.lul.kobalttown.account.domain.AccountCodeType;
import kr.lul.kobalttown.account.jpa.entity.AbstractAccountCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author justburrow
 * @since 2017. 9. 6.
 */
@Repository
public interface AccountCodeRepository extends JpaRepository<AbstractAccountCode, Long> {
  AbstractAccountCode findOneByAccount(Account account);

  long countByTypeAndCode(AccountCodeType type, String code);

  AbstractAccountCode findOneByTypeAndCode(AccountCodeType type, String code);

  boolean existsByTypeAndAccount(AccountCodeType type, Account account);

  void deleteByTypeAndAccount(AccountCodeType type, Account account);
}
