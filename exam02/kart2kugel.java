import java.util.*;

public class kart2kugel {

  public static void main(String[] args) {
    Locale.setDefault(Locale.US);
    Scanner sc = new Scanner(System.in);

    // Variablen des Programms
    double x, y, z; // kartesische Koordinaten
    double r, phi, theta; // Polarkoordinaten

    // lange Ausgabezeile kann zusammengesetzt werden
    System.out.println("Bitte geben Sie einen Punkt"
        + "in kartesischen Koordinaten an");
    System.out.println("Erst x, dann y, und dann z");

    // kartesische Koordinaten in dieser Reihenfolge einlesen
    x = sc.nextDouble();
    y = sc.nextDouble();
    z = sc.nextDouble();

    // gegebene Formeln umsetzen
    r = Math.sqrt(x * x + y * y + z * z); // Wurzel und Quadrate
    phi = Math.atan2(y, x) / Math.PI; // aehnlich atan(y/x), geht aber auch fuer x=0
    theta = Math.acos(z / r) / Math.PI; // Fall r=0 wird hier nicht getrennt behandelt

    // in gewuenschte Schreibweise PI*xxx umrechnen und mit Text ausgeben,
    // dazu muessen phi und theta durch die obige Konstante pi dividiert werden
    System.out.println("Der Punkt lautet in Polarkoordinaten");
    System.out.println("r = " + r + ", PHI = PI * " + phi + ", THETA = PI * "
        + theta);

    sc.close();
  }

}