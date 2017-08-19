package kr.lul.kobalttown.jpa.repository;

import kr.lul.kobalttown.domain.account.AccountPrincipalType;
import kr.lul.kobalttown.jpa.entity.AccountPrincipalEmailEntity;
import org.springframework.stereotype.Repository;

/**
 * @author justburrow
 * @since 2017. 8. 18.
 */
@Repository
public interface AccountPrincipalEmailRepository extends AccountPrincipalRepository<AccountPrincipalEmailEntity> {
  @Override
  default AccountPrincipalType supportType() {
    return AccountPrincipalType.EMAIL_PASSWORD;
  }

  /**
   * @param publicKey
   * @return
   */
  AccountPrincipalEmailEntity findOneByPublicKey(String publicKey);

  /**
   * @param email
   * @return
   */
  default AccountPrincipalEmailEntity findOneByEmail(String email) {
    return findOneByPublicKey(email);
  }
}
