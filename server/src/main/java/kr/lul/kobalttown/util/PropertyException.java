package kr.lul.kobalttown.util;

import java.io.Serializable;
import java.util.List;

import static java.lang.String.format;
import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;

/**
 * @author justburrow
 * @since 2017. 9. 24.
 */
public class PropertyException extends Exception {
  public static class CauseProperty implements SimpleString, Serializable {
    private String name;
    private Object value;
    private String message;

    public CauseProperty(String name, Object value) {
      if (null == name || name.isEmpty()) {
        throw new IllegalArgumentException("name is null or empty.");
      }

      this.name = name;
      this.value = value;
    }

    public CauseProperty(String name, Object value, String message) {
      this(name, value);

      if (null == message || message.isEmpty()) {
        throw new IllegalArgumentException("message is null or empty.");
      }
      this.message = message;
    }

    public String getName() {
      return this.name;
    }

    public Object getValue() {
      return this.value;
    }

    public String getMessage() {
      return this.message;
    }

    @Override
    public String toSimpleString() {
      return format("%s=%s", this.name, this.value);
    }

    @Override
    public String toString() {
      return new StringBuffer(CauseProperty.class.getSimpleName())
          .append("{name='").append(this.name).append('\'')
          .append(", value=").append(this.value)
          .append(", message='").append(this.message).append('\'')
          .append('}').toString();
    }
  }

  private List<CauseProperty> properties;

  public PropertyException(CauseProperty... properties) {
    this.properties = unmodifiableList(asList(properties));
  }

  public PropertyException(String message, CauseProperty... properties) {
    super(message);
    this.properties = unmodifiableList(asList(properties));
  }

  public PropertyException(String message, Throwable cause, CauseProperty... properties) {
    super(message, cause);
    this.properties = unmodifiableList(asList(properties));
  }

  public PropertyException(Throwable cause, CauseProperty... properties) {
    super(cause);
    this.properties = unmodifiableList(asList(properties));
  }

  public List<CauseProperty> getProperties() {
    return this.properties;
  }

  @Override
  public String toString() {
    return new StringBuffer(PropertyException.class.getSimpleName())
        .append("{message=").append(getMessage())
        .append(", properties=").append(this.properties)
        .append('}').toString();
  }
}
