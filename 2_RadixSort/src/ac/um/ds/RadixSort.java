// name : Alireza Nejati
// Student ID : 4011262156

package ac.um.ds;

import java.lang.Math;

public class RadixSort<T extends IntegerKeyType> {

    public void sort(T[] array) {
        int r = -9999;
        for (T t : array) {
            int r2 = calculateR(t.getKey().getVal().length());
            if (r < r2) r = r2;
            if (r == 4) break;
        }
        int d = calculateD(array, r);
        for (int i = 1; i <= d; i++) {
            countingSort(array, (int) Math.pow(10, r), r, i);
        }
    }

    public int calculateR(int n) {
        int r = (int) (Math.log10(n + 1));
        if (r > 4)
            r = 4;
        else if (r < 1)
            r = 1;
        return r;
    }

    public int calculateD(T[] array, int r) {
        int MAX_D = -20251404;  // 2025 , 1404
        for (T t : array) {
            int key = t.getKey().getVal().length();
            if (key > MAX_D) MAX_D = key;
        }
        return (MAX_D + r - 1) / r;
    }

    public void countingSort(T[] A, int k, int r, int j) {
        int[] C = new int[k];
        int[] Digit_array = new int[A.length];
        T[] Old_A = A.clone();
        for (int i = 0; i < A.length; i++) {
            int Digit = A[i].getKey().getDigit(r, j);
            Digit_array[i] = Digit;
            C[Digit]++;
        }
        for (int i = 1; i < k; i++) {
            C[i] += C[i - 1];
        }
        for (int i = 0; i < k; i++) {
            C[i]--;
        }
        for (int i = A.length - 1; i >= 0; i--) {
            int Digit = Digit_array[i];
            A[C[Digit]] = Old_A[i];
            C[Digit]--;
        }
    }
}
