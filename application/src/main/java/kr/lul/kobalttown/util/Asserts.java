package kr.lul.kobalttown.util;

import static java.lang.String.format;

/**
 * <code>assert</code>를 보완하는 단순한 단정 유틸리티 모음.
 *
 * @author just.burrow@lul.kr
 * @since 2016. 8. 1.
 */
public abstract class Asserts {
  /**
   * <code>boolean</code>값이 <code>true</code>임을 단정한다.
   *
   * @param bool 단정할 <code>boolean</code>값.
   * @throws AssertionException <code>true</code>가 아닌 경우.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void isTrue(boolean bool) throws AssertionException {
    if (bool) {
      return;
    }
    throw new AssertionException("boolean is not true.");
  }

  /**
   * <code>boolean</code>값이 <code>true</code>임을 단정한다.
   *
   * @param bool    단정할 <code>boolean</code>값.
   * @param message 단정 실패시의 예외 메시지.
   * @throws AssertionException <code>true</code>가 아닌 경우.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void isTrue(boolean bool, String message) throws AssertionException {
    if (bool) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * <code>boolean</code>값이 <code>false</code>임을 단정한다.
   *
   * @param bool 단정할 <code>boolean</code>값.
   * @throws AssertionException <code>false</code>가 아닌 경우.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void isFalse(boolean bool) throws AssertionException {
    if (!bool) {
      return;
    }
    throw new AssertionException("boolean is not false.");
  }

  /**
   * <code>boolean</code>값이 <code>false</code>임을 단정한다.
   *
   * @param bool    단정할 <code>boolean</code>값.
   * @param message 단정 실패시의 예외 메시지.
   * @throws AssertionException <code>false</code>가 아닌 경우.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void isFalse(boolean bool, String message) throws AssertionException {
    if (!bool) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 0보다 작음을 단정한다.
   *
   * @param number 단정할 숫자.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void negative(byte number) throws AssertionException {
    if ((byte) 0 > number) {
      return;
    }
    throw new AssertionException(format("number[%d] is not negative.", number));
  }

  /**
   * 숫자가 0보다 작음을 단정한다.
   *
   * @param number  단정할 숫자.
   * @param message 단정 실패시의 예외 메시지.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void negative(byte number, String message) throws AssertionException {
    if ((byte) 0 > number) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 0보다 작음을 단정한다.
   *
   * @param number 단정할 숫자.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void negative(short number) throws AssertionException {
    if ((short) 0 > number) {
      return;
    }
    throw new AssertionException(format("number[%d] is not negative.", number));
  }

  /**
   * 숫자가 0보다 작음을 단정한다.
   *
   * @param number  단정할 숫자.
   * @param message 단정 실패시의 예외 메시지.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void negative(short number, String message) throws AssertionException {
    if ((short) 0 > number) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 0보다 작음을 단정한다.
   *
   * @param number 단정할 숫자.
   * @throws AssertionException 단정 실패.
   */
  public static void negative(int number) throws AssertionException {
    if (0 > number) {
      return;
    }
    throw new AssertionException(format("number[%d] is not negative.", number));
  }

