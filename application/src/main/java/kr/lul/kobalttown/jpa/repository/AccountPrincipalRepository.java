package kr.lul.kobalttown.jpa.repository;

import kr.lul.kobalttown.domain.account.AccountPrincipalType;
import kr.lul.kobalttown.jpa.entity.AbstractAccountPrincipalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author justburrow
 * @since 2017. 8. 19.
 */
@NoRepositoryBean
public interface AccountPrincipalRepository<P extends AbstractAccountPrincipalEntity> extends JpaRepository<P, Long> {
  AccountPrincipalType supportType();
}
