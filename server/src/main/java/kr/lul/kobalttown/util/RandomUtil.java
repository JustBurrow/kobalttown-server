package kr.lul.kobalttown.util;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

import static java.lang.String.format;

/**
 * 일정 조건의 임의의 정수를 생성하는 유틸리티.
 *
 * @author Just Burrow
 * @since 2016. 8. 14.
 */
public class RandomUtil {
  public static final RandomUtil R;
  public static final RandomUtil SR;

  static {
    R = random();
    SR = secureRandom();
  }

  /**
   * {@link Random}을 사용하는 인스턴스.
   *
   * @author Just Burrow
   * @since 2016. 8. 14.
   */
  public static RandomUtil random() {
    return new RandomUtil(new Random());
  }

  /**
   * {@link SecureRandom}을 사용하는 인스턴스.
   *
   * @author Just Burrow
   * @since 2016. 8. 14.
   */
  public static RandomUtil secureRandom() {
    return new RandomUtil(new SecureRandom());
  }

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  private Random r;

  private RandomUtil(Random random) {
    this.r = random;
  }

  /**
   * 임의의 <code>boolean</code>을 고른다.
   *
   * @return 임의의 <code>boolean</code>.
   * @author Just Burrow
   * @since 2016. 8. 14.
   */
  public boolean bool() {
    return this.r.nextBoolean();
  }

  /**
   * 임의의 음수를 반환한다.
   *
   * @return 임의의 음수.
   * @author Just Burrow
   * @since 2016. 8. 14.
   */
  public int negative() {
    return -1 - this.r.nextInt(Integer.MAX_VALUE);
  }

  /**
   * @param min 최소값(포함).
   * @return
   * @throws IllegalArgumentException <code>-1 < min</code>
   * @author Just Burrow
   * @since 2016. 9. 27.
   */
  public int negative(int min) throws IllegalArgumentException {
    if (-1 < min) {
      throw new IllegalArgumentException(format("min[%d] is greater than -1.", min));
    } else if (-1 == min) {
      return -1;
    }
    return -1 - this.notNegative(-min - 1);
  }

  /**
   * 임의의 음수를 반환한다.
   *
   * @return 임의의 음수.
   * @author Just Burrow
   * @since 2016. 8. 14.
   */
  public long negativeLong() {
    long val = this.r.nextLong();
    if (0L < val) {
      val *= -1;
    }
    return -1L + val;
  }

  /**
   * @param min 최소값(포함).
   * @return
   * @throws IllegalArgumentException <code>-1 < min</code>
   * @author Just Burrow
   * @since 2016. 9. 27.
   */
  public long negative(long min) throws IllegalArgumentException {
    if (-1L < min) {
      throw new IllegalArgumentException(format("min[%d] is greater than -1.", min));
    } else if (-1L == min) {
      return -1L;
    }
    return -1L - this.notNegative(-min - 1);
  }

  /**
   * 임의의 양의 정수를 반환한다.
   *
   * @return 임의의 양의 정수.
   * @author Just Burrow
   * @since 2016. 8. 14.
   */
  public int positive() {
    return 1 + this.r.nextInt(Integer.MAX_VALUE);
  }

  /**
   * @param max 최대값(미포함).
   * @return
   * @throws IllegalArgumentException <code>max ≤ 2</code>
   * @author Just Burrow
   * @since 2016. 9. 27.
   */
  public int positive(int max) throws IllegalArgumentException {
    if (1 >= max) {
      throw new IllegalArgumentException(format("max[%d} is less than 2.", max));
    }
    return 1 + this.r.nextInt(max - 1);
  }

  /**
   * 임의의 양의 정수를 반환한다.
   *
   * @return 임의의 양의 정수.
   * @author Just Burrow
   * @since 2016. 8. 14.
   */
  public long positiveLong() {
    long val = this.r.nextLong();
    if (Long.MIN_VALUE == val) {
      return this.positiveLong();
    } else if (0L > val) {
      val *= -1L;
    }
    return 1 + val;
  }

  /**
   * @param max 최대값(미포함).
   * @return
   * @throws IllegalArgumentException <code>max ≤ 2</code>
   * @author Just Burrow
   * @since 2016. 9. 27.
   */
  public long positive(long max) throws IllegalArgumentException {
    if (2L > max) {
      throw new IllegalArgumentException(format("max[%d] is less than 2.", max));
    }
    return 1L + this.notNegative(max - 1L);
  }

