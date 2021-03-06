import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Calculator {
  private final NLPDisplay view = new NLPDisplay(new Controller());
  private final List<Character> important = new ArrayList<>(List.of('(', ')', '+', '-', '*', '/'));
  private final StringProcessor strProc;

  public Calculator() {
    strProc = new StringProcessor(' ', important);
    strProc.setView(view);
  }

  public static void main(String[] args) {
    new Calculator();
  }

  class Controller implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      String input = view.getInput();
      strProc.setInput(input);
      strProc.eval();
    }
  }
}
