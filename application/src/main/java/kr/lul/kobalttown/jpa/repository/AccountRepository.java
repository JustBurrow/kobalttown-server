package kr.lul.kobalttown.jpa.repository;

import kr.lul.kobalttown.jpa.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author justburrow
 * @since 2017. 8. 5.
 */
@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
  /**
   * @param email
   * @return
   */
  AccountEntity findOneByEmail(String email);
}
