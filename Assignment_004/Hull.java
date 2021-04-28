// @author  Christopher K. Schmitt
// @version 3/21/2021

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;


public class Hull {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    // Stream Input Points
    int n = scanner.nextInt();
    float[] x = new float[n];
    float[] y = new float[n];

    for (int i = 0; i < n; i += 1) {
      x[i] = scanner.nextFloat();
      y[i] = scanner.nextFloat();
    }

    // Compute the Convex Hull
    List<Pair> segments = new ArrayList<Pair>();

    for (int i = 0; i < n; i += 1) {
      for (int j = i + 1; j < n; j += 1) {
        // Compute the Line
        float a = y[j] - y[i];
        float b = x[i] - x[j];
        float c = x[i] * y[j] - y[i] * x[j];

        // Test if Every Point is to One Sie
        float sign = 0;
        boolean isHull = true;

        for (int k = 0; k < n; k += 1) {
          float region = Math.signum(a * x[k] + b * y[k] - c);

          if (sign == 0f && region != 0f) {
            sign = Math.signum(region);
          }

          if (k == i || k == j) {
            continue;
          }

          if (region == 0f) {
            isHull = false;
            break;
          }

          if (sign != 0f && region != sign) {
            isHull = false;
            break;
          }
        }

        // Add the Segemnt to the Solution
        if (isHull) {
          segments.add(new Pair(i, j));
        }
      }
    }

    // Output Segemnt Indicies
    System.out.println(segments.size());
    for (Pair segment : segments) {
      System.out.println(segment);
    }
  }


  private static class Pair {
    int a;
    int b;

    public Pair(int a, int b) {
      this.a = a;
      this.b = b;
    }

    public String toString() {
      return "[" + a + " -> " + b + "]";
    }
  }
}