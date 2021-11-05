public class CombinedExpr implements Expression {
    Expression expr1;
    Expression expr2;
    Operator op;

    private enum Operator {
        ADD,
        SUB,
        MUL,
        DIV
    }

    public int eval() {
        int answer;
        return switch (op) {
            case ADD -> expr1.eval() + expr2.eval();
            case SUB -> expr1.eval() - expr2.eval();
            case MUL -> expr1.eval() * expr2.eval();
            case DIV -> expr1.eval() / expr2.eval();
        };
    }
}
