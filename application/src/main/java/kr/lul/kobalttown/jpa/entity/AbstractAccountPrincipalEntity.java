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
 * @since 2017. 8. 19.
 */
@Entity(name = "AccountPrincipal")
@Table(name = "user_account_principal",
    uniqueConstraints = {
        @UniqueConstraint(name = "UQ_ACCOUNT_PRINCIPAL_ACCOUNT", columnNames = {"account"}),
        @UniqueConstraint(name = "UQ_ACCOUNT_PRINCIPAL_PUBLIC_KEY", columnNames = {"principal_type", "public_key"})
    })
@DiscriminatorColumn(name = "principal_type", discriminatorType = DiscriminatorType.INTEGER)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class AbstractAccountPrincipalEntity extends AbstractCreatableEntity implements AccountPrincipal {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true, insertable = false, updatable = false)
  private long    id;
  @OneToOne(targetEntity = AccountEntity.class)
  @JoinColumn(name = "account",
      unique = true,
      nullable = false,
      updatable = false,
      foreignKey = @ForeignKey(name = "FK_ACCOUNT_PRINCIPAL_PK_ACCOUNT"),
      referencedColumnName = "id")
  private Account account;
  @Column(name = "public_key", nullable = false, updatable = false)
  private String  publicKey;
  @Column(name = "private_key", nullable = false, updatable = false)
  private String  privateKey;

  protected AbstractAccountPrincipalEntity() {
  }

  protected AbstractAccountPrincipalEntity(Account account, String publicKey, String privateKey) {
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
    if (0L < this.id && null != obj && obj instanceof AbstractAccountPrincipalEntity) {
      return this.id == ((AbstractAccountPrincipalEntity) obj).id;
    } else {
      return false;
    }
  }

  @Override
  public String toString() {
    return new StringBuffer(AccountPrincipalEmailEntity.class.getSimpleName())
        .append("{id=").append(this.id)
        .append(", type=").append(getType())
        .append(", publicKey='").append(this.publicKey).append("\', privateKey=[ PROTECTED ]")
        .append(", account=").append(this.account)
        .append(", create=").append(getCreate())
        .append('}').toString();
  }
}
