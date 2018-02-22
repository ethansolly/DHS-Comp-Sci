import java.util.Random;
import java.util.function.Function;

public class ComplexFunction {

    public static final ComplexFunction ZERO = new ComplexFunction(c -> Complex.ZERO);
    public static final ComplexFunction ONE = new ComplexFunction(c -> Complex.ONE);
    public static final ComplexFunction I = new ComplexFunction(c -> Complex.I);

    public static final ComplexFunction IDENTITY = new ComplexFunction(c -> c);
    public static final ComplexFunction INVERSE = new ComplexFunction(c -> c.pow(-1));
    public static final ComplexFunction EXP = new ComplexFunction(Complex::exp);

    public static final ComplexFunction SIN = new ComplexFunction(Complex::sin);
    public static final ComplexFunction COS = new ComplexFunction(Complex::cos);
    public static final ComplexFunction SINH = new ComplexFunction(Complex::sinh);
    public static final ComplexFunction COSH = new ComplexFunction(Complex::cosh);
    public static final ComplexFunction ARCSIN = new ComplexFunction(Complex::arcsin);
    public static final ComplexFunction ARCCOS = new ComplexFunction(Complex::arccos);

    public static final ComplexFunction ERICA = new ComplexFunction(c -> Complex.erica(c, 0));

    public static final ComplexFunction GAMMA = new ComplexFunction(Complex::gamma);

    public static final ComplexFunction LOG = new ComplexFunction(Complex::log);

    /* D. Kouznetsov (July 2009).
     * "Solution of F(z+1) = exp(F(z)) in complex z-plane"
     * (PDF).
     * Mathematics of Computation.
     * 78 (267): 1647–1670. doi:10.1090/S0025-5718-09-02188-7.
     *
     * http://www.ams.org/journals/mcom/2009-78-267/S0025-5718-09-02188-7/S0025-5718-09-02188-7.pdf
     */
    private static Complex xtremeHelper(Complex c) {
        if (c.real() <= -1) {
            return xtremeHelper(c.plus(1)).log();
        }
        if (c.real() > -1 && c.real() <= 0) {
            Complex s2 = Complex.exp(Complex.exp(c.minus(2.51))).minus(0.6).plus(c.plus(1).times(0.08));
            Complex s21 = Complex.exp(Complex.exp(c.plus(1).minus(2.51))).minus(0.6).plus(c.plus(1).plus(1).times(0.08));
            Complex fit2 = c.plus(2).log().plus(c.plus(1).times(c.divide(2).times(Complex.exp(s2))).times(Math.E-2+Math.log(4/3)).plus(1-Math.log(2)));
            Complex fit21 = c.plus(3).log().plus(c.plus(2).times(c.plus(1).divide(2).times(Complex.exp(s21))).times(Math.E-2+Math.log(4/3)).plus(1-Math.log(2)));
            Complex fit3 = fit2.times(0.6).plus(fit21.log().times(0.4));
            return fit3;
        }
        return Complex.exp(xtremeHelper(c.minus(1)));
    }

    public static final ComplexFunction XTREME = new ComplexFunction(c -> xtremeHelper(c));

    private Function<Complex, Complex> function;

    public ComplexFunction(Function<Complex, Complex> function) {
        this.function = function;
    }

    public Complex apply(Complex c) {
        return function.apply(c);
    }

    /** path is defined from c1 to c2 from t = 0 to 1, respectively
     *  pathPrime is the derivative of path with respect to time
     */
    public Complex contour(Function<Double, Complex> path, Function<Double, Complex> pathPrime) {
        double width = 0.001;
        Complex area = Complex.ZERO;
        for (double t = 0; t <= 1; t+=width) {
            area = area.plus(apply(path.apply(t))).times(pathPrime.apply(t)).times(width);
        }
        return area;
    }

    public Complex contour(Complex a) {
        return contour(t -> Complex.cis(t).plus(a), Complex::icis);
    }

    public Complex contour() {
        return contour(Complex.ZERO);
    }

    public ComplexFunction derivative() {
        return new ComplexFunction(a ->
                new ComplexFunction(z ->
                        this.apply(z).divide(z.minus(a).pow(2))).contour(
                                t -> Complex.cis(t).plus(a),
                                t -> Complex.cis(t).times(Complex.I)).divide(Complex.I.times(2*Math.PI)));
    }


}
