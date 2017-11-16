package kr.lul.kobalttown.util;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import static kr.lul.kobalttown.util.Asserts.notNull;

/**
 * fluent api.
 *
 * @author justburrow
 * @since 2017. 9. 23.
 */
public class SetUtil<E> {
  public static <E> SetUtil<E> hashSet() {
    return new SetUtil<>(new HashSet<>());
  }

  public static <E> SetUtil<E> hashSet(E... elements) {
    SetUtil<E> util = new SetUtil<>(new HashSet<>());
    util.add(elements);
    return util;
  }

  public static <E> SetUtil<E> linkedHashSet() {
    return new SetUtil<>(new LinkedHashSet<>());
  }

  public static <E> SetUtil<E> linkedHashSet(E... elements) {
    SetUtil<E> util = new SetUtil<>(new LinkedHashSet<>());
    util.add(elements);
    return util;
  }

  private final Object lock = new Object();
  private Set<E> set;

  public SetUtil(Set<E> set) {
    notNull(set, "set");
    this.set = set;
  }

  public SetUtil<E> add(E... elements) {
    synchronized (this.lock) {
      if (null == this.set) {
        throw new IllegalStateException("already built.");
      }
      for (E e : elements) {
        this.set.add(e);
      }
    }
    return this;
  }

  public Set<E> build() {
    synchronized (this.lock) {
      if (null == this.set) {
        throw new IllegalStateException("already built.");
      }
      Set<E> rv = this.set;
      this.set = null;
      return rv;
    }
  }
}
