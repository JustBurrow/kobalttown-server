package kr.lul.kobalttown.support.service;

import kr.lul.kobalttown.domain.account.Account;

import static kr.lul.kobalttown.util.Asserts.notNull;

/**
 * @author justburrow
 * @since 2017. 9. 21.
 */
public abstract class OperatorParams {
  protected final Account operator;

  protected OperatorParams(Account operator) {
    notNull(operator, "operator");
    this.operator = operator;
  }

  public Account getOperator() {
    return this.operator;
  }

  @Override
  public String toString() {
    return this.operator.toSimpleString();
  }
}
