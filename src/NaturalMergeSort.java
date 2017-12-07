import java.util.*;

/**
 Eventuell in jeder Iteration runs suchen? Iteration abbrechen wenn nur noch ein run vorhanden ist
 */

public class NaturalMergeSort {
    private static int n;

    public static void main(String [] args) {

        int[] test1 = {1, 5, 2, 4, 3};
        System.out.println("Test1: " + Arrays.toString(test1));
        naturalMergeSort(test1);

        int[] test2 = {17, 15, 14, 12, 13, 11, 9, 10, 5};
        System.out.println("Test2: " + Arrays.toString(test2));
        naturalMergeSort(test2);

        int[] test3 = {4, 8, 5, 7};
        System.out.println("Test3: " + Arrays.toString(test3));
        naturalMergeSort(test3);
        //System.out.println(Arrays.toString(test1));
    }

    private static Queue<Integer> findRuns(int[] A) {
        Queue<Integer> runs = new ArrayDeque<>();
        int low = 0;
        int high = 1;
        while (high <= n) {
            while(high < n && A[high-1] < A[high]) {
                high++;
            }
            // Run beenden
            runs.add(high-1);
            // Neuer low-Wert liegt auf dem run-verletzenden Wert
            low = high;
            high++;
        }

        return runs;
    }

    private static void naturalMergeSort(int[] A) {
        n = A.length;
        Queue<Integer> runs = findRuns(A);

        // In while-Schleife gefundene Runs abarbeiten
        while(runs.size() > 1) {
            // Nach jeder Iteration erneut bei 0 initialisieren
            int start = 0;
            // Runs paarweise in For-Schleife abarbeiten
            if(runs.size() > 1) {
                int runHigh1 = runs.poll();
                int runHigh2 = runs.poll();
                int[] B = new int[(runHigh1 - start) + (runHigh2 - runHigh1 + 1)];

                ArrayDeque<Integer> s1 = new ArrayDeque<>();
                ArrayDeque<Integer> s2 = new ArrayDeque<>();
                for(int i = start; i <= runHigh1; i++) {
                    s1.add(A[i]);
                }
                for(int j = runHigh1+1; j <= runHigh2; j++) {
                    s2.add(A[j]);
                }

                int counter = 0;
                while(!s1.isEmpty() || !s2.isEmpty()) {
                    if(!s1.isEmpty() && !s2.isEmpty()) {
                        if(s1.peek() < s2.peek()) {
                            B[counter] = s1.poll();
                            counter++;
                        } else if (s1.peek() >= s2.peek()){
                            B[counter] = s2.poll();
                            counter++;
                        }
                    } else {
                        // Eine von beiden leer
                        if(s1.isEmpty()) {
                            B[counter] = s2.poll();
                            counter++;
                        } else {
                            B[counter] = s1.poll();
                            counter++;
                        }
                    }
                }
                // B nach A kopieren nach Merge des runs
                for(int l = start; l <= runHigh2; l++) {
                    A[l] = B[l];
                }
            } else {
                // letzten Run verwerfen
                runs.poll();
            }
            runs = findRuns(A);




            // run ist per Definition sortiert, also neue Grenzen erneut einfügen mit

        }
        System.out.println("Sortiertes Ergebnis: " + Arrays.toString(A));
    }
}
