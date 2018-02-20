package com.ethansolly.cantrip;

/**
 * Created by Ethan Sollenberger on 4/5/2017.
 */
public class Complex {

    private double real, imag;

    public Complex(double real, double imag) {
        this.real = real;
        this.imag = imag;
    }

    public Complex plus(Complex b) {
        return new Complex(real + b.real, imag + b.imag);
    }

    public Complex times(Complex b) {
        return new Complex(real * b.real - imag * b.imag, imag * b.real + real * b.imag);
    }

    public double abs() {
        return Math.sqrt(real*real + imag*imag);
    }

    public double re() {
        return real;
    }

    public double im() {
        return imag;
    }

    public String toString() {
        return real + " + " + imag + "i";
    }

}
