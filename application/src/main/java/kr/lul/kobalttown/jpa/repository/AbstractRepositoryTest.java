package kr.lul.kobalttown.jpa.repository;

import java.time.Instant;

/**
 * @author justburrow
 * @since 2017. 8. 26.
 */
public abstract class AbstractRepositoryTest {
  protected Instant before;

  protected void commonSetUp() throws Exception {
    before = Instant.now();
  }
}
