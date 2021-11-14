public class SimpleExpr implements Expression {
    double num;

    public SimpleExpr(double num) {
        this.num = num;
    }

    public double eval() {
        return num;
    }
}
