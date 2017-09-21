package kr.lul.kobalttown.jpa.account.repository;

import kr.lul.kobalttown.domain.account.AccountPrincipalType;
import kr.lul.kobalttown.jpa.account.entity.AccountEntity;
import kr.lul.kobalttown.jpa.account.entity.AccountPrincipalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * TODO 통합 레포지토리로 변경.
 *
 * @author justburrow
 * @since 2017. 8. 19.
 */
@NoRepositoryBean
public interface AccountPrincipalRepository<P extends AccountPrincipalEntity> extends JpaRepository<P, Long> {
  AccountPrincipalType supportType();

  AccountPrincipalEntity findOneByAccount(AccountEntity account);
}
