import java.util.*;

public class Quaternionen {

  public static void main(String[] args) {
    Locale.setDefault(Locale.US);
    Scanner sc = new Scanner(System.in);
    Quaternion.setScanner(sc);

    Quaternion p = Quaternion.readFromConsole("p");
    Quaternion q = Quaternion.readFromConsole("q");

    Quaternion sum = Quaternion.add(p, q);
    Quaternion product = Quaternion.mult(p, q);

    System.out.println(
        String.format(
            "p=%s, |p|=%g\n" + "q=%s, |q|=%g\n" + "p+q=%s\n" + "p*q=%s",
            p.toString(),
            p.magnitude(),
            q.toString(),
            q.magnitude(),
            sum.toString(),
            product.toString()));
  }

  static class Quaternion {

    // STATIC METHODS / FIELDS
    private static Scanner scanner;

    public static void setScanner(Scanner sc) {
      Quaternion.scanner = sc;
    }

    public static Quaternion readFromConsole() {
      return Quaternion.readFromConsole("q");
    }

    public static Quaternion readFromConsole(String token) {
      System.out.println(
          String.format(
              "Enter %s0, %s1, %s2, %s3 separated by spaces:", token, token, token, token));
      String[] input = Quaternion.scanner.nextLine().split(" ");
      double[] values = Arrays.stream(input).mapToDouble(Double::parseDouble).toArray();
      return new Quaternion(values[0], values[1], values[2], values[3]);
    }

    public static Quaternion mult(Quaternion p, Quaternion q) {
      return new Quaternion(
          p.i * q.i - p.j * q.j - p.k * q.k - p.l * q.l,
          p.i * q.j + p.j * q.i + p.k * q.l - p.l * q.k,
          p.i * q.k + p.k * q.i + p.l * q.j - p.j * q.l,
          p.i * q.l + p.l * q.i + p.j * q.k - p.k * q.j);
    }

    public static Quaternion add(Quaternion p, Quaternion q) {
      return new Quaternion(p.i + q.i, p.j + q.j, p.k + q.k, p.l + q.l);
    }

    // END STATIC METHODS / FIELDS

    public double i, j, k, l;

    public Quaternion(double i, double j, double k, double l) {
      this.i = i;
      this.j = j;
      this.k = k;
      this.l = l;
    }

    public double magnitude() {
      return Math.sqrt(this.i * this.i + this.j * this.j + this.k * this.k + this.l * this.l);
    }

    public String toString() {
      return String.format("[%g,%g,%g,%g]", this.i, this.j, this.k, this.l);
    }

    public static void main(String[] args) {
      Quaternion q = Quaternion.readFromConsole();
      System.out.println(q.toString());
    }
  }
}
