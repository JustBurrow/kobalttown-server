package kr.lul.kobalttown.jpa.account.entity;

import kr.lul.kobalttown.domain.account.Account;
import kr.lul.kobalttown.domain.account.AccountCode;
import kr.lul.kobalttown.domain.account.AccountCodeType;
import kr.lul.kobalttown.support.jpa.entity.AbstractUpdatableEntity;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;

import static java.lang.String.format;
import static kr.lul.kobalttown.util.Asserts.*;

/**
 * @author justburrow
 * @since 2017. 9. 6.
 */
@Entity(name = "AccountCode")
@Table(name = "user_account_code",
    uniqueConstraints = {@UniqueConstraint(name = "UQ_ACCOUNT_CODE", columnNames = {"type", "code"})},
    indexes = {@Index(name = "FK_ACCOUNT_CODE_PK_ACCOUNT", columnList = "account")}
)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.INTEGER)
public abstract class AbstractAccountCode extends AbstractUpdatableEntity implements AccountCode {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true, insertable = false, updatable = false)
  private long            id;
  @OneToOne(targetEntity = AccountEntity.class, fetch = FetchType.EAGER)
  @JoinColumn(name = "account",
      nullable = false,
      updatable = false,
      foreignKey = @ForeignKey(name = "FK_ACCOUNT_ACTIVATE_CODE_PK_ACCOUNT"),
      referencedColumnName = "id")
  private Account         account;
  @Column(name = "type", nullable = false, insertable = false, updatable = false)
  @Enumerated(EnumType.ORDINAL)
  private AccountCodeType type;

  @Column(name = "code", unique = true, nullable = false, updatable = false, length = CODE_LENGTH)
  private String code;

  @Column(name = "expire_utc", nullable = false, updatable = false)
  private Instant expire;
  @Column(name = "used_utc")
  private Instant used;

  protected AbstractAccountCode() {
    // JPA
  }

  public AbstractAccountCode(Account account, String code, Instant expire) {
    notNull(account, "account");
    positive(account.getId(), "account.id");
    notNull(account.getCreate(), "account.create");
    notNull(account.getUpdate(), "account.update");
    matches(code, CODE_PATTERN, "code");
    notNull(expire, "expire");

    this.account = account;
    this.code = code;
    this.expire = expire;
  }

  @PrePersist
  private void prePersist() {
    Instant now = Instant.now();
    this.expire = now.plusMillis(TTL);
    setCreate(now);
    setUpdate(now);
  }

  @PreUpdate
  private void preUpdate() {
    setUpdate(Instant.now());
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
  public AccountCodeType getType() {
    return this.type;
  }

  @Override
  public String getCode() {
    return this.code;
  }

  @Override
  public Instant getExpire() {
    return this.expire;
  }

  @Override
  public Instant getUsed() {
    return this.used;
  }

  @Override
  public Account use() {
    if (null != this.used) {
      throw new IllegalStateException(format("already used at %s", this.used));
    }

    this.used = Instant.now();
    this.account.enable();

    return this.account;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    } else if (0L < this.id && null != obj && obj instanceof AbstractAccountCode) {
      return this.id == ((AbstractAccountCode) obj).id;
    } else {
      return false;
    }
  }

  @Override
  public String toString() {
    return new StringBuffer(AbstractAccountCode.class.getSimpleName())
        .append("{id=").append(this.id)
        .append(", account=").append(this.account.toSimpleString())
        .append(", type=").append(this.type)
        .append(", code='").append(this.code).append('\'')
        .append(", expire=").append(this.expire)
        .append(", used=").append(this.used)
        .append('}').toString();
  }
}
