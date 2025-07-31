public class Main {
    public static void main(String[] args) {

        Polynom p1 = new Polynom(2);
        Polynom p2 = new Polynom(0, 0, 1);

        Polynom res = Polynom.multiply(p1, p2);
        for (int i = 0; i < res.getCoef().length; i++) {
            System.out.println(res.getCoef()[i]);
        }
        System.out.println("йоу");
    }
}