  /**
   * 숫자가 0보다 작음을 단정한다.
   *
   * @param number  단정할 숫자.
   * @param message 단정 실패시의 예외 메시지.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void negative(int number, String message) throws AssertionException {
    if (0 > number) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 0보다 작음을 단정한다.
   *
   * @param number 단정할 숫자.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void negative(long number) throws AssertionException {
    if (0L > number) {
      return;
    }
    throw new AssertionException(format("number[%d] is not negative.", number));
  }

  /**
   * 숫자가 0보다 작음을 단정한다.
   *
   * @param number  단정할 숫자.
   * @param message 단정 실패시의 예외 메시지.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void negative(long number, String message) throws AssertionException {
    if (0L > number) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 0보다 작음을 단정한다.
   *
   * @param number 단정할 숫자.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void negative(float number) throws AssertionException {
    if (0.0F > number) {
      return;
    }
    throw new AssertionException(format("number[%f] is not negative.", number));
  }

  /**
   * 숫자가 0보다 작음을 단정한다.
   *
   * @param number  단정할 숫자.
   * @param message 단정 실패시의 예외 메시지.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void negative(float number, String message) throws AssertionException {
    if (0.0F > number) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 0보다 작음을 단정한다.
   *
   * @param number 단정할 숫자.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void negative(double number) throws AssertionException {
    if (0.0 > number) {
      return;
    }
    throw new AssertionException(format("number[%f] is not negative.", number));
  }

  /**
   * 숫자가 0보다 작음을 단정한다.
   *
   * @param number  단정할 숫자.
   * @param message 단정 실패시의 예외 메시지.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void negative(double number, String message) throws AssertionException {
    if (0.0 > number) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 0임을 단정한다.
   *
   * @param number 단정할 숫자.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void zero(byte number) throws AssertionException {
    if ((byte) 0 == number) {
      return;
    }
    throw new AssertionException(format("number[%d] is not zero.", number));
  }

  /**
   * 숫자가 0임을 단정한다.
   *
   * @param number  단정할 숫자.
   * @param message 단정 실패시의 예외 메시지.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void zero(byte number, String message) throws AssertionException {
    if ((byte) 0 == number) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 0임을 단정한다.
   *
   * @param number 단정할 숫자.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void zero(short number) throws AssertionException {
    if ((short) 0 == number) {
      return;
    }
    throw new AssertionException(format("number[%d] is not zero.", number));
  }

  /**
   * 숫자가 0임을 단정한다.
   *
   * @param number  단정할 숫자.
   * @param message 단정 실패시의 예외 메시지.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void zero(short number, String message) throws AssertionException {
    if ((short) 0 == number) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 0임을 단정한다.
   *
   * @param number 단정할 숫자.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void zero(int number) throws AssertionException {
    if (0 == number) {
      return;
    }
    throw new AssertionException(format("number[%d] is not zero.", number));
  }

  /**
   * 숫자가 0임을 단정한다.
   *
   * @param number  단정할 숫자.
   * @param message 단정 실패시의 예외 메시지.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void zero(int number, String message) throws AssertionException {
    if (0 == number) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 0임을 단정한다.
   *
   * @param number 단정할 숫자.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void zero(long number) throws AssertionException {
    if (0L == number) {
      return;
    }
    throw new AssertionException(format("number[%d] is not zero.", number));
  }

  /**
   * 숫자가 0임을 단정한다.
   *
   * @param number  단정할 숫자.
   * @param message 단정 실패시의 예외 메시지.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void zero(long number, String message) throws AssertionException {
    if (0L == number) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 0임을 단정한다.
   *
   * @param number 단정할 숫자.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void zero(float number) throws AssertionException {
    if (0.0F == number) {
      return;
    }
    throw new AssertionException(format("number[%f] is not zero.", number));
  }

  /**
   * 숫자가 0임을 단정한다.
   *
   * @param number  단정할 숫자.
   * @param message 단정 실패시의 예외 메시지.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void zero(float number, String message) throws AssertionException {
    if (0.0F == number) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 0임을 단정한다.
   *
   * @param number 단정할 숫자.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void zero(double number) throws AssertionException {
    if (0.0 == number) {
      return;
    }
    throw new AssertionException(format("number[%f] is not zero.", number));
  }

  /**
   * 숫자가 0임을 단정한다.
   *
   * @param number  단정할 숫자.
   * @param message 단정 실패시의 예외 메시지.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void zero(double number, String message) throws AssertionException {
    if (0.0 == number) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 0보다 큼을 단정한다.
   *
   * @param number 단정할 숫자.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void positive(byte number) throws AssertionException {
    if ((byte) 0 < number) {
      return;
    }
    throw new AssertionException(format("number[%d] is not zero.", number));
  }

  /**
   * 숫자가 0보다 큼을 단정한다.
   *
   * @param number  단정할 숫자.
   * @param message 단정 실패시의 예외 메시지.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void positive(byte number, String message) throws AssertionException {
    if ((byte) 0 < number) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 0보다 큼을 단정한다.
   *
   * @param number 단정할 숫자.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void positive(short number) throws AssertionException {
    if ((short) 0 < number) {
      return;
    }
    throw new AssertionException(format("number[%d] is not zero.", number));
  }

  /**
   * 숫자가 0보다 큼을 단정한다.
   *
   * @param number  단정할 숫자.
   * @param message 단정 실패시의 예외 메시지.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void positive(short number, String message) throws AssertionException {
    if ((short) 0 < number) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 0보다 큼을 단정한다.
   *
   * @param number 단정할 숫자.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void positive(int number) throws AssertionException {
    if (0 < number) {
      return;
    }
    throw new AssertionException(format("number[%d] is not zero.", number));
  }

  /**
   * 숫자가 0보다 큼을 단정한다.
   *
   * @param number  단정할 숫자.
   * @param message 단정 실패시의 예외 메시지.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void positive(int number, String message) throws AssertionException {
    if (0 < number) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 0보다 큼을 단정한다.
   *
   * @param number 단정할 숫자.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void positive(long number) throws AssertionException {
    if (0L < number) {
      return;
    }
    throw new AssertionException(format("number[%d] is not zero.", number));
  }

  /**
   * 숫자가 0보다 큼을 단정한다.
   *
   * @param number  단정할 숫자.
   * @param message 단정 실패시의 예외 메시지.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void positive(long number, String message) throws AssertionException {
    if (0L < number) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 0보다 큼을 단정한다.
   *
   * @param number 단정할 숫자.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void positive(float number) throws AssertionException {
    if (0.0F < number) {
      return;
    }
    throw new AssertionException(format("number[%f] is not zero.", number));
  }

  /**
   * 숫자가 0보다 큼을 단정한다.
   *
   * @param number  단정할 숫자.
   * @param message 단정 실패시의 예외 메시지.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void positive(float number, String message) throws AssertionException {
    if (0.0F < number) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 0보다 큼을 단정한다.
   *
   * @param number 단정할 숫자.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void positive(double number) throws AssertionException {
    if (0.0 < number) {
      return;
    }
    throw new AssertionException(format("number[%f] is not zero.", number));
  }

  /**
   * 숫자가 0보다 큼을 단정한다.
   *
   * @param number  단정할 숫자.
   * @param message 단정 실패시의 예외 메시지.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void positive(double number, String message) throws AssertionException {
    if (0.0 < number) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 0보다 작지 않음을 단정한다.
   *
   * @param number 단정할 숫자.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void notNegative(byte number) throws AssertionException {
    if ((byte) 0 <= number) {
      return;
    }
    throw new AssertionException(format("number[%d] is negative.", number));
  }

  /**
   * 숫자가 0보다 작지 않음을 단정한다.
   *
   * @param number  단정할 숫자.
   * @param message 단정 실패시의 예외 메시지.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void notNegative(byte number, String message) throws AssertionException {
    if ((byte) 0 <= number) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 0보다 작지 않음을 단정한다.
   *
   * @param number 단정할 숫자.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void notNegative(short number) throws AssertionException {
    if ((short) 0 <= number) {
      return;
    }
    throw new AssertionException(format("number[%d] is negative.", number));
  }

  /**
   * 숫자가 0보다 작지 않음을 단정한다.
   *
   * @param number  단정할 숫자.
   * @param message 단정 실패시의 예외 메시지.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void notNegative(short number, String message) throws AssertionException {
    if ((short) 0 <= number) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 0보다 작지 않음을 단정한다.
   *
   * @param number 단정할 숫자.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void notNegative(int number) throws AssertionException {
    if (0 <= number) {
      return;
    }
    throw new AssertionException(format("number[%d] is negative.", number));
  }

  /**
   * 숫자가 0보다 작지 않음을 단정한다.
   *
   * @param number  단정할 숫자.
   * @param message 단정 실패시의 예외 메시지.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void notNegative(int number, String message) throws AssertionException {
    if (0 <= number) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 0보다 작지 않음을 단정한다.
   *
   * @param number 단정할 숫자.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void notNegative(long number) throws AssertionException {
    if (0L <= number) {
      return;
    }
    throw new AssertionException(format("number[%d] is negative.", number));
  }

  /**
   * 숫자가 0보다 작지 않음을 단정한다.
   *
   * @param number  단정할 숫자.
   * @param message 단정 실패시의 예외 메시지.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void notNegative(long number, String message) throws AssertionException {
    if (0L <= number) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 0보다 작지 않음을 단정한다.
   *
   * @param number 단정할 숫자.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void notNegative(float number) throws AssertionException {
    if (0.0F <= number) {
      return;
    }
    throw new AssertionException(format("number[%f] is negative.", number));
  }

  /**
   * 숫자가 0보다 작지 않음을 단정한다.
   *
   * @param number  단정할 숫자.
   * @param message 단정 실패시의 예외 메시지.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void notNegative(float number, String message) throws AssertionException {
    if (0.0F <= number) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 0보다 작지 않음을 단정한다.
   *
   * @param number 단정할 숫자.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void notNegative(double number) throws AssertionException {
    if (0.0 <= number) {
      return;
    }
    throw new AssertionException(format("number[%f] is negative.", number));
  }

  /**
   * 숫자가 0보다 작지 않음을 단정한다.
   *
   * @param number  단정할 숫자.
   * @param message 단정 실패시의 예외 메시지.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void notNegative(double number, String message) throws AssertionException {
    if (0.0 <= number) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 0보다 크지 않음을 단정한다.
   *
   * @param number 단정할 숫자.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void notPositive(byte number) throws AssertionException {
    if ((byte) 0 >= number) {
      return;
    }
    throw new AssertionException(format("number[%d] is positive.", number));
  }

  /**
   * 숫자가 0보다 크지 않음을 단정한다.
   *
   * @param number  단정할 숫자.
   * @param message 단정 실패시의 예외 메시지.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void notPositive(byte number, String message) throws AssertionException {
    if ((byte) 0 >= number) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 0보다 크지 않음을 단정한다.
   *
   * @param number 단정할 숫자.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void notPositive(short number) throws AssertionException {
    if ((short) 0 >= number) {
      return;
    }
    throw new AssertionException(format("number[%d] is positive.", number));
  }

  /**
   * 숫자가 0보다 크지 않음을 단정한다.
   *
   * @param number  단정할 숫자.
   * @param message 단정 실패시의 예외 메시지.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void notPositive(short number, String message) throws AssertionException {
    if ((short) 0 >= number) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 0보다 크지 않음을 단정한다.
   *
   * @param number 단정할 숫자.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void notPositive(int number) throws AssertionException {
    if (0 >= number) {
      return;
    }
    throw new AssertionException(format("number[%d] is positive.", number));
  }

  /**
   * 숫자가 0보다 크지 않음을 단정한다.
   *
   * @param number  단정할 숫자.
   * @param message 단정 실패시의 예외 메시지.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void notPositive(int number, String message) throws AssertionException {
    if (0 >= number) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 0보다 크지 않음을 단정한다.
   *
   * @param number 단정할 숫자.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void notPositive(long number) throws AssertionException {
    if (0L >= number) {
      return;
    }
    throw new AssertionException(format("number[%d] is positive.", number));
  }

  /**
   * 숫자가 0보다 크지 않음을 단정한다.
   *
   * @param number  단정할 숫자.
   * @param message 단정 실패시의 예외 메시지.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void notPositive(long number, String message) throws AssertionException {
    if (0L >= number) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 0보다 크지 않음을 단정한다.
   *
   * @param number 단정할 숫자.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void notPositive(float number) throws AssertionException {
    if (0.0F >= number) {
      return;
    }
    throw new AssertionException(format("number[%f] is positive.", number));
  }

  /**
   * 숫자가 0보다 크지 않음을 단정한다.
   *
   * @param number  단정할 숫자.
   * @param message 단정 실패시의 예외 메시지.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void notPositive(float number, String message) throws AssertionException {
    if (0.0F >= number) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 0보다 크지 않음을 단정한다.
   *
   * @param number 단정할 숫자.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void notPositive(double number) throws AssertionException {
    if (0.0 >= number) {
      return;
    }
    throw new AssertionException(format("number[%f] is positive.", number));
  }

  /**
   * 숫자가 0보다 크지 않음을 단정한다.
   *
   * @param number  단정할 숫자.
   * @param message 단정 실패시의 예외 메시지.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void notPositive(double number, String message) throws AssertionException {
    if (0.0 >= number) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 범위 안에 들어감을 단정한다.
   *
   * @param number 단정할 숫자.
   * @param min    최소(포함).
   * @param max    최대(미포함).
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void in(byte number, byte min, byte max) throws AssertionException {
    if (min >= max) {
      throw new AssertionException(format("min[%d] is greater than or equal to max[%d].", min, max));
    } else if (min <= number && number < max) {
      return;
    }
    throw new AssertionException(format("number[%d] not in range [min[%d], max[%d])", number, min, max));
  }

  /**
   * 숫자가 범위 안에 들어감을 단정한다.
   *
   * @param number  단정할 숫자.
   * @param min     최소(포함).
   * @param max     최대(미포함).
   * @param message 단정 실패시의 예외 메시지.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void in(byte number, byte min, byte max, String message) throws AssertionException {
    if (min >= max) {
      throw new AssertionException(message, new IllegalArgumentException(
          format("min[%d] is greater than or equal to max[%d].", min, max)));
    } else if (min <= number && number < max) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 범위 안에 들어감을 단정한다.
   *
   * @param number 단정할 숫자.
   * @param min    최소(포함).
   * @param max    최대(미포함).
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void in(short number, short min, short max) throws AssertionException {
    if (min >= max) {
      throw new AssertionException(format("min[%d] is greater than or equal to max[%d].", min, max));
    } else if (min <= number && number < max) {
      return;
    }
    throw new AssertionException(format("number[%d] not in range [min[%d], max[%d])", number, min, max));
  }

  /**
   * 숫자가 범위 안에 들어감을 단정한다.
   *
   * @param number  단정할 숫자.
   * @param min     최소(포함).
   * @param max     최대(미포함).
   * @param message 단정 실패시의 예외 메시지.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void in(short number, short min, short max, String message) throws AssertionException {
    if (min >= max) {
      throw new AssertionException(message, new IllegalArgumentException(
          format("min[%d] is greater than or equal to max[%d].", min, max)));
    } else if (min <= number && number < max) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 범위 안에 들어감을 단정한다.
   *
   * @param number 단정할 숫자.
   * @param min    최소(포함).
   * @param max    최대(미포함).
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void in(int number, int min, int max) throws AssertionException {
    if (min >= max) {
      throw new AssertionException(format("min[%d] is greater than or equal to max[%d].", min, max));
    } else if (min <= number && number < max) {
      return;
    }
    throw new AssertionException(format("number[%d] not in range [min[%d], max[%d])", number, min, max));
  }

  /**
   * 숫자가 범위 안에 들어감을 단정한다.
   *
   * @param number  단정할 숫자.
   * @param min     최소(포함).
   * @param max     최대(미포함).
   * @param message 단정 실패시의 예외 메시지.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void in(int number, int min, int max, String message) throws AssertionException {
    if (min >= max) {
      throw new AssertionException(message, new IllegalArgumentException(
          format("min[%d] is greater than or equal to max[%d].", min, max)));
    } else if (min <= number && number < max) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 범위 안에 들어감을 단정한다.
   *
   * @param number 단정할 숫자.
   * @param min    최소(포함).
   * @param max    최대(미포함).
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void in(long number, long min, long max) throws IllegalArgumentException, AssertionException {
    if (min >= max) {
      throw new AssertionException(format("min[%d] is greater than or equal to max[%d].", min, max));
    } else if (min <= number && number < max) {
      return;
    }
    throw new AssertionException(format("number[%d] not in range [min[%d], max[%d])", number, min, max));
  }

  /**
   * 숫자가 범위 안에 들어감을 단정한다.
   *
   * @param number  단정할 숫자.
   * @param min     최소(포함).
   * @param max     최대(미포함).
   * @param message 단정 실패시의 예외 메시지.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void in(long number, long min, long max, String message) throws AssertionException {
    if (min >= max) {
      throw new AssertionException(message, new IllegalArgumentException(
          format("min[%d] is greater than or equal to max[%d].", min, max)));
    } else if (min <= number && number < max) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 범위 안에 들어감을 단정한다.
   *
   * @param number 단정할 숫자.
   * @param min    최소(포함).
   * @param max    최대(미포함).
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void in(float number, float min, float max) throws IllegalArgumentException, AssertionException {
    if (min >= max) {
      throw new AssertionException(format("min[%d] is greater than or equal to max[%d].", min, max));
    } else if (min <= number && number < max) {
      return;
    }
    throw new AssertionException(format("number[%f] not in range [min[%f], max[%f])", number, min, max));
  }

  /**
   * 숫자가 범위 안에 들어감을 단정한다.
   *
   * @param number  단정할 숫자.
   * @param min     최소(포함).
   * @param max     최대(미포함).
   * @param message 단정 실패시의 예외 메시지.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void in(float number, float min, float max, String message) throws AssertionException {
    if (min >= max) {
      throw new AssertionException(message, new IllegalArgumentException(
          format("min[%d] is greater than or equal to max[%d].", min, max)));
    } else if (min <= number && number < max) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 범위 안에 들어감을 단정한다.
   *
   * @param number 단정할 숫자.
   * @param min    최소(포함).
   * @param max    최대(미포함).
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void in(double number, double min, double max) throws AssertionException {
    if (min >= max) {
      throw new AssertionException(format("min[%d] is greater than or equal to max[%d].", min, max));
    } else if (min <= number && number < max) {
      return;
    }
    throw new AssertionException(format("number[%f] not in range [min[%f], max[%f])", number, min, max));
  }

  /**
   * 숫자가 범위 안에 들어감을 단정한다.
   *
   * @param number  단정할 숫자.
   * @param min     최소(포함).
   * @param max     최대(미포함).
   * @param message 단정 실패시의 예외 메시지.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void in(double number, double min, double max, String message) throws AssertionException {
    if (min >= max) {
      throw new AssertionException(message, new IllegalArgumentException(
          format("min[%d] is greater than or equal to max[%d].", min, max)));
    } else if (min <= number && number < max) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 기준값보다 작음을 단정한다.
   *
   * @param number   단정할 숫자.
   * @param boundary 기준값.
   * @throws AssertionException 단정 실패.
   */
  public static void lt(byte number, byte boundary) throws AssertionException {
    if (number < boundary) {
      return;
    }
    throw new AssertionException(format("number[%d] is not less than boundary[%d].", number, boundary));
  }

