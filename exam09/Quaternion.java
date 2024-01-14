package exam09;

import java.util.*;
import java.util.stream.*;

public class Quaternion {

  // STATIC METHODS
  public static Quaternion readFromConsole() {
    Locale.setDefault(Locale.US);
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter q0, q1, q2, q3 separated by spaces:");
    String[] input = sc.nextLine().split(" ");
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

  public static Quaternion add(Quaterion p, Quaternion q) {
    return new Quaternion(p.i + q.i, p.j + q.j, p.k + q.k, p.l + q.l);
  }

  // END STATIC METHODS

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
