import java.util.Arrays;

public class MergeSortIter {

    private static int n;

    public static void main(String [] args) {
        int[] test1 = {1, 5, 2, 4, 3};
        mergeSortIter(test1);
        System.out.println(Arrays.toString(test1));
    }

    private static void mergeSortIter(int[] A) {
        n = A.length;
        int s = 1;
        while (s < n) {
            int l = 0;
            while(l + s < n ) {
                mergeIter(A, l, l+s, Math.min(l+2*s, n));
                l = l + 2*s;
            }
            s = 2 * s;
        }
    }

    private static void mergeIter(int[] A, int l, int m, int r) {
        int[] B = new int[A.length];

        int i = l;
        int j = m;
        int k = l;

        while(i < m && j < r) {
            if(A[i] < A[j]) {
                B[k] = A[i];
                i = i + 1;
            } else {
                B[k] = A[j];
                j = j + 1;
            }
            k = k + 1;
        }

        for(int h = i; h < m; h++) {
            B[k-i+h] = A[h];
        }
        for(int h = j; h < r; h++) {
            B[k-j+h] = A[h];
        }
        for(int h = l; h < r; h++) {
            A[h] = B[h];
        }
    }
}
