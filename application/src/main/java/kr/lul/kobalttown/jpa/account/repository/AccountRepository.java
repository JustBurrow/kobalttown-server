package kr.lul.kobalttown.jpa.account.repository;

import kr.lul.kobalttown.jpa.account.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author justburrow
 * @since 2017. 8. 5.
 */
@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
  /**
   * @param name
   * @return
   * @@since 2017. 9. 24.
   */
  AccountEntity findOneByName(String name);

  /**
   * @param email
   * @return
   * @@since 2017. 9. 28.
   */
  AccountEntity findOneByEmail(String email);
}
