import java.util.*;

public class StringProcessor {
  char divider;
  List<Character> important;
  String input;
  private int answer;
  private boolean error;
  private NLPDisplay view;

  public StringProcessor(char divider, List<Character> important) {
    this.divider = divider;
    this.important = important;
  }

  public boolean getError() {
    return error;
  }

  public int getAnswer() {
    return answer;
  }

  public void setInput(String input) {
    this.input = input;
  }

  public void setView(NLPDisplay view) {
    this.view = view;
  }

  /* Splits string into words without spaces as a List of strings.
     Existing functions already do this, but why not try from scratch?
   */
  public List<String> splitUp(String str) {
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

  public static Queue<String> toRPNQueue(List<String> input) {
    return new ArrayDeque<>(input);
  }

  /* Only pass maths into this!!
     Uses Dijkstra's shunting yard algorithm */
  public Queue<String> shuntingYard(List<String> expressions) {
    Queue<String> output = new ArrayDeque<>();

    // stack of operators, but not true operators like + or / since these also
    // include ( and )
    Stack<String> opStack = new Stack<>();
    for (String str : expressions) {
      if (isNumber(str)) {
        output.add(str);
      } else if (str.equals("(")) {
        opStack.push(str);
      } else if (str.equals(")")) {
        while (!opStack.peek().equals("(")) {
          output.add(opStack.pop());
        }
        opStack.pop();
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
      }

    }

    while (!opStack.isEmpty()) {
      output.add(opStack.pop());
    }

    return output;
  }

  private static boolean isNumber(String num) {
    for (int i = 0; i < num.length(); i++) {
      char c = num.charAt(i);
      if (!Character.isDigit(c)) {
        return false;
      }
    }
    return true;
  }

  // taking reverse polish notation, generate an answer
  public static int calc(Queue<String> processed) throws Exception {
    Stack<Integer> stack = new Stack<>();
    while (!processed.isEmpty()) {
      String item = processed.poll();
      if (isNumber(item)) {
        stack.add(Integer.parseInt(item));
      } else {
        int x, y;
        try {
          y = stack.pop();
          x = stack.pop();
        } catch (EmptyStackException e) {
          throw new Exception("Invalid input - try again.");
        }

        int result = Operator.evalStr(x, y, item);
        stack.push(result);
      }
    }

    if (stack.size() != 1) {
      throw new Exception("Invalid input - try again.");
    }

    return stack.pop();
  }

  public void evalRPN() {
    try {
      List<String> tokenised = splitUp(input);
      Queue<String> tokenisedQueue = toRPNQueue(tokenised);
      answer = StringProcessor.calc(tokenisedQueue);
    } catch (Exception e) {
      error = true;
    }
  }

  public void eval() {
    try {
      List<String> tokenised = splitUp(input);
      Queue<String> tokenisedQueue = shuntingYard(tokenised);
      answer = StringProcessor.calc(tokenisedQueue);
      error = false;
    } catch (Exception e) {
      error = true;
    }
    if (view != null) {
      view.update(this);
    }
  }
}
