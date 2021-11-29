import javax.swing.*;
import java.awt.event.ActionListener;

public class NLPDisplay {
    private final JTextField inputText = new JTextField(15);
    private final JButton calculateButton = new JButton("Calculate");
    private final JTextField answer = new JTextField(5);

    public NLPDisplay(ActionListener controller) {
        JFrame frame = new JFrame("Natural Language Processing");
        frame.setSize(300, 200);

        calculateButton.addActionListener(controller);

        JPanel panel = new JPanel();
        panel.add(inputText);
        panel.add(calculateButton);
        panel.add(answer);
        inputText.setEditable(true);
        answer.setEditable(false);
        frame.getContentPane().add(panel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public String getInput() {
        return inputText.getText();
    }

    public void update(StringProcessor strProc) {

        if (strProc.getError()) {
            answer.setText("ERROR");
        } else {
            answer.setText(String.valueOf(strProc.getAnswer()));
        }
    }
}
