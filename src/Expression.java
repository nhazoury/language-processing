public class Expression {
    Expression expr1;
    Expression expr2;
    Operator op;
    Integer num;

    public Expression(Expression expr1, Expression expr2, Operator op, Integer num) {
        this.expr1 = expr1;
        this.expr2 = expr2;
        this.op = op;
        this.num = num;
    }

    public double eval() {
        int answer;
        return switch (op) {
            case ADD -> expr1.eval() + expr2.eval();
            case SUB -> expr1.eval() - expr2.eval();
            case MUL -> expr1.eval() * expr2.eval();
            case DIV -> expr1.eval() / expr2.eval();
            default -> num;
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

    public void addArgument(int num) {
        if (this.num == null) {
            this.num = num;
        } else {
            expr1 = new ExpressionBuilder().withNum(this.num).build();
            expr2 = new ExpressionBuilder().withNum(num).build();
            this.num = null;
        }
    }
}


