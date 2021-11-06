import java.util.ArrayList;
import java.util.List;

public class StringProcessor {

    public StringProcessor() {
    }

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
                word = split(word, ans);
            } else if (important.contains(c)) {
                word = split(word, ans);
                word.append(c);
            } else {
                word.append(c);
            }
        }

        return ans;
    }

    private StringBuilder split(StringBuilder currentWord, List<String> sentence) {
        sentence.add(currentWord.toString());
        return new StringBuilder();
    }

    /* Only pass maths into this!! */
    public Expression process(List<String> expressions) {
        for (String str : expressions) {
            // TODO: process maths, stack?, etc.
        }
        return null;
    }
}
