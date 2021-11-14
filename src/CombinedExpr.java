public class CombinedExpr implements Expression {
    Expression expr1;
    Expression expr2;
    Operator op;

    public double eval() {
        int answer;
        return switch (op) {
            case ADD -> expr1.eval() + expr2.eval();
            case SUB -> expr1.eval() - expr2.eval();
            case MUL -> expr1.eval() * expr2.eval();
            case DIV -> expr1.eval() / expr2.eval();
        };
    }

    public void setExpr1(Expression expr1) {
        this.expr1 = expr1;
    }

    public void setExpr2(Expression expr2) {
        this.expr2 = expr2;
    }

    public void setOp(Operator op) {
        this.op = op;
    }
}


