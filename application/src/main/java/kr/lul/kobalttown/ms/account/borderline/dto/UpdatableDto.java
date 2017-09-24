package kr.lul.kobalttown.ms.account.borderline.dto;

import lombok.Data;

import java.time.ZonedDateTime;

/**
 * @author justburrow
 * @since 2017. 8. 7.
 */
@Data
public class UpdatableDto {
  private ZonedDateTime create;
  private ZonedDateTime update;
}
