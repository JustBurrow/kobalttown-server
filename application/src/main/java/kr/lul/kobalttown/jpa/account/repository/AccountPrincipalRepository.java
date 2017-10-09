package kr.lul.kobalttown.jpa.account.repository;

import kr.lul.kobalttown.domain.account.Account;
import kr.lul.kobalttown.jpa.account.entity.AccountPrincipalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author justburrow
 * @since 2017. 8. 19.
 */
@Repository
public interface AccountPrincipalRepository extends JpaRepository<AccountPrincipalEntity, Long> {
  AccountPrincipalEntity findOneByAccount(Account account);

  AccountPrincipalEntity findOneByPublicKey(String publicKey);
}
