package funcs;

import static java.lang.Math.abs;

public class Ln implements FuncInterface {
    private double eps;
    public Ln(double eps){
        this.eps = eps;
    }
    public double calc(double x){
        if (x < 0) return Double.NaN;
        if (x == 0) return Double.NEGATIVE_INFINITY;
        double result = Double.MAX_VALUE;
        double n = 1.0;
        double newResult = 0.0;

        while (abs(result - newResult) > eps) {
            result = newResult;
            newResult += (Math.pow((x - 1) / (x + 1), 2 * n - 1)) / (2 * n - 1);
            n++;
        }
        return 2 * newResult;
    }

    public double stubCalculate(double x){
        return Math.log(x);
    }

    public void setEps(double eps) {
        this.eps = eps;
    }
}
