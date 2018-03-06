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

    private Function<Complex, Complex> function;

    public ComplexFunction(Function<Complex, Complex> function) {
        this.function = function;
    }




    public Complex apply(Complex c) {
        return function.apply(c);
    }

}
