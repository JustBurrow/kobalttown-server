package kr.lul.kobalttown.jpa.entity;

import kr.lul.kobalttown.domain.account.Account;
import kr.lul.kobalttown.jpa.support.entity.AbstractUpdatableEntity;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;

import static kr.lul.kobalttown.util.Asserts.hasLength;

/**
 * @author justburrow
 * @since 2017. 8. 5.
 */
@Entity(name = "Account")
@Table(name = "user_account", uniqueConstraints = {@UniqueConstraint(name = "UQ_ACCOUNT_EMAIL", columnNames = "email")})
public class AccountEntity extends AbstractUpdatableEntity implements Account {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, insertable = false, updatable = false)
  private long    id;
  @Column(name = "email", unique = true, nullable = false)
  private String  email;
  @Column(name = "enabled", nullable = false)
  private boolean enabled;

  private AccountEntity() {
  }

  public AccountEntity(String email) {
    hasLength(email, "email");

    this.email = email;
  }

  @PrePersist
  private void prePersist() {
    Instant now = Instant.now();
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
  public String getEmail() {
    return this.email;
  }

  @Override
  public boolean isEnable() {
    return this.enabled;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this.id);
  }

  @Override
  public boolean equals(Object obj) {
    if (0L < this.id && null != obj && obj instanceof AccountEntity) {
      return this.id == ((AccountEntity) obj).id;
    } else {
      return false;
    }
  }

  @Override
  public String toString() {
    return new StringBuffer(AccountEntity.class.getSimpleName())
        .append("{id=").append(this.id)
        .append(", email='").append(this.email).append('\'')
        .append(", enabled=").append(this.enabled)
        .append(", create=").append(getCreate())
        .append(", update=").append(getUpdate())
        .append('}').toString();
  }
}
