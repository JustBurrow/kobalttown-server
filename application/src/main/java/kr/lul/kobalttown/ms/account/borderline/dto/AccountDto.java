package kr.lul.kobalttown.ms.account.borderline.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author justburrow
 * @since 2017. 8. 7.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AccountDto extends UpdatableDto {
  private long    id;
  private String  email;
  private String  name;
  private boolean enabled;
}
