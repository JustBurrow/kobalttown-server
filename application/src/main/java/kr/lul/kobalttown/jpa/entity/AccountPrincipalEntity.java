package kr.lul.kobalttown.jpa.entity;

import kr.lul.kobalttown.domain.account.Account;
import kr.lul.kobalttown.domain.account.AccountPrincipal;
import kr.lul.kobalttown.jpa.support.entity.AbstractCreatableEntity;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;

import static kr.lul.kobalttown.util.Asserts.hasLength;
import static kr.lul.kobalttown.util.Asserts.notNull;

/**
 * @author justburrow
 * @since 2017. 8. 18.
 */
@Entity(name = "AccountPrincipal")
@Table(name = "user_account_principal",
    indexes = {@Index(name = "IDX_ACCOUNT_PRICIPAL_PUBLIC_KEY", columnList = "public_key ASC")})
public class AccountPrincipalEntity extends AbstractCreatableEntity implements AccountPrincipal {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true, insertable = false, updatable = false)
  private long    id;
  @OneToOne(targetEntity = AccountEntity.class, mappedBy = "principal")
  private Account account;
  @Column(name = "public_key", nullable = false, updatable = false)
  private String  publicKey;
  @Column(name = "private_key", nullable = false, updatable = false)
  private String  privateKey;

  AccountPrincipalEntity() {
  }

  public AccountPrincipalEntity(Account account, String publicKey, String privateKey) {
    notNull(account, "account");
    hasLength(publicKey, "publicKey");
    hasLength(privateKey, "privateKey");

    this.account = account;
    this.publicKey = publicKey;
    this.privateKey = privateKey;
  }

  @PrePersist
  private void prePersist() throws Exception {
    setCreate(Instant.now());
  }

  @Override
  public long getId() {
    return this.id;
  }

  @Override
  public Account getAccount() {
    return this.account;
  }

  @Override
  public String getPublicKey() {
    return this.publicKey;
  }

  @Override
  public String getPrivateKey() {
    return this.privateKey;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  @Override
  public boolean equals(Object obj) {
    if (0L < this.id && null != obj && obj instanceof AccountPrincipalEntity) {
      return this.id == ((AccountPrincipalEntity) obj).id;
    } else {
      return false;
    }
  }

  @Override
  public String toString() {
    return new StringBuffer(AccountPrincipalEntity.class.getSimpleName())
        .append("{id=").append(this.id)
        .append(", publicKey='").append(this.publicKey).append("\', privateKey=[ PROTECTED ]")
        .append(", account=").append(this.account)
        .append(", create=").append(getCreate())
        .append('}').toString();
  }
}
