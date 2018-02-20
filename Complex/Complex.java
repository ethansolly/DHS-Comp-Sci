import java.awt.*;

public class Complex {

    public static final Complex ZERO = new Complex(0, 0);
    public static final Complex ONE = new Complex(1, 0);
    public static final Complex I = new Complex(0, 1);

    double a, b;

    public Complex(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public static Complex cis(double r, double angle) {
        return new Complex(r*Math.cos(angle), r*Math.sin(angle));
    }

    public static Complex cis(double angle) {
        return cis(1, angle);
    }

    public static Complex exp(Complex c) {
        return cis(Math.exp(c.a), c.b);
    }

    public static Complex sin(Complex c) {
        return new Complex(Math.sin(c.a)*Math.cosh(c.b), Math.cos(c.a)*Math.sinh(c.b));
    }

    public static Complex cos(Complex c) {
        return new Complex(Math.cos(c.a)*Math.cosh(c.b), Math.sin(c.a)*Math.sinh(c.b));
    }

    ////////////////

    public double abs() {
        return Math.sqrt(a*a + b*b);
    }

    public double arg() {
        return Math.atan2(b, a);
    }

    public double real() {
        return a;
    }

    public double imag() {
        return b;
    }

    /////////////////

    public Complex plus(Complex c) {
        return new Complex(a + c.a, b + c.b);
    }

    public Complex plus(double d) {
        return new Complex(a + d, b);
    }

    public Complex plusI(double d) {
        return new Complex(a, b + d);
    }

    public Complex minus(Complex c) {
        return new Complex(a - c.a, b - c.b);
    }

    public Complex minus(double d) {
        return new Complex(a - d, b);
    }

    public Complex minusI(double d) {
        return new Complex(a, b - d);
    }

    public Complex neg() { return new Complex(-a, -b); }

    public Complex times(Complex c) {
        return new Complex(a*c.a - b*c.b, a*c.b + b*c.a);
    }

    public Complex times(double d) {
        return new Complex(a*d, b*d);
    }

    public Complex timesI(double d) {
        return new Complex(-b*d, a*d);
    }

    public Complex divide(Complex c) {
        return new Complex(a - c.a, b - c.b);
    }

    public Complex divide(double d) {
        return new Complex(a - d, b);
    }

    public Complex divideI(double d) {
        return new Complex(a, b - d);
    }

    public Complex log() {
        return new Complex(Math.log(abs()), arg());
    }

    public Complex pow(Complex c) {
        if (abs()==0)
            return ZERO;
        return cis(Math.pow(abs(), c.a) * Math.exp(-arg()*c.b), arg()*c.a + c.b*Math.log(abs()));
    }

    public Complex pow(double d) {
        return cis(Math.pow(abs(), d), arg()*d);
    }

    public Complex copy() {
        return new Complex(a, b);
    }

    //////////////////

    public Color getColor() {
        float H = (float)(arg()/(2*Math.PI));
        float S = (abs() < 1.0/16.0 || (abs() < 17.0/16.0 && abs() > 15.0/16.0))? 0.0f : 1.0f;
        float L = (float)Math.exp(-abs());
        return Color.getHSBColor(H, S, L);
    }

    ///////////////

    public String toString() {
        return a + " + " + b + "i";
    }



}
