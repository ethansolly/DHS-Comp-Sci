import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Function;

public class ComplexFunction {

    public static final ComplexFunction ZERO = new ComplexFunction(c -> Complex.ZERO, "Zero");
    public static final ComplexFunction ONE = new ComplexFunction(c -> Complex.ONE, "One");
    public static final ComplexFunction I = new ComplexFunction(c -> Complex.I, "I");

    public static final ComplexFunction IDENTITY = new ComplexFunction(c -> c, "Identity");
    public static final ComplexFunction INVERSE = new ComplexFunction(c -> c.pow(-1), "Inverse");
    public static final ComplexFunction EXP = new ComplexFunction(Complex::exp, "Exponential");

    public static final ComplexFunction SIN = new ComplexFunction(Complex::sin, "Sine");
    public static final ComplexFunction COS = new ComplexFunction(Complex::cos, "Cosine");
    public static final ComplexFunction SINH = new ComplexFunction(Complex::sinh, "Hyperbolic Sine");
    public static final ComplexFunction COSH = new ComplexFunction(Complex::cosh, "Hyperbolic Cosine");
    public static final ComplexFunction ARCSIN = new ComplexFunction(Complex::arcsin, "Inverse Sine");
    public static final ComplexFunction ARCCOS = new ComplexFunction(Complex::arccos, "Inverse Cosine");

    public static final ComplexFunction ERICA = new ComplexFunction(c -> c.squared().minusOne().times(c.minusTwo().minusI().squared()).divide(c.squared().plusTwo().plus2i()), "Erica");

    public static final ComplexFunction LOG = new ComplexFunction(c -> c.log(), "Logarithm");

    public static final ComplexFunction EXPMAP = new ComplexFunction(ComplexFunction::exponentialMap, "Exponential Map");
    public static Complex exponentialMap(Complex c) {
        int iter = 0;
        Complex z = Complex.ZERO;
        while (iter < 100) {
            z = Complex.exp(z).plus(c);
            iter++;
        }
        return z;
    }

    public static Complex lambertW(Complex c) {
        Complex w = Complex.log(c).minus(Complex.log(Complex.log(c)));
        Complex wtemp;
        int iter = 0;
        boolean isAccurate;
        double error = Math.pow(10, -3);
        do {
            Complex exp = Complex.exp(w);
            wtemp = w.minus(w.times(exp).minus(c).divide(exp.times(w.plus(1)).minus(w.plus(2).times(w.times(exp).minus(c)).divide(w.times(2).plus(2)))));
            isAccurate = Math.abs(w.minus(wtemp).abs()) < error;
            w = wtemp;
            iter++;
        } while (!isAccurate && iter < 100);
        return w;
    }

    public static final ComplexFunction GAUSSMAP = new ComplexFunction(c -> gaussMap(c), "Gauss Map");
    public static Complex gaussMap(Complex c) {
        int iter = 0;
        Complex z = Complex.ZERO;
        while (iter < 100) {
            z = Complex.exp(z.pow(2).neg().times(c.real())).plus(c.imag());
            iter++;
        }
        return z;
    }

    /**
     * @param function A function where the first argument is z' and the second is the initial value z
     */
    public static ComplexFunction iter(BiFunction<Complex, Complex, Complex> function, int iter, String name) {
        return new ComplexFunction(c -> {
            Complex z = Complex.ZERO;
            for (int i = 0; i < iter; i++) {
                z = function.apply(z, c);
            }
            return z;
        }, name + " iterated " + iter + " times");
    }

    private Function<Complex, Complex> function;

    private String name;

    public ComplexFunction(Function<Complex, Complex> function, String name) {
        this.function = function;
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public Complex apply(Complex c) {
        return function.apply(c);
    }

    @Override
    public String toString() {
        return name;
    }
}
