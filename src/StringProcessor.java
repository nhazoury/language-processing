import java.util.ArrayList;
import java.util.List;

public class StringProcessor {

    public StringProcessor() {
    }

    /* Splits string into words without spaces as a List of strings.
    Existing functions already do this, but why not try from scratch?
     */
    public List<String> splitUp(String str) {
        ArrayList<String> ans = new ArrayList<>();
        StringBuilder word = new StringBuilder();
        str = str + ' ';
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c != ' ') {
                word.append(c);
            } else {
                ans.add(word.toString());
                word = new StringBuilder();
            }
        }

        return ans;
    }
}
