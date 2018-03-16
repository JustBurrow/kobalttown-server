package kr.lul.kobalttown.support.jpa.entity;

import kr.lul.kobalttown.account.domain.Updatable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.Instant;

import static kr.lul.kobalttown.util.Asserts.notNull;

/**
 * @author justburrow
 * @since 2017. 8. 5.
 */
@MappedSuperclass
public abstract class AbstractUpdatableEntity implements Updatable {
  @Column(name = "create_utc", nullable = false, updatable = false)
  private Instant create;
  @Column(name = "update_utc", nullable = false)
  private Instant update;

  protected void setCreate(Instant create) {
    notNull(create, "create");
    this.create = create;
  }

  protected void setUpdate(Instant update) {
    notNull(update, "update");
    this.update = update;
  }

  @Override
  public Instant getCreate() {
    return this.create;
  }

  @Override
  public Instant getUpdate() {
    return this.update;
  }
}
