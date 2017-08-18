package kr.lul.kobalttown.jpa.repository;

import kr.lul.kobalttown.jpa.entity.AccountPrincipalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author justburrow
 * @since 2017. 8. 18.
 */
@Repository
public interface AccountPrincipalRepository extends JpaRepository<AccountPrincipalEntity, Long> {
  /**
   * @param publicKey
   * @return
   */
  AccountPrincipalEntity findOneByPublicKey(String publicKey);
}
