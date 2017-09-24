package kr.lul.kobalttown.support.borderline;

import kr.lul.kobalttown.domain.account.AccountSimple;

import static kr.lul.kobalttown.util.Asserts.notNull;

/**
 * 현재 스레드를 실행한 사용자 정보를 가지는 커맨드 오브젝트.
 *
 * @author justburrow
 * @since 2017. 9. 20.
 */
public abstract class OperatorCmd {
  protected final AccountSimple operator;

  protected OperatorCmd(AccountSimple operator) {
    notNull(operator, "operator");

    this.operator = operator;
  }

  public AccountSimple getOperator() {
    return this.operator;
  }
}
