import java.util.Arrays;

public class Polynom {
    private final int deg;
    private final double[] coef;

    public Polynom(double... coef) {
        this.coef = Arrays.copyOf(coef, coef.length);
        this.deg = coef.length - 1; // TODO: max non zero
    }


    public int getDeg() {
        return deg;
    }

    public double[] getCoef() {
        return coef;
    }

    public static Polynom multiply(Polynom a, Polynom b) {
        int sumDeg = a.getDeg() + b.getDeg();

        int n = Integer.highestOneBit(sumDeg + 1);
        n <<= (int) Math.signum(sumDeg + 1 - n);

        Complex[] aVal = FFT(a.getCoef(), n);
        Complex[] bVal = FFT(b.getCoef(), n);
        Complex[] mulVal = new Complex[aVal.length];
        for (int i = 0; i < mulVal.length; i++) {
            mulVal[i] = Complex.multiply(aVal[i], bVal[i]).getConj();
        }
        Complex[] res = IFFT(mulVal, mulVal.length);
        double[] real = new double[res.length];
        for (int i = 0; i < res.length; i++) {
            real[i] = res[i].getReal() / n;
        }
        return new Polynom(real);
    }

    private static Complex[] FFT(double[] coef, int n) {
        double[] a = new double[n];
        System.arraycopy(coef, 0, a, 0, coef.length);


        if (n == 1) return coef.length > 0 ? new Complex[]{new Complex(coef[0], 0)} : new Complex[]{new Complex(0, 0)};

        Complex[] onesRoots = Complex.onesRoots(n);

        double[] even = new double[n / 2];
        double[] odd = new double[n / 2];
        for (int i = 0; i < n; i++) {
            if ((i & 1) == 0) even[i / 2] = a[i];
            else odd[i / 2] = a[i];
        }

        Complex[] eRes = FFT(even, n / 2);
        Complex[] oRes = FFT(odd, n / 2);

        Complex[] res = new Complex[n];
        for (int i = 0; i < n / 2; i++) {
            res[i] = Complex.add(eRes[i], Complex.multiply(onesRoots[i], oRes[i]));
            res[i + n / 2] = Complex.sub(eRes[i], Complex.multiply(onesRoots[i], oRes[i]));
        }
        return res;
    }

    private static Complex[] IFFT(Complex[] values, int n) {
        if (n == 1) return values;

        Complex[] onesRoots = Complex.onesRoots(n);

        Complex[] even = new Complex[values.length / 2 + values.length % 2];
        Complex[] odd = new Complex[values.length / 2];

        for (int i = 0; i < values.length; i++) {
            if (i % 2 == 0)
                even[i / 2] = values[i];
            else
                odd[i / 2] = values[i];
        }

        Complex[] eRes = IFFT(even, n / 2);
        Complex[] oRes = IFFT(odd, n / 2);

        Complex[] res = new Complex[n];
        for (int i = 0; i < n / 2; i++) {
            res[i] = Complex.add(eRes[i], Complex.multiply(onesRoots[i], oRes[i]));
            res[i + n / 2] = Complex.sub(eRes[i], Complex.multiply(onesRoots[i], oRes[i]));
        }
        return res;
    }
}
