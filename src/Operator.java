import java.util.ArrayList;
import java.util.List;

public enum Operator {
    ADD(0, "+"),
    SUB(0, "-"),
    MUL(1, "*"),
    DIV(1, "/");
    private final int precedence;
    private final String strVersion;

    Operator(int precedence, String strVersion) {
        this.precedence = precedence;
        this.strVersion = strVersion;
    }

    public int getPrecedence() {
        return precedence;
    }

    public String getStrVersion() {
        return strVersion;
    }

    public static boolean less(Operator less, Operator more) {
        return less.precedence <= more.precedence;
    }

    public static boolean isOperator(String str) {
        for (Operator op : Operator.values()) {
            if (op.getStrVersion().equals(str)) {
                return true;
            }
        }
        return false;
    }

    public static Operator translate(String str) {
        for (Operator op : Operator.values()) {
            if (op.getStrVersion().equals(str)) {
                return op;
            }
        }
        return null;
    }
}