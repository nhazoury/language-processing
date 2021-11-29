import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class StringProcessorTests {
  private final List<Character> important = new ArrayList<>(List.of('(', ')', '+', '-', '*', '/'));
  private final StringProcessor strProc = new StringProcessor(' ', important);

  @Test
  public void tokenisesWordsInEnglishSentence() {
    String str = "Not sure what to put here";
    List<String> result = strProc.splitUp(str);
    List<String> answer = new ArrayList<>(List.of("Not", "sure", "what", "to", "put", "here"));

    assertEquals(answer, result);
  }

  @Test
  public void tokenisesNumbers() {
    String str1 = "1 + (2 - 3)";
    String str2 = "1+(2-3)";
    List<String> result1 = strProc.splitUp(str1);
    List<String> answer1 = new ArrayList<>(List.of("1", "+", "(", "2", "-", "3", ")"));
    List<String> result2 = strProc.splitUp(str2);
    List<String> answer2 = answer1;

    assertEquals(answer1, result1);
    assertEquals(answer2, result2);
  }

  @Test
  public void doesShuntingYardCorrectly() {
    String str = "3 * (1 + 2)";
    List<String> tokenised = strProc.splitUp(str);

    ArrayDeque<String> answer = new ArrayDeque<>();
    answer.add("3");
    answer.add("1");
    answer.add("2");
    answer.add("+");
    answer.add("*");

    ArrayDeque<String> result = (ArrayDeque<String>) strProc.shuntingYard(tokenised);

    Assert.assertTrue(queueEquals(result, answer));
  }

  @Test
  public void doesShuntingYardCorrectly2() {
    String str = "3 + 1 + 2";
    List<String> tokenised = strProc.splitUp(str);

    ArrayDeque<String> answer = new ArrayDeque<>();
    answer.add("3");
    answer.add("1");
    answer.add("+");
    answer.add("2");
    answer.add("+");

    ArrayDeque<String> result = (ArrayDeque<String>) strProc.shuntingYard(tokenised);

    Assert.assertTrue(queueEquals(result, answer));
  }

  private boolean queueEquals(ArrayDeque<String> a, ArrayDeque<String> b) {
    for (String aItem : a) {
      String bItem = b.pop();
      if (!aItem.equals(bItem)) {
        return false;
      }
    }
    return true;
  }

  @Test
  public void correctReversePolishNotationEvaluation() {
    String input = "3 4 2 * +";
    strProc.setInput(input);
    double answer = 11;
    strProc.evalRPN();
    double result = strProc.getAnswer();
    assertEquals(answer, result, 0.1);
  }

  @Test
  public void correctNormalEvaluation() {

    String input = "3 * (4 + 3)";
    strProc.setInput(input);
    double answer = 21;
    strProc.eval();
    double result = strProc.getAnswer();
    assertEquals(answer, result, 0.1);
  }


}