  /**
   * 숫자가 기준값보다 작음을 단정한다.
   *
   * @param number   단정할 숫자.
   * @param boundary 기준값.
   * @param message  단정 실패시의 예외 메시지.
   * @throws AssertionException 단정 실패.
   */
  public static void lt(byte number, byte boundary, String message) throws AssertionException {
    if (number < boundary) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 기준값보다 작음을 단정한다.
   *
   * @param number   단정할 숫자.
   * @param boundary 기준값.
   * @throws AssertionException 단정 실패.
   */
  public static void lt(short number, short boundary) throws AssertionException {
    if (number < boundary) {
      return;
    }
    throw new AssertionException(format("number[%d] is not less than boundary[%d].", number, boundary));
  }

  /**
   * 숫자가 기준값보다 작음을 단정한다.
   *
   * @param number   단정할 숫자.
   * @param boundary 기준값.
   * @param message  단정 실패시의 예외 메시지.
   * @throws AssertionException 단정 실패.
   */
  public static void lt(short number, short boundary, String message) throws AssertionException {
    if (number < boundary) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 기준값보다 작음을 단정한다.
   *
   * @param number   단정할 숫자.
   * @param boundary 기준값.
   * @throws AssertionException 단정 실패.
   */
  public static void lt(int number, int boundary) throws AssertionException {
    if (number < boundary) {
      return;
    }
    throw new AssertionException(format("number[%d] is not less than boundary[%d].", number, boundary));
  }

  /**
   * 숫자가 기준값보다 작음을 단정한다.
   *
   * @param number   단정할 숫자.
   * @param boundary 기준값.
   * @param message  단정 실패시의 예외 메시지.
   * @throws AssertionException 단정 실패.
   */
  public static void lt(int number, int boundary, String message) throws AssertionException {
    if (number < boundary) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 기준값보다 작음을 단정한다.
   *
   * @param number   단정할 숫자.
   * @param boundary 기준값.
   * @throws AssertionException 단정 실패.
   */
  public static void lt(long number, long boundary) throws AssertionException {
    if (number < boundary) {
      return;
    }
    throw new AssertionException(format("number[%d] is not less than boundary[%d].", number, boundary));
  }

  /**
   * 숫자가 기준값보다 작음을 단정한다.
   *
   * @param number   단정할 숫자.
   * @param boundary 기준값.
   * @param message  단정 실패시의 예외 메시지.
   * @throws AssertionException 단정 실패.
   */
  public static void lt(long number, long boundary, String message) throws AssertionException {
    if (number < boundary) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 기준값보다 작음을 단정한다.
   *
   * @param number   단정할 숫자.
   * @param boundary 기준값.
   * @throws AssertionException 단정 실패.
   */
  public static void lt(float number, float boundary) throws AssertionException {
    if (number < boundary) {
      return;
    }
    throw new AssertionException(format("number[%f] is not less than boundary[%f].", number, boundary));
  }

  /**
   * 숫자가 기준값보다 작음을 단정한다.
   *
   * @param number   단정할 숫자.
   * @param boundary 기준값.
   * @param message  단정 실패시의 예외 메시지.
   * @throws AssertionException 단정 실패.
   */
  public static void lt(float number, float boundary, String message) throws AssertionException {
    if (number < boundary) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 기준값보다 작음을 단정한다.
   *
   * @param number   단정할 숫자.
   * @param boundary 기준값.
   * @throws AssertionException 단정 실패.
   */
  public static void lt(double number, double boundary) throws AssertionException {
    if (number < boundary) {
      return;
    }
    throw new AssertionException(format("number[%f] is not less than boundary[%f].", number, boundary));
  }

  /**
   * 숫자가 기준값보다 작음을 단정한다.
   *
   * @param number   단정할 숫자.
   * @param boundary 기준값.
   * @param message  단정 실패시의 예외 메시지.
   * @throws AssertionException 단정 실패.
   */
  public static void lt(double number, double boundary, String message) throws AssertionException {
    if (number < boundary) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * <code>c1</code>이 <code>c2</code>보다 작음을 단정한다.
   *
   * @param c1
   * @param c2
   * @throws AssertionException 단정 실패.
   * @author Just Burrow
   * @since 2016. 9. 27.
   */
  public static <T extends Comparable<T>> void lt(T c1, T c2) throws AssertionException {
    if (null == c1) {
      throw new AssertionException("c1 is null.");
    } else if (null == c2) {
      throw new AssertionException("c2 is null.");
    } else if (c1 == c2 || 0 <= c1.compareTo(c2)) {
      throw new AssertionException(format("c1[%s] is not less than c2[%s].", c1, c2));
    }
  }

  /**
   * 숫자가 기준값보다 작거나 같음을 단정한다.
   *
   * @param number   단정할 숫자.
   * @param boundary 기준값.
   * @throws AssertionException 단정 실패.
   */
  public static void le(byte number, byte boundary) throws AssertionException {
    if (number <= boundary) {
      return;
    }
    throw new AssertionException(format("number[%d] is not less than or equal to boundary[%d].", number, boundary));
  }

  /**
   * 숫자가 기준값보다 작거나 같음을 단정한다.
   *
   * @param number   단정할 숫자.
   * @param boundary 기준값.
   * @param message  단정 실패시의 예외 메시지.
   * @throws AssertionException 단정 실패.
   */
  public static void le(byte number, byte boundary, String message) throws AssertionException {
    if (number <= boundary) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 기준값보다 작거나 같음을 단정한다.
   *
   * @param number   단정할 숫자.
   * @param boundary 기준값.
   * @throws AssertionException 단정 실패.
   */
  public static void le(short number, short boundary) throws AssertionException {
    if (number <= boundary) {
      return;
    }
    throw new AssertionException(format("number[%d] is not less than or equal to boundary[%d].", number, boundary));
  }

  /**
   * 숫자가 기준값보다 작거나 같음을 단정한다.
   *
   * @param number   단정할 숫자.
   * @param boundary 기준값.
   * @param message  단정 실패시의 예외 메시지.
   * @throws AssertionException 단정 실패.
   */
  public static void le(short number, short boundary, String message) throws AssertionException {
    if (number <= boundary) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 기준값보다 작거나 같음을 단정한다.
   *
   * @param number   단정할 숫자.
   * @param boundary 기준값.
   * @throws AssertionException 단정 실패.
   */
  public static void le(int number, int boundary) throws AssertionException {
    if (number <= boundary) {
      return;
    }
    throw new AssertionException(format("number[%d] is not less than or equal to boundary[%d].", number, boundary));
  }

  /**
   * 숫자가 기준값보다 작거나 같음을 단정한다.
   *
   * @param number   단정할 숫자.
   * @param boundary 기준값.
   * @param message  단정 실패시의 예외 메시지.
   * @throws AssertionException 단정 실패.
   */
  public static void le(int number, int boundary, String message) throws AssertionException {
    if (number <= boundary) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 기준값보다 작거나 같음을 단정한다.
   *
   * @param number   단정할 숫자.
   * @param boundary 기준값.
   * @throws AssertionException 단정 실패.
   */
  public static void le(long number, long boundary) throws AssertionException {
    if (number <= boundary) {
      return;
    }
    throw new AssertionException(format("number[%d] is not less than or equal to boundary[%d].", number, boundary));
  }

  /**
   * 숫자가 기준값보다 작거나 같음을 단정한다.
   *
   * @param number   단정할 숫자.
   * @param boundary 기준값.
   * @param message  단정 실패시의 예외 메시지.
   * @throws AssertionException 단정 실패.
   */
  public static void le(long number, long boundary, String message) throws AssertionException {
    if (number <= boundary) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 기준값보다 작거나 같음을 단정한다.
   *
   * @param number   단정할 숫자.
   * @param boundary 기준값.
   * @throws AssertionException 단정 실패.
   */
  public static void le(float number, float boundary) throws AssertionException {
    if (number <= boundary) {
      return;
    }
    throw new AssertionException(format("number[%f] is not less than or equal to boundary[%f].", number, boundary));
  }

  /**
   * 숫자가 기준값보다 작거나 같음을 단정한다.
   *
   * @param number   단정할 숫자.
   * @param boundary 기준값.
   * @param message  단정 실패시의 예외 메시지.
   * @throws AssertionException 단정 실패.
   */
  public static void le(float number, float boundary, String message) throws AssertionException {
    if (number <= boundary) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 기준값보다 작거나 같음을 단정한다.
   *
   * @param number   단정할 숫자.
   * @param boundary 기준값.
   * @throws AssertionException 단정 실패.
   */
  public static void le(double number, double boundary) throws AssertionException {
    if (number <= boundary) {
      return;
    }
    throw new AssertionException(format("number[%f] is not less than or equal to boundary[%f].", number, boundary));
  }

  /**
   * 숫자가 기준값보다 작거나 같음을 단정한다.
   *
   * @param number   단정할 숫자.
   * @param boundary 기준값.
   * @param message  단정 실패시의 예외 메시지.
   * @throws AssertionException 단정 실패.
   */
  public static void le(double number, double boundary, String message) throws AssertionException {
    if (number <= boundary) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * <code>c1</code>이 <code>c2</code>보다 작거다 같음을 단정한다.
   *
   * @param c1
   * @param c2
   * @throws AssertionException 단정 실패.
   * @author Just Burrow
   * @since 2016. 9. 27.
   */
  public static <T extends Comparable<T>> void le(T c1, T c2) throws AssertionException {
    if (null == c1) {
      throw new AssertionException("c1 is null.");
    } else if (null == c2) {
      throw new AssertionException("c2 is null.");
    } else if (c1 == c2) {
      return;
    } else if (0 < c1.compareTo(c2)) {
      throw new AssertionException(format("c1[%s] is not less than or equal to c2[%s].", c1, c2));
    }
  }

  /**
   * 숫자가 기준값보다 큼을 단정한다.
   *
   * @param number   단정할 숫자.
   * @param boundary 기준값.
   * @throws AssertionException 단정 실패.
   */
  public static void gt(byte number, byte boundary) throws AssertionException {
    if (number > boundary) {
      return;
    }
    throw new AssertionException(format("number[%d] is not greater than boundary[%d].", number, boundary));
  }

  /**
   * 숫자가 기준값보다 큼을 단정한다.
   *
   * @param number   단정할 숫자.
   * @param boundary 기준값.
   * @param message  단정 실패시의 예외 메시지.
   * @throws AssertionException 단정 실패.
   */
  public static void gt(byte number, byte boundary, String message) throws AssertionException {
    if (number > boundary) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 기준값보다 큼을 단정한다.
   *
   * @param number   단정할 숫자.
   * @param boundary 기준값.
   * @throws AssertionException 단정 실패.
   */
  public static void gt(short number, short boundary) throws AssertionException {
    if (number > boundary) {
      return;
    }
    throw new AssertionException(format("number[%d] is not greater than boundary[%d].", number, boundary));
  }

  /**
   * 숫자가 기준값보다 큼을 단정한다.
   *
   * @param number   단정할 숫자.
   * @param boundary 기준값.
   * @param message  단정 실패시의 예외 메시지.
   * @throws AssertionException 단정 실패.
   */
  public static void gt(short number, short boundary, String message) throws AssertionException {
    if (number > boundary) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 기준값보다 큼을 단정한다.
   *
   * @param number   단정할 숫자.
   * @param boundary 기준값.
   * @throws AssertionException 단정 실패.
   */
  public static void gt(int number, int boundary) throws AssertionException {
    if (number > boundary) {
      return;
    }
    throw new AssertionException(format("number[%d] is not greater than boundary[%d].", number, boundary));
  }

  /**
   * 숫자가 기준값보다 큼을 단정한다.
   *
   * @param number   단정할 숫자.
   * @param boundary 기준값.
   * @param message  단정 실패시의 예외 메시지.
   * @throws AssertionException 단정 실패.
   */
  public static void gt(int number, int boundary, String message) throws AssertionException {
    if (number > boundary) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 기준값보다 큼을 단정한다.
   *
   * @param number   단정할 숫자.
   * @param boundary 기준값.
   * @throws AssertionException 단정 실패.
   */
  public static void gt(long number, long boundary) throws AssertionException {
    if (number > boundary) {
      return;
    }
    throw new AssertionException(format("number[%d] is not greater than boundary[%d].", number, boundary));
  }

  /**
   * 숫자가 기준값보다 큼을 단정한다.
   *
   * @param number   단정할 숫자.
   * @param boundary 기준값.
   * @param message  단정 실패시의 예외 메시지.
   * @throws AssertionException 단정 실패.
   */
  public static void gt(long number, long boundary, String message) throws AssertionException {
    if (number > boundary) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 기준값보다 큼을 단정한다.
   *
   * @param number   단정할 숫자.
   * @param boundary 기준값.
   * @throws AssertionException 단정 실패.
   */
  public static void gt(float number, float boundary) throws AssertionException {
    if (number > boundary) {
      return;
    }
    throw new AssertionException(format("number[%f] is not greater than boundary[%f].", number, boundary));
  }

  /**
   * 숫자가 기준값보다 큼을 단정한다.
   *
   * @param number   단정할 숫자.
   * @param boundary 기준값.
   * @param message  단정 실패시의 예외 메시지.
   * @throws AssertionException 단정 실패.
   */
  public static void gt(float number, float boundary, String message) throws AssertionException {
    if (number > boundary) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 기준값보다 큼을 단정한다.
   *
   * @param number   단정할 숫자.
   * @param boundary 기준값.
   * @throws AssertionException 단정 실패.
   */
  public static void gt(double number, double boundary) throws AssertionException {
    if (number > boundary) {
      return;
    }
    throw new AssertionException(format("number[%f] is not greater than boundary[%f].", number, boundary));
  }

  /**
   * 숫자가 기준값보다 큼을 단정한다.
   *
   * @param number   단정할 숫자.
   * @param boundary 기준값.
   * @param message  단정 실패시의 예외 메시지.
   * @throws AssertionException 단정 실패.
   */
  public static void gt(double number, double boundary, String message) throws AssertionException {
    if (number > boundary) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * <code>c1</code>이 <code>c2</code>보다 큼을 단정한다.
   *
   * @param c1
   * @param c2
   * @throws AssertionException 단정 실패.
   * @author Just Burrow
   * @since 2016. 9. 27.
   */
  public static <T extends Comparable<T>> void gt(T c1, T c2) throws AssertionException {
    if (null == c1) {
      throw new AssertionException("c1 is null.");
    } else if (null == c2) {
      throw new AssertionException("c2 is null.");
    } else if (c1 == c2 || 0 > c1.compareTo(c2)) {
      throw new AssertionException(format("c1[%s] is not greater than c2[%s].", c1, c2));
    }
  }

  /**
   * 숫자가 기준값보다 크거나 같음을 단정한다.
   *
   * @param number   단정할 숫자.
   * @param boundary 기준값.
   * @throws AssertionException 단정 실패.
   */
  public static void ge(byte number, byte boundary) throws AssertionException {
    if (number >= boundary) {
      return;
    }
    throw new AssertionException(format("number[%d] is not greater than or equal to boundary[%d].", number, boundary));
  }

  /**
   * 숫자가 기준값보다 크거나 같음을 단정한다.
   *
   * @param number   단정할 숫자.
   * @param boundary 기준값.
   * @param message  단정 실패시의 예외 메시지.
   * @throws AssertionException 단정 실패.
   */
  public static void ge(byte number, byte boundary, String message) throws AssertionException {
    if (number >= boundary) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 기준값보다 크거나 같음을 단정한다.
   *
   * @param number   단정할 숫자.
   * @param boundary 기준값.
   * @throws AssertionException 단정 실패.
   */
  public static void ge(short number, short boundary) throws AssertionException {
    if (number >= boundary) {
      return;
    }
    throw new AssertionException(format("number[%d] is not greater than or equal to boundary[%d].", number, boundary));
  }

  /**
   * 숫자가 기준값보다 크거나 같음을 단정한다.
   *
   * @param number   단정할 숫자.
   * @param boundary 기준값.
   * @param message  단정 실패시의 예외 메시지.
   * @throws AssertionException 단정 실패.
   */
  public static void ge(short number, short boundary, String message) throws AssertionException {
    if (number >= boundary) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 기준값보다 크거나 같음을 단정한다.
   *
   * @param number   단정할 숫자.
   * @param boundary 기준값.
   * @throws AssertionException 단정 실패.
   */
  public static void ge(int number, int boundary) throws AssertionException {
    if (number >= boundary) {
      return;
    }
    throw new AssertionException(format("number[%d] is not greater than or equal to boundary[%d].", number, boundary));
  }

  /**
   * 숫자가 기준값보다 크거나 같음을 단정한다.
   *
   * @param number   단정할 숫자.
   * @param boundary 기준값.
   * @param message  단정 실패시의 예외 메시지.
   * @throws AssertionException 단정 실패.
   */
  public static void ge(int number, int boundary, String message) throws AssertionException {
    if (number >= boundary) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 기준값보다 크거나 같음을 단정한다.
   *
   * @param number   단정할 숫자.
   * @param boundary 기준값.
   * @throws AssertionException 단정 실패.
   */
  public static void ge(long number, long boundary) throws AssertionException {
    if (number >= boundary) {
      return;
    }
    throw new AssertionException(format("number[%d] is not greater than or equal to boundary[%d].", number, boundary));
  }

  /**
   * 숫자가 기준값보다 크거나 같음을 단정한다.
   *
   * @param number   단정할 숫자.
   * @param boundary 기준값.
   * @param message  단정 실패시의 예외 메시지.
   * @throws AssertionException 단정 실패.
   */
  public static void ge(long number, long boundary, String message) throws AssertionException {
    if (number >= boundary) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 기준값보다 크거나 같음을 단정한다.
   *
   * @param number   단정할 숫자.
   * @param boundary 기준값.
   * @throws AssertionException 단정 실패.
   */
  public static void ge(float number, float boundary) throws AssertionException {
    if (number >= boundary) {
      return;
    }
    throw new AssertionException(format("number[%f] is not greater than or equal to boundary[%f].", number, boundary));
  }

  /**
   * 숫자가 기준값보다 크거나 같음을 단정한다.
   *
   * @param number   단정할 숫자.
   * @param boundary 기준값.
   * @param message  단정 실패시의 예외 메시지.
   * @throws AssertionException 단정 실패.
   */
  public static void ge(float number, float boundary, String message) throws AssertionException {
    if (number >= boundary) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 숫자가 기준값보다 크거나 같음을 단정한다.
   *
   * @param number   단정할 숫자.
   * @param boundary 기준값.
   * @throws AssertionException 단정 실패.
   */
  public static void ge(double number, double boundary) throws AssertionException {
    if (number >= boundary) {
      return;
    }
    throw new AssertionException(format("number[%f] is not greater than or equal to boundary[%f].", number, boundary));
  }

  /**
   * 숫자가 기준값보다 크거나 같음을 단정한다.
   *
   * @param number   단정할 숫자.
   * @param boundary 기준값.
   * @param message  단정 실패시의 예외 메시지.
   * @throws AssertionException 단정 실패.
   */
  public static void ge(double number, double boundary, String message) throws AssertionException {
    if (number >= boundary) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * <code>c1</code>이 <code>c2</code>보다 크거나 같음을 단정한다.
   *
   * @param c1
   * @param c2
   * @throws AssertionException 단정 실패.
   * @author Just Burrow
   * @since 2016. 9. 27.
   */
  public static <T extends Comparable<T>> void ge(T c1, T c2) throws AssertionException {
    if (null == c1) {
      throw new AssertionException("c1 is null.");
    } else if (null == c2) {
      throw new AssertionException("c2 is null.");
    } else if (c1 == c2) {
      return;
    } else if (0 >= c1.compareTo(c2)) {
      throw new AssertionException(format("c1[%s] is not greater than or equal to c2[%s].", c1, c2));
    }
  }

  /**
   * 인스턴스가 <code>null</code>임을 단정한다.
   *
   * @param instance 단정할 인스턴스.
   * @throws AssertionException 단정 실패.
   */
  public static void isNull(Object instance) throws AssertionException {
    if (null == instance) {
      return;
    }
    throw new AssertionException(format("instance[%s] is not null.", instance.toString()));
  }

  /**
   * 인스턴스가 <code>null</code>임을 단정한다.
   *
   * @param instance 단정할 인스턴스.
   * @param message  단정 실패시의 예외 메시지.
   * @throws AssertionException 단정 실패.
   */
  public static void isNull(Object instance, String message) throws AssertionException {
    if (null == instance) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 인스턴스가 <code>null</code>이 아님을 단정한다.
   *
   * @param instance 단정할 인스턴스.
   * @throws AssertionException 단정 실패.
   */
  public static void notNull(Object instance) throws AssertionException {
    if (null != instance) {
      return;
    }
    throw new AssertionException("instance is null.");
  }

  /**
   * 인스턴스가 <code>null</code>이 아님을 단정한다.
   *
   * @param instance 단정할 인스턴스.
   * @param message  단정 실패시의 예외 메시지.
   * @throws AssertionException 단정 실패.
   */
  public static void notNull(Object instance, String message) throws AssertionException {
    if (null != instance) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * @param instance
   * @param type
   * @return
   * @throws AssertionException
   * @author justburrow
   * @since 2016. 8. 1.
   */
  private static boolean isAssignable(Object instance, Class<?> type) throws AssertionException {
    if (null == type) {
      throw new AssertionException("type is null.");
    } else if (null == instance) {
      return true;
    }

    return type.isAssignableFrom(instance.getClass());
  }

  /**
   * 인스턴스가 특정 타입의 변수에 할당할 수 있는지 단정한다.
   *
   * @param instance 단정할 인스턴스.
   * @param type     할당할 변수의 타입.
   * @throws AssertionException 단정 실패.
   */
  public static void assignable(Object instance, Class<?> type) throws AssertionException {
    if (isAssignable(instance, type)) {
      return;
    }
    throw new AssertionException(
        format("instance[%s] could not assign to variable of type[%s].", instance.toString(), type.getName()));
  }

  /**
   * 인스턴스가 특정 타입의 변수에 할당할 수 있는지 단정한다.
   *
   * @param instance 단정할 인스턴스.
   * @param type     할당할 변수의 타입.
   * @param message  단정 실패시의 예외 메시지.
   * @throws AssertionException 단정 실패.
   */
  public static void assignable(Object instance, Class<?> type, String message) throws AssertionException {
    if (isAssignable(instance, type)) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * @param instance
   * @param that
   * @return
   * @author justburrow
   * @since 2016. 8. 1.
   */
  private static boolean isEqual(Object instance, Object that) {
    if (null == instance && null == that) {
      return true;
    }
    return null != instance && instance.equals(that);
  }

  /**
   * 인스턴스가 다른 인스턴스와 같은지 단정한다.
   *
   * @param instance 단정할 인스턴스.
   * @param that     비교할 인스턴스.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void equal(Object instance, Object that) throws AssertionException {
    if (isEqual(instance, that)) {
      return;
    }
    throw new AssertionException(format("instance[%s] is not equal to that[%s].", instance, that));
  }

  /**
   * 인스턴스가 다른 인스턴스와 같은지 단정한다.
   *
   * @param instance 단정할 인스턴스.
   * @param that     비교할 인스턴스.
   * @param message  단정 실패시의 예외 메시지.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void equal(Object instance, Object that, String message) throws AssertionException {
    if (isEqual(instance, that)) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 문자열의 길이가 1 이상임을 단정한다.
   *
   * @param string 단정할 문자열.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void hasLength(CharSequence string) throws AssertionException {
    if (null == string) {
      throw new AssertionException("string is null.");
    } else if (0 == string.length()) {
      throw new AssertionException("string is empty.");
    }
  }

  /**
   * 문자열의 길이가 1 이상임을 단정한다.
   *
   * @param string  단정할 문자열.
   * @param message 단정 실패시의 예외 메시지.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void hasLength(CharSequence string, String message) throws AssertionException {
    if (null != string && 0 < string.length()) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 문자열의 길이가 기준 길이와 같음을 단정한다.
   *
   * @param string 단정할 문자열.
   * @param length 기준 길이.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void length(CharSequence string, int length) throws AssertionException {
    if (null == string) {
      throw new AssertionException("string is null.");
    } else if (0 > length) {
      throw new AssertionException(format("length[%d] is negative.", length));
    } else if (length > string.length()) {
      throw new AssertionException(
          format("string[%s](%d) is shorter than length[%d]", string, string.length(), length));
    } else if (length < string.length()) {
      throw new AssertionException(
          format("string[%s](%d) is longer than length[%d]", string, string.length(), length));
    }
  }

  /**
   * 문자열의 길이가 기준 길이와 같음을 단정한다.
   *
   * @param string  단정할 문자열.
   * @param length  기준 길이.
   * @param message 단정 실패시의 예외 메시지.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void length(CharSequence string, int length, String message) throws AssertionException {
    if (null == string) {
      throw new AssertionException(message, new NullPointerException("string"));
    } else if (0 > length) {
      throw new AssertionException(message, new IllegalArgumentException(format("length[%d] is negative.", length)));
    } else if (length == string.length()) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 문자열의 길이가 기준 범위 내임을 단정한다.
   *
   * @param string 단정할 문자열.
   * @param min    최소 길이(포함).
   * @param max    최대 길이(포함).
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void length(CharSequence string, int min, int max) throws AssertionException {
    if (null == string) {
      throw new AssertionException("string");
    } else if (min > max) {
      throw new AssertionException(format("min[%d] is greater than max[%d].", min, max));
    }

    int length = string.length();
    if (min <= length && length <= max) {
      return;
    }
    throw new AssertionException(
        format("length[%d] of string[%s] is not in range [min[%d], max[%d]].", string.length(), string, min, max));
  }

  /**
   * 문자열의 길이가 기준 범위 내임을 단정한다.
   *
   * @param string  단정할 문자열.
   * @param min     최소 길이(포함).
   * @param max     최대 길이(포함).
   * @param message 단정 실패시의 예외 메시지.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void length(CharSequence string, int min, int max, String message) throws AssertionException {
    if (null == string) {
      throw new AssertionException(message, new NullPointerException("string"));
    } else if (min > max) {
      throw new AssertionException(
          message, new IllegalArgumentException(format("min[%d] is greater than max[%d].", min, max)));
    }

    int length = string.length();
    if (min <= length && length <= max) {
      return;
    }
    throw new AssertionException(message);
  }

  /**
   * 문자열의 길이가 기준값보다 짧거나 같음을 단정한다.
   *
   * @param string 단정할 문자열.
   * @param length 기준 길이(포함).
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void shorter(CharSequence string, int length) throws AssertionException {
    if (null == string) {
      throw new AssertionException("string");
    } else if (0 > length) {
      throw new AssertionException(format("length[%d] is negative.", length));
    } else if (length < string.length()) {
      throw new AssertionException(
          format("length[%d] of string[%s] is longer than boundary[%d].", string.length(), string, length));
    }
  }

  /**
   * 문자열의 길이가 기준값보다 짧거나 같음을 단정한다.
   *
   * @param string  단정할 문자열.
   * @param length  기준 길이(포함).
   * @param message 단정 실패시의 예외 메시지.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void shorter(CharSequence string, int length, String message) throws AssertionException {
    if (null == string) {
      throw new AssertionException(message, new NullPointerException("string"));
    } else if (0 > length) {
      throw new AssertionException(message, new IllegalArgumentException(format("length[%d] is negative.", length)));
    } else if (length < string.length()) {
      throw new AssertionException(message);
    }
  }

  /**
   * 문자열의 길이가 기준값보다 길거나 같음을 단정한다.
   *
   * @param string 단정할 문자열.
   * @param length 기준 길이(포함).
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void longer(CharSequence string, int length) throws AssertionException {
    if (null == string) {
      throw new AssertionException("string");
    } else if (0 > length) {
      throw new AssertionException(format("length[%d] is negative.", length));
    } else if (length > string.length()) {
      throw new AssertionException(
          format("length[%d] of string[%s] is shorter than boundary[%d].", string.length(), string, length));
    }
  }

  /**
   * 문자열의 길이가 기준값보다 길거나 같음을 단정한다.
   *
   * @param string  단정할 문자열.
   * @param length  기준 길이(포함).
   * @param message 단정 실패시의 예외 메시지.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void longer(CharSequence string, int length, String message) throws AssertionException {
    if (null == string) {
      throw new AssertionException(message, new NullPointerException("string is null."));
    } else if (0 > length) {
      throw new AssertionException(message, new IllegalArgumentException(format("length[%d] is negative.", length)));
    } else if (length > string.length()) {
      throw new AssertionException(message);
    }
  }

  /**
   * 문자열이 어떤 문자열로 시작하는지 여부를 단정한다.
   *
   * @param text 단정할 문자열.
   * @param that 비교할 문자열.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void starts(CharSequence text, CharSequence that) throws AssertionException {
    if (null == text) {
      throw new AssertionException("text is null.");
    } else if (null == that) {
      throw new AssertionException("that is null.");
    } else if (!text.subSequence(0, that.length()).equals(that)) {
      throw new AssertionException(format("text[%s] does not start with that[%s].", text, that));
    }
  }

  /**
   * 문자열이 어떤 문자열로 시작하는지 여부를 단정한다.
   *
   * @param text    단정할 문자열.
   * @param that    비교할 문자열.
   * @param message 단정 실패시의 예외 메시지.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void starts(CharSequence text, CharSequence that, String message) throws AssertionException {
    if (null == text) {
      throw new AssertionException(message, new NullPointerException("text is null."));
    } else if (null == that) {
      throw new AssertionException(message, new NullPointerException("that is null."));
    } else if (!text.subSequence(0, that.length()).equals(that)) {
      throw new AssertionException(message);
    }
  }

  /**
   * 문자열이 정규표현식에 맞는지 단정한다.
   *
   * @param string 단정할 문자열.
   * @param regex  기준 정규표현식.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void matches(CharSequence string, String regex) throws AssertionException {
    if (null == string) {
      throw new AssertionException("string is null.");
    } else if (null == regex) {
      throw new AssertionException("regex is null.");
    } else if (!(string instanceof String ? (String) string : string.toString()).matches(regex)) {
      throw new AssertionException(format("string[%s] does not match to regex[%s].", string, regex));
    }
  }

  /**
   * 문자열이 정규표현식에 맞는지 단정한다.
   *
   * @param string  단정할 문자열.
   * @param regex   기준 정규표현식.
   * @param message 단정 실패시의 예외 메시지.
   * @throws AssertionException 단정 실패.
   * @author justburrow
   * @since 2016. 8. 1.
   */
  public static void matches(CharSequence string, String regex, String message) throws AssertionException {
    if (null == string) {
      throw new AssertionException(message, new NullPointerException("string is null."));
    } else if (null == regex) {
      throw new AssertionException(message, new NullPointerException("regex is null."));
    } else if (!(string instanceof String ? (String) string : string.toString()).matches(regex)) {
      throw new AssertionException(message);
    }
  }

  /**
   * 문자열이 BCrypt 해시인지 판단한다.
   *
   * @param hash
   * @throws NullPointerException <code>hash</code>가 <code>null</code>일 때.
   * @throws AssertionException
   */
  public static void bcrypt(String hash) throws AssertionException {
    if (null == hash) {
      throw new AssertionException("hash is null.");
    } else if (!hash.matches("\\A\\$2a?\\$\\d\\d\\$[./0-9A-Za-z]{53}")) {
      throw new AssertionException(format("hash[%s] is not a bcrypt hash string."));
    }
  }

  /**
   * 문자열이 BCrypt 해시인지 판단한다.
   *
   * @param hash
   * @param message
   * @throws AssertionException
   */
  public static void bcrypt(String hash, String message) throws AssertionException {
    if (null == hash) {
      throw new AssertionException(message, new NullPointerException("hash is null."));
    } else if (!hash.matches("\\A\\$2a?\\$\\d\\d\\$[./0-9A-Za-z]{53}")) {
      throw new AssertionException(message);
    }
  }

  /**
   * 배열이 비어있지 않음을 단정한다.
   *
   * @param array
   * @throws AssertionException 배열이 <code>null</code> 이거나 비어있을 때.
   * @author Just Burrow
   * @since 2016. 9. 2.
   */
  public static <T> void notEmpty(T[] array) throws AssertionException {
    if (null == array) {
      throw new AssertionException("array is null.");
    } else if (0 == array.length) {
      throw new AssertionException("array is empty.");
    }
  }

  /**
   * 배열이 비어있지 않음을 단정한다.
   *
   * @param array
   * @param message
   * @throws AssertionException 배열이 <code>null</code> 이거나 비어있을 때.
   * @author Just Burrow
   * @since 2016. 9. 2.
   */
  public static <T> void notEmpty(T[] array, String message) throws AssertionException {
    if (null == array) {
      throw new AssertionException(message, new NullPointerException("array is null."));
    } else if (0 == array.length) {
      throw new AssertionException(message);
    }
  }

  protected Asserts() {
    throw new UnsupportedOperationException();
  }
}