  /**
   * 0보다 크거나 같은 임의의 수를 반환한다.
   *
   * @return 0보다 크거나 같은 임의의 수.
   * @author Just Burrow
   * @since 2016. 8. 14.
   */
  public int notNegative() {
    return this.r.nextInt(Integer.MAX_VALUE);
  }

  /**
   * 0보다 크거나 같은 임의의 수를 반환한다.
   *
   * @return 0 이상의 임의의 수.
   */
  public long notNegativeLong() {
    return this.notNegative(Long.MAX_VALUE);
  }

  /**
   * 0 이상, 최대값 미만의 임의의 값을 반환한다.
   *
   * @param max 최대값(미포함).
   * @return 임의의 0 이상의 수.
   * @author Just Burrow
   * @since 2016. 8. 14.
   */
  public int notNegative(int max) {
    Asserts.positive(max);
    return this.r.nextInt(max);
  }

  /**
   * 0 이상, 최대값 미만의 임의의 값을 반환한다.
   *
   * @param max 최대값(미포함).
   * @return 임의의 0 이상의 수.
   * @author Just Burrow
   * @see <a href="http://stackoverflow.com/a/2546186">http://stackoverflow.com/a/2546186</a>
   * @since 2016. 8. 14.
   */
  public long notNegative(long max) {
    Asserts.positive(max);
    long bits, val;
    do {
      bits = this.r.nextLong() << 1 >>> 1;
      val = bits % max;
    } while (bits - val + max - 1 < 0L);
    return val;
  }

  /**
   * 0이 아닌 임의의 수를 반환한다.
   *
   * @return 0이 아닌 임의의 수.
   * @author Just Burrow
   * @since 2016. 8. 14.
   */
  public int notZero() {
    if (this.r.nextBoolean()) {
      return this.negative();
    } else {
      return this.positive();
    }
  }

  /**
   * 0이 아닌 임의의 수를 반환한다.
   *
   * @return 0이 아닌 임의의 수.
   * @author Just Burrow
   * @since 2016. 8. 14.
   */
  public long notZeroLong() {
    if (this.r.nextBoolean()) {
      return this.negativeLong();
    } else {
      return this.positiveLong();
    }
  }

  /**
   * 0보다 작거나 같은 임의의 수를 반환한다.
   *
   * @return 0 이하의 임의의 수.
   * @author Just Burrow
   * @since 2016. 8. 14.
   */
  public int notPositive() {
    return -this.r.nextInt(Integer.MAX_VALUE);
  }

  /**
   * @param min 최소값(포함).
   * @return
   * @throws IllegalArgumentException <code>min > 0</code>
   * @author Just Burrow
   * @since 2016. 9. 27.
   */
  public int notPositive(int min) throws IllegalArgumentException {
    if (0 < min) {
      throw new IllegalArgumentException(format("min[%d] is greater than 0.", min));
    } else if (0 == min) {
      return 0;
    } else {
      return -this.r.nextInt(1 - min);
    }
  }

  /**
   * 0보다 작거나 같은 임의의 수를 반환한다.
   *
   * @return 0 이하의 임의의 수.
   * @author Just Burrow
   * @since 2016. 8. 14.
   */
  public long notPositiveLong() {
    return -this.notNegative(Long.MAX_VALUE);
  }

  /**
   * @param min 최소값(포함).
   * @return
   * @throws IllegalArgumentException <code>min > 0</code>
   * @author Just Burrow
   * @since 2016. 9. 27.
   */
  public long notPositive(long min) throws IllegalArgumentException {
    if (0L < min) {
      throw new IllegalArgumentException(format("min[%d] is greater than 0.", min));
    } else if (0L == min) {
      return 0L;
    } else {
      return -this.notNegative(1 - min);
    }
  }

