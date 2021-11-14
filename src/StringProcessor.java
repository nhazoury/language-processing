import java.util.*;

public class StringProcessor {

  public StringProcessor() {}

  /* Splits string into words without spaces as a List of strings.
     Existing functions already do this, but why not try from scratch?
   */
  public List<String> splitUp(String str, char divider, List<Character> important) {
    ArrayList<String> ans = new ArrayList<>();
    StringBuilder word = new StringBuilder();

    // just to make sure the final word is actually included
    str = str + ' ';

    for (int i = 0; i < str.length(); i++) {
      char c = str.charAt(i);
      if (c == divider) {
        word = cut(word, ans);
      } else if (important.contains(c)) {
        word = cut(word, ans);
        word.append(c);
        word = cut(word, ans);
      } else {
        word.append(c);
      }
    }
    ans.removeAll(Collections.singleton(""));
    return ans;
  }

  private StringBuilder cut(StringBuilder currentWord, List<String> sentence) {
    sentence.add(currentWord.toString());
    return new StringBuilder();
  }

  /* Only pass maths into this!!
     Uses Djikstra's shunting yard algorithm */
  public Queue<String> shuntingyard(List<String> expressions) {
    Queue<String> output = new ArrayDeque<>();

    // stack of operators, but not true operators since these also
    // include ( and )
    Stack<String> opStack = new Stack<>();
    for (String str : expressions) {
      if (isNumber(str)) {
        output.add(str);
      } else if (Operator.isOperator(str)) {
        if (!opStack.isEmpty()) {
          String top = opStack.peek();
          Operator currOp = Operator.translate(str);
          Operator topOp = Operator.translate(top);

          while (!opStack.isEmpty() && !top.equals("(") && Operator.less(currOp, topOp)) {
            output.add(opStack.pop());
            top = opStack.peek();
            topOp = Operator.translate(top);
          }
        }

        opStack.push(str);
      } else if (str.equals("(")) {
        opStack.push(str);
      } else if (str.equals(")")) {
        while (!opStack.peek().equals("(")) {
          output.add(opStack.pop());
        }
        opStack.pop();
      }
    }

    while (!opStack.isEmpty()) {
      output.add(opStack.pop());
    }

    return output;
  }

  public boolean isNumber(String num) {
    for (int i = 0; i < num.length(); i++) {
      char c = num.charAt(i);
      if (!Character.isDigit(c)) {
        return false;
      }
    }
    return true;
  }

  // taking reverse polish notation, generate an expression
  public Expression generateExpression(Queue<String> processed) {
    /*
    Expression expr = new ExpressionBuilder().build();
    for (String item : processed) {
      if (isNumber(item)) {
        int num = Integer.parseInt(item);
        expr.addArgument(num);
      }
    }
    */
    return null;
  }
}
