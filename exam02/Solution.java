import java.util.*;

public class Solution {

  public static void main(String[] args) {
    Locale.setDefault(Locale.US);
    Scanner sc = new Scanner(System.in);

    // Variablen des Programms
    double r, phi, theta; // Polarkoordinaten
    double x, y, z; // kartesische Koordinaten

    // lange Ausgabezeile kann zusammengesetzt werden
    System.out.println("Bitte geben Sie erst den Radius und dann zwei Winkel in Bogenmaß an (Vielfache von PI)");
    System.out.println("Erst Radius, dann Phi, dann Theta (0.5 entpricht 0.5*PI)");

    // polare Koordinaten in dieser Reihenfolge einlesen
    r = sc.nextDouble();
    phi = sc.nextDouble() * Math.PI;
    theta = sc.nextDouble() * Math.PI;

    // gegebene Formeln umsetzen
    x = r * Math.sin(theta) * Math.cos(phi);
    y = r * Math.sin(theta) * Math.sin(phi);
    z = r * Math.cos(theta);

    System.out.println("Der Punkt lautet in kartesischen Koordinaten");
    System.out.println("x = " + x + ", y = " + y + ", z = " + z);
  }

}