package kr.lul.kobalttown.service.message.params;

import java.util.Map;

import static java.util.Collections.emptyMap;
import static kr.lul.kobalttown.util.Asserts.hasLength;
import static kr.lul.kobalttown.util.Asserts.notNull;

/**
 * @author justburrow
 * @since 2017. 8. 31.
 */
public class TemplateEmailMessageParams implements TemplateMessageParams<String> {
  private Address<String>     from;
  private Address<String>     to;
  private String              title;
  private String              template;
  private Map<String, Object> model;

  public TemplateEmailMessageParams(Address<String> from, Address<String> to, String title, String template, Map<String, Object>
      model) {
    notNull(from, "from");
    notNull(to, "to");
    hasLength(title, "title");
    hasLength(template, "template");

    this.from = from;
    this.to = to;
    this.title = title;
    this.template = template;
    this.model = (null == model ? emptyMap() : model);
  }

  @Override
  public Address<String> from() {
    return from;
  }

  @Override
  public Address<String> to() {
    return to;
  }

  @Override
  public String title() {
    return title;
  }

  @Override
  public String template() {
    return template;
  }

  @Override
  public Map<String, Object> model() {
    return model;
  }

  @Override
  public String toString() {
    return new StringBuffer(TemplateEmailMessageParams.class.getSimpleName())
        .append("{from=").append(from)
        .append(", to=").append(to)
        .append(", title='").append(title).append('\'')
        .append(", template='").append(template).append('\'')
        .append(", model=").append(model)
        .append('}').toString();
  }
}
