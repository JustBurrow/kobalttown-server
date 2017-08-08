package kr.lul.kobalttown.borderline.account.dto;

import lombok.Data;

import java.time.Instant;

/**
 * @author justburrow
 * @since 2017. 8. 7.
 */
@Data
public class UpdatableDto {
  private Instant create;
  private Instant update;

  public UpdatableDto() {
  }

  public UpdatableDto(Instant create, Instant update) {
    this.create = create;
    this.update = update;
  }
}