  /**
   * 최소값과 최대값 사이에서 임의의 수를 반환한다.
   *
   * @param min 최소값(포함).
   * @param max 최대값(미포함).
   * @return 임의의 수.
   * @throws IllegalArgumentException <ul>
   *                                  <li>임의의 수를 만드는 범위가 <code>int</code>형으로 표현 불가능할 때.</li>
   *                                  <li>범위 지정이 잘못된 경우.</li>
   *                                  </ul>
   * @author Just Burrow
   * @since 2016. 8. 14.
   */
  public int in(int min, int max) throws IllegalArgumentException {
    if (min >= max) {
      throw new IllegalArgumentException(format("min[%d] is greater than or equal to max[%d].", min, max));
    }

    long width = (long) max - (long) min;
    if (Integer.MAX_VALUE < width) {
      return min + (int) this.notNegative(width);
    } else {
      return min + this.r.nextInt(max - min);
    }
  }

  /**
   * 최소값과 최대값 사이에서 임의의 수를 반환한다.
   *
   * @param min 최소값(포함).
   * @param max 최대값(미포함).
   * @return 임의의 수.
   * @throws IllegalArgumentException 범위 지정이 잘못된 경우.
   * @author Just Burrow
   * @since 2016. 8. 14.
   */
  public long in(long min, long max) throws IllegalArgumentException {
    if (min >= max) {
      throw new IllegalArgumentException(format("width[%s] is wider than long max[%d]",
                                                BigInteger.valueOf(max).subtract(BigInteger.valueOf(min)),
                                                Long.MAX_VALUE));
    }
    return min + this.notNegative(max - min);
  }

  /**
   * <code>int</code>형으로 표현 가능한 값 중에서, 최소값과 최대값 사이를 제외한 임의의 수를 반환한다.
   *
   * @param min 최소값(제외 영역에 포함).
   * @param max 최대값(제외 영역에 미포함).
   * @return 범위 밖의 임의의 수.
   * @author Just Burrow
   * @since 2016. 8. 14.
   */
  public int notIn(int min, int max) {
    if (min >= max) {
      throw new IllegalArgumentException(format("min[%d] is greater than or equal to max[%d].", min, max));
    }
    return this.r.nextBoolean() ? this.in(Integer.MIN_VALUE, min + 1) : this.in(max, Integer.MAX_VALUE);
  }

  /**
   * <code>long</code>형으로 표현 가능한 값 중에서, 최소값과 최대값 사이를 제외한 임의의 수를 반환한다.
   *
   * @param min 최소값(제외 영역에 포함).
   * @param max 최대값(제외 영역에 미포함).
   * @return 범위 밖의 임의의 수.
   * @author Just Burrow
   * @since 2016. 8. 14.
   */
  public long notIn(long min, long max) {
    if (max <= min) {
      throw new IllegalArgumentException(format("min[%d] is greater than or equal to max[%d].", min, max));
    }

    long underWidth = (min >> 1) - (Long.MIN_VALUE >> 1);
    long overWidth  = (Long.MAX_VALUE >> 1) - (max >> 1);
    long width      = underWidth + overWidth;

    long number = this.notNegative(width);
    if (underWidth >= number) {
      number = Long.MIN_VALUE + (number << 1);
      return 0L == min % 2L && this.r.nextBoolean() ? number + 1L : number;
    } else {
      return Long.MIN_VALUE + (max - min) + (number << 1) + (1L == (min % 2L + max % 2L) ? 1L : 0L);
    }
  }

  /**
   * 기준값보다 작은 임의의 수를 반환한다.
   *
   * @param boundary 기준값(미포함).
   * @return 기준값보다 작은 임의의 수.
   * @author Just Burrow
   * @since 2016. 8. 14.
   */
  public int lt(int boundary) {
    Asserts.gt(boundary, Integer.MIN_VALUE);
    if (Integer.MIN_VALUE + 1 == boundary) {
      return Integer.MIN_VALUE;
    } else if (0 > boundary) {
      return boundary - 1 - this.r.nextInt(boundary - Integer.MIN_VALUE);
    } else {
      return (int) this.in((long) Integer.MIN_VALUE, boundary);
    }
  }

  /**
   * 기준값보다 작은 임의의 수를 반환한다.
   *
   * @param boundary 기준값(미포함).
   * @return 기준값보다 작은 임의의 수.
   * @author Just Burrow
   * @since 2016. 8. 14.
   */
  public long lt(long boundary) {
    Asserts.gt(boundary, Long.MIN_VALUE);
    if (Long.MIN_VALUE + 1L == boundary) {
      return Long.MIN_VALUE;
    }
    long number = this.notNegative((boundary >> 1) - (Long.MIN_VALUE >> 1));
    number = Long.MIN_VALUE + (number << 1);
    return 0L == boundary % 2L && this.r.nextBoolean() ? number : number + 1L;
  }

