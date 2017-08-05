package kr.lul.kobalttown.domain;

import java.time.Instant;

/**
 * @author justburrow
 * @since 2017. 8. 5.
 */
public interface Updatable extends Creatable {
  Instant getUpdate();
}
