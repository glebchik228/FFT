public class Complex {
    private double real;
    private double imag;

    public Complex(double real, double imag) {
        this.real = real;
        this.imag = imag;
    }

    public double getReal() {
        return real;
    }

    public double getImag() {
        return imag;
    }

    public Complex getConj() {
        return new Complex(getReal(), -getImag());
    }

    public static Complex[] onesRoots(int count) {
        Complex[] res = new Complex[count];
        for (int i = 0; i < count; i++) {
            res[i] = new Complex(Math.cos(2 * Math.PI * i / count), Math.sin(2 * Math.PI * i / count));
        }
        return res;
    }

    public static Complex[] foo(int count) {
        Complex[] res = new Complex[count];
        for (int i = 0; i < count; i++) {
            res[i] = Complex.multiply(new Complex(Math.cos(-2 * Math.PI * i / count), Math.sin(-2 * Math.PI * i / count)), new Complex((double) 1 / count, 0));
        }
        return res;
    }

    public static Complex add(Complex a, Complex b) {
        return new Complex(a.real + b.real, a.imag + b.imag);
    }

    public static Complex sub(Complex a, Complex b) {
        return new Complex(a.real - b.real, a.imag - b.imag);
    }

    public static Complex multiply(Complex a, Complex b) {
        return new Complex(a.real * b.real - a.imag * b.imag, a.real * b.imag + a.imag * b.real);
    }
}
