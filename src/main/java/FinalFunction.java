import funcs.*;

public class FinalFunction implements FuncInterface {
    private double eps;
    private Sin sin;
    private Cos cos;
    private Cot cot;
    private Log log3;
    private Log log10;
    private Log log5;
    private Log log2;

    public FinalFunction(Sin sin, Cos cos, Cot cot, Log log3, Log log5, Log log10, Log log2) {
        this.sin = sin;
        this.cos = cos;
        this.cot = cot;
        this.log3 = log3;
        this.log10 = log10;
        this.log5 = log5;
        this.log2 = log2;
    }

    public double calc(double x){
        if (x<=0){//((((cot(x) / sin(x)) - cot(x)) + cot(x)) ^ 2)
            return Math.pow((((cot.calc(x)/sin.calc(x)) - cot.calc(x)) + cot.calc(x)), 2);
        }
        else {//(((((log_3(x) + log_10(x)) - log_5(x)) + log_5(x)) - log_10(x)) * log_2(x))
            return (((((log3.calc(x) + log10.calc(x)) - log5.calc(x)) +log5.calc(x)) - log10.calc(x)) * log2.calc(x));
        }
    }

    @Override
    public void setEps(double eps) {
        this.eps = eps;
    }
}
