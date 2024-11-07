import java.util.Scanner;

public class SegitigaArrayCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan jumlah segitiga: ");
        int jumlahSegitiga = scanner.nextInt();

        double[] luasSegitiga = new double[jumlahSegitiga];

        for (int i = 0; i < jumlahSegitiga; i++) {
            System.out.println("Segitiga ke-" + (i + 1));
            System.out.print("Masukkan alas segitiga: ");
            double alas = scanner.nextDouble();

            System.out.print("Masukkan tinggi segitiga: ");
            double tinggi = scanner.nextDouble();

            luasSegitiga[i] = hitungLuasSegitiga(alas, tinggi);
        }

        System.out.println("Luas segitiga:");

        for (int i = 0; i < jumlahSegitiga; i++) {
            System.out.println("Segitiga ke-" + (i + 1) + ": " + luasSegitiga[i]);
        }

        scanner.close();
    }

    public static double hitungLuasSegitiga(double alas, double tinggi) {
        double luas = 0.5 * alas * tinggi;
        return luas;
    }
}
