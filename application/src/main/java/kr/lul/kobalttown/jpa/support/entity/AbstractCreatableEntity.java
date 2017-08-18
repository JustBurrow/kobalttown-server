package kr.lul.kobalttown.jpa.support.entity;

import kr.lul.kobalttown.domain.account.Creatable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.Instant;

import static kr.lul.kobalttown.util.Asserts.notNull;

/**
 * @author justburrow
 * @since 2017. 8. 18.
 */
@MappedSuperclass
public abstract class AbstractCreatableEntity implements Creatable {
  @Column(name = "create_utc", nullable = false, updatable = false)
  private Instant create;

  protected void setCreate(Instant create) {
    notNull(create, "create");
    this.create = create;
  }

  @Override
  public Instant getCreate() {
    return this.create;
  }
}
