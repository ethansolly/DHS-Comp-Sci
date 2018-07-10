import java.awt.*;

public class Complex implements Cloneable {

    public static final Complex ZERO = new Complex(0, 0);
    public static final Complex ONE = new Complex(1, 0);
    public static final Complex I = new Complex(0, 1);
    public static final Complex PII = new Complex(0, Math.PI);
    public static final Complex TWOPII = new Complex(0, 2*Math.PI);

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

    public static Complex icis(double angle) { return cis(angle).timesI(1); }

    public static Complex exp(Complex c) {
        return cis(Math.exp(c.a), c.b);
    }

    public static Complex sin(Complex c) {
        return new Complex(Math.sin(c.a)*Math.cosh(c.b), Math.cos(c.a)*Math.sinh(c.b));
    }

    public static Complex cos(Complex c) {
        return new Complex(Math.cos(c.a)*Math.cosh(c.b), -Math.sin(c.a)*Math.sinh(c.b));
    }
    
    public static Complex sinh(Complex c) {
        return new Complex(Math.sinh(c.a)*Math.cos(c.b), Math.cosh(c.a)*Math.sin(c.b));
    }

    public static Complex cosh(Complex c) {
        return new Complex(Math.cosh(c.a)*Math.cos(c.b), Math.sinh(c.a)*Math.sin(c.b));
    }


    public static Complex arcsin(Complex c) {
        return c.timesI(1).plus(ONE.minus(c.pow(2)).pow(0.5)).log().timesI(-1);
    }

    public static Complex arccos(Complex c) {
        return c.plus(ONE.minus(c.pow(2)).pow(0.5).timesI(1)).log().timesI(-1);
    }

    public static Complex arctan(Complex c) {
        return I.plus(c).divide(I.minus(c)).log().timesI(0.5);
    }

    public static Complex log(Complex c) {
        return c.log();
    }

    public static Complex thetaFunction(int a, int b, Complex z, Complex t) {
        if (a == 0 && b == 0) {
            return thetaFunction(z, t);
        }
        else if (a == 0 && b == 1) {
            return thetaFunction(z.plus(0.5), t);
        }
        else if (a == 1 && b == 0) {
            return exp(PII.times(t.times(0.25).plus(z))).times(thetaFunction(z.plus(t.times(0.5)), t));
        }
        else if (a == 1 && b == 1) {
            return exp(PII.times(t.times(0.25).plus(z.plus(0.5)))).times(thetaFunction(z.plus(t.plusOne().times(0.5)), t));
        }
        else {
            return ZERO;
        }
    }

    private static Complex thetaFunction(Complex z, Complex t) {
        Complex q = exp(t.times(PII));
        Complex m = exp(z.times(TWOPII));
        Complex sum = ONE; //From when n = 0, q^n^2 * m = 1
        int n = 1;
        int P = 8;
        int maxIter = (int)Math.ceil(Math.sqrt((P+2)/(Math.PI*t.b*Math.log(Math.E)/Math.log(2))))+1;
        while (n < maxIter) {
            Complex subSum = q.pow(n*n).times(m.pow(n).plus(m.pow(-n)));
            sum = sum.plus(subSum);
            n++;
        }
        return sum;
    }

    public static Complex weierstrassElliptic(Complex z, Complex t) {
        Complex theta   = thetaFunction(0, 0, ZERO, t);
        Complex theta10 = thetaFunction(1, 0, ZERO, t);
        Complex theta01 = thetaFunction(0, 1, z, t);
        Complex theta11 = thetaFunction(1, 1, z, t);
        return (theta.squared().times(theta10.squared()).times(theta01.squared().divide(theta11.squared())).times(Math.PI*Math.PI).minus(theta.pow(4).plus(theta10.pow(4)).times(Math.PI*Math.PI/3)));
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

    public Complex conj() {
        return new Complex(a, -b);
    }
    public Complex plus(Complex c) {
        return new Complex(a + c.a, b + c.b);
    }

    public Complex plus(double d) {
        return new Complex(a + d, b);
    }

    public Complex plusOne() {
        return plus(1);
    }

    public Complex plusTwo() {
        return plus(2);
    }

    public Complex plusI(double d) {
        return new Complex(a, b + d);
    }

    public Complex plusI() {
        return plusI(1);
    }

    public Complex plus2i() {
        return plusI(2);
    }

    public Complex minus(Complex c) {
        return new Complex(a - c.a, b - c.b);
    }

    public Complex minus(double d) {
        return new Complex(a - d, b);
    }

    public Complex minusOne() {
        return minus(1);
    }

    public Complex minusTwo() {
        return minus(2);
    }

    public Complex minusI(double d) {
        return new Complex(a, b - d);
    }

    public Complex minusI() {
        return minusI(1);
    }

    public Complex minus2i() {
        return minusI(2);
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

    public Complex timesI() {
        return timesI(1);
    }

    public Complex divide(Complex c) {
        double k = c.a*c.a + c.b*c.b;
        return new Complex((a*c.a + b*c.b)/k, (b*c.a - a*c.b)/k);
    }

    public Complex divide(double d) {
        return new Complex(a/d, b/d);
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

    public Complex squared() {
        return pow(2);
    }

    public Complex sqrt() {
        return pow(0.5);
    }

    public Complex inv() {
        return pow(-1);
    }

    public Complex clone() {
        return new Complex(a, b);
    }

    //////////////////

    boolean grid = true;

    public Color getColor() {
        float H = (float)((arg()/(2*Math.PI)));
        float S = (
                !grid? 1
                        :(abs() < 1.0/16.0)? 0 //A zero
                        :(abs() > 1 && abs() < 18.0/16.0)? 0.1f //Outer section of unit circle
                        :(abs() < 1 && abs() > 14.0/16.0)? 0.2f //Inner section
                        : 1 //else
        );
        float L = (float)(
                ((abs() > 18.0/16.0 || (abs() < 14.0/16.0 && abs() > 1.0/16.0)) && (grid && ((a % 1 < 1.0/16.0 && a % 1 > -1.0/16.0) || (b % 1 < 1.0/16.0 && b % 1 > -1.0/16.0))))? 0 //Blacken grid
                        : ((abs() < 18.0/16.0) && (abs() > 14.0/16.0)) ? 1  //Brighten anything within circle border
                        : (abs() > 1.0/16.0) ? ((((Math.log(abs()))/Math.log(2) % 1) + 1) % 1)     //Else, do the step effect as you go further out
                        : 1
        );
        return Color.getHSBColor(H, S, L);
    }

    ///////////////

    public String toString() {
        return a + " + " + b + "i";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Complex) {
            Complex cObj = (Complex) obj;
            return (a == cObj.a) && (b == cObj.b);
        }
        return false;
    }
}
