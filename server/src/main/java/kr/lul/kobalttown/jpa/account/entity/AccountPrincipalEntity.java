package kr.lul.kobalttown.jpa.account.entity;

import kr.lul.kobalttown.domain.account.Account;
import kr.lul.kobalttown.domain.account.AccountPrincipal;
import kr.lul.kobalttown.support.jpa.entity.AbstractCreatableEntity;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;

import static kr.lul.kobalttown.util.Asserts.*;

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
public abstract class AccountPrincipalEntity extends AbstractCreatableEntity implements AccountPrincipal {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true, insertable = false, updatable = false)
  private   long    id;
  @OneToOne(targetEntity = AccountEntity.class)
  @JoinColumn(name = "account",
      unique = true,
      nullable = false,
      updatable = false,
      foreignKey = @ForeignKey(name = "FK_ACCOUNT_PRINCIPAL_PK_ACCOUNT"),
      referencedColumnName = "id")
  private   Account account;
  @Column(name = "public_key", nullable = false, updatable = false)
  protected String  publicKey;
  @Column(name = "private_key", nullable = false, updatable = false)
  protected String  privateKey;

  protected AccountPrincipalEntity() {
  }

  protected AccountPrincipalEntity(Account account, String publicKey, String privateKey) {
    notNull(account, "account");
    hasLength(publicKey, "publicKey");
    bcrypt(privateKey, "privateKey");

    this.account = account;
    setPublicKey(publicKey);
    setPrivateKey(privateKey);
  }

  @PrePersist
  private void prePersist() throws Exception {
    setCreate(Instant.now());
  }

  abstract void setPublicKey(String publicKey);

  abstract void setPrivateKey(String privateKey);

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
    return new StringBuffer(AccountPrincipalEmailEntity.class.getSimpleName())
        .append("{id=").append(this.id)
        .append(", type=").append(getType())
        .append(", publicKey='").append(this.publicKey).append('\'')
        .append(", privateKey=[ PROTECTED ], account=").append(this.account)
        .append(", create=").append(getCreate())
        .append('}').toString();
  }
}
