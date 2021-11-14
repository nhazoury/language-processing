public class ExpressionBuilder {
    private Expression expr1;
    private Expression expr2;
    private Operator op;
    private Integer num;

    public ExpressionBuilder() {
        this.expr1 = null;
        this.expr2 = null;
        this.op = null;
        this.num = null;
    }

    public ExpressionBuilder withExpr1(Expression expr1) {
        this.expr1 = expr1;
        return this;
    }

    public ExpressionBuilder withExpr2(Expression expr2) {
        this.expr2 = expr2;
        return this;
    }

    public ExpressionBuilder withOperator(Operator op) {
        this.op = op;
        return this;
    }

    public ExpressionBuilder withNum(int num) {
        this.num = num;
        return this;
    }

    public Expression build() {
        return new Expression(expr1, expr2, op, num);
    }
}
