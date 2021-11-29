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

    public static double evalStr(double x, double y, String op) {
        return eval(x, y, translate(op));
    }

    public static double eval(double x, double y, Operator op) {
        return switch (op) {
            case ADD -> x + y;
            case SUB -> x - y;
            case MUL -> x * y;
            case DIV -> x / y;
        };
    }

    public int getPrecedence() {
        return precedence;
    }

    public String getStrVersion() {
        return strVersion;
    }
}
