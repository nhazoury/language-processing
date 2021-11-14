import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import org.junit.Assert.*;

public class StringProcessorTests {
    private final StringProcessor strProc = new StringProcessor();
    private final List<Character> important = new ArrayList<>(List.of('(', ')', '+', '-', '*', '/'));

    @Test
    public void tokenisesWordsInEnglishSentence() {
        String str = "Not sure what to put here";
        List<String> result = strProc.splitUp(str, ' ', important);
        List<String> answer = new ArrayList<>(List.of("Not", "sure", "what", "to", "put", "here"));

        Assert.assertEquals(answer, result);
    }

    @Test
    public void tokenisesNumbers() {
        String str1 = "1 + (2 - 3)";
        String str2 = "1+(2-3)";
        List<String> result1 = strProc.splitUp(str1, ' ', important);
        List<String> answer1 = new ArrayList<>(List.of("1", "+", "(", "2", "-", "3", ")"));
        List<String> result2 = strProc.splitUp(str2, ' ', important);
        List<String> answer2 = answer1;

        Assert.assertEquals(answer1, result1);
        Assert.assertEquals(answer2, result2);
    }

    @Test
    public void doesShuntingYardCorrectly() {
        String str = "3 * (1 + 2)";
        List<String> tokenised = strProc.splitUp(str, ' ', important);

        ArrayDeque<String> answer = new ArrayDeque<>();
        answer.add("3");
        answer.add("1");
        answer.add("2");
        answer.add("+");
        answer.add("*");

        ArrayDeque<String> result = (ArrayDeque<String>) strProc.shuntingyard(tokenised);

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
}