  /**
   * 기준값보다 작거나 같은 임의의 수를 반환한다.
   *
   * @param boundary 기준값(미포함).
   * @return 기준값보다 작거나 같은 임의의 수.
   * @author Just Burrow
   * @since 2016. 8. 14.
   */
  public int le(int boundary) {
    if (Integer.MIN_VALUE == boundary) {
      return Integer.MIN_VALUE;
    } else if (0 > boundary) {
      return boundary - this.r.nextInt(boundary - Integer.MIN_VALUE);
    } else {
      return (int) this.in(Integer.MIN_VALUE, 1L + boundary);
    }
  }

  /**
   * 기준값보다 작거나 같은 임의의 수를 반환한다.
   *
   * @param boundary 기준값(미포함).
   * @return 기준값보다 작거나 같은 임의의 수.
   * @author Just Burrow
   * @since 2016. 8. 14.
   */
  public long le(long boundary) {
    if (Long.MIN_VALUE == boundary) {
      return Long.MIN_VALUE;
    }
    long number = this.notNegative(((boundary + 1L) >> 1) - (Long.MIN_VALUE >> 1));
    number = Long.MIN_VALUE + (number << 1);
    return 0L != boundary % 2L && this.r.nextBoolean() ? number : number + 1L;
  }

  /**
   * 기준값보다 큰 임의의 수를 반환한다.
   *
   * @param boundary 기준값(미포함).
   * @return 기준갑보다 큰 임의의 수.
   * @author Just Burrow
   * @since 2016. 8. 14.
   */
  public int gt(int boundary) {
    Asserts.lt(boundary, Integer.MAX_VALUE);
    if (Integer.MAX_VALUE - 1 == boundary) {
      return Integer.MAX_VALUE;
    } else if (0 > boundary) {
      return boundary + 1 + (int) this.notNegative((long) Integer.MAX_VALUE - boundary);
    } else {
      return boundary + 1 + this.r.nextInt(Integer.MAX_VALUE - boundary);
    }
  }

  /**
   * 기준값보다 큰 임의의 수를 반환한다.
   *
   * @param boundary 기준값(미포함).
   * @return 기준갑보다 큰 임의의 수.
   * @author Just Burrow
   * @since 2016. 8. 14.
   */
  public long gt(long boundary) {
    Asserts.lt(boundary, Long.MAX_VALUE);
    if (Long.MAX_VALUE - 1L == boundary) {
      return Long.MAX_VALUE;
    }

    long number = this.notNegative((Long.MAX_VALUE >> 1) - (boundary >> 1));
    number = boundary + (number << 1);
    return 0L == boundary % 2L ? number + this.r.nextInt(1) : number + 1L + this.r.nextInt(1);
  }

  /**
   * 기준값보다 크거나 같은 임의의 수를 반환한다.
   *
   * @param boundary 기준값(미포함).
   * @return 기준갑보다 크거나 같은 임의의 수.
   * @author Just Burrow
   * @since 2016. 8. 14.
   */
  public int ge(int boundary) {
    if (Integer.MAX_VALUE == boundary) {
      return Integer.MAX_VALUE;
    } else if (0 > boundary) {
      return boundary + (int) this.notNegative((long) Integer.MAX_VALUE - boundary);
    } else {
      return boundary + this.r.nextInt(Integer.MAX_VALUE - boundary);
    }
  }

  /**
   * 기준값보다 크거나 같은 임의의 수를 반환한다.
   *
   * @param boundary 기준값(미포함).
   * @return 기준갑보다 크거나 같은 임의의 수.
   * @author Just Burrow
   * @since 2016. 8. 14.
   */
  public long ge(long boundary) {
    if (Long.MAX_VALUE == boundary) {
      return Long.MAX_VALUE;
    }
    long number = this.notNegative((Long.MAX_VALUE >> 1) - (boundary >> 1));
    number = boundary + (number << 1);
    return 0L != boundary % 2L ? number + this.r.nextInt(1) : number + 1L + this.r.nextInt(1);
  }
}
