package kr.lul.kobalttown.util;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * {@link Map} 유틸리티.
 *
 * @author justburrow
 * @since 2017. 8. 31.
 */
public class Maps<K, V> {
  public static <K, V> Maps<K, V> hashmap() {
    return new Maps<>(new HashMap<>());
  }

  public static <K, V> Maps<K, V> hashmap(K key, V value) {
    return new Maps<>(new HashMap<K, V>()).put(key, value);
  }

  public static <K, V> Maps<K, V> linkedHashMap() {
    return new Maps<>(new LinkedHashMap<K, V>());
  }

  public static <K, V> Maps<K, V> linkedHashMap(K key, V value) {
    return new Maps<>(new LinkedHashMap<K, V>()).put(key, value);
  }

  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  private final Object lock = new Object();
  private Map<K, V> map;

  public Maps() {
    this(new HashMap<>());
  }

  public Maps(Map<K, V> map) {
    if (null == map) {
      throw new IllegalArgumentException("map is null.");
    }
    this.map = map;
  }

  public Maps<K, V> put(K key, V value) {
    synchronized (lock) {
      if (null == map) {
        throw new IllegalStateException("already built.");
      }
      this.map.put(key, value);
    }
    return this;
  }

  /**
   * 유틸리티 사용을 끝내고 맵을 반환한다.
   *
   * @return
   */
  public Map<K, V> build() {
    synchronized (lock) {
      if (null == map) {
        throw new IllegalStateException("already built.");
      }
      Map<K, V> map = this.map;
      this.map = null;
      return map;
    }
  }
}
