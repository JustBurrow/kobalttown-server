package kr.lul.kobalttown.jpa.repository;

import kr.lul.kobalttown.domain.account.Account;
import kr.lul.kobalttown.jpa.entity.AccountActivateCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author justburrow
 * @since 2017. 9. 6.
 */
@Repository
public interface AccountActivateCodeRepository extends JpaRepository<AccountActivateCodeEntity, Long> {
  AccountActivateCodeEntity findOneByAccount(Account account);

  long countByCode(String code);

  AccountActivateCodeEntity findOneByCode(String code);

  boolean existsByAccount(Account account);

  void deleteByAccount(Account account);
}
