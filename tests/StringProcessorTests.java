import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert.*;

public class StringProcessorTests {
    private StringProcessor strProc = new StringProcessor();

    @Test
    public void tokenisesWordsInEnglishSentence() {
        String str = "Not sure what to put here";
        List<String> result = strProc.splitUp(str, ' ', new ArrayList<>(List.of('(', ')')));
        List<String> answer = new ArrayList<>(List.of("Not", "sure", "what", "to", "put", "here"));

        Assert.assertEquals(answer, result);
    }

    @Test
    public void tokenisesNumbers() {
        String str = "1 + (2 - 3)";
        List<String> result = strProc.splitUp(str, ' ', new ArrayList<>(List.of('(', ')')));
        List<String> answer = new ArrayList<>(List.of("1", "+", "(", "2", "-", "3", ")"));

        Assert.assertEquals(answer, result);
    }
}
