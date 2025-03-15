// name : Alireza Nejati
// Student Id : 4011262156

public class Main {

    public static void main(String[] args) {
        int[] array = {74, 482, 819, 90, 1, 3, 45, 7};
        int[] array2 = {34, 67, 89, 12, 45, 78, 23, 56, 90, 11, 33, 44, 55, 66, 77, 88, 99, 22, 10, 9};

        Quick_Sort_Functions.QuickSort(array, 0, array.length - 1);
        Quick_Sort_Functions.QuickSort(array2, 0, array2.length - 1);

        System.out.println("Sorted array:");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " , ");
        }
        System.out.println();

        System.out.println("Sorted array2:");
        for (int i = 0; i < array2.length; i++) {
            System.out.print(array2[i] + " , ");
        }
        System.out.println();
    }
}

class Quick_Sort_Functions {
    public static int choosePivot(int[] Array, int p) {
        return Array[p];
    }

    public static int partition(int[] Array, int p, int r, int pivot) {
        int i = p;
        int j = r;

        while (i <= j) {
            while (Array[i] < pivot) i++;
            while (Array[j] > pivot) j--;

            if (i <= j) {
                // Swap Array[i] and Array[j]
                int temp = Array[i];
                Array[i] = Array[j];
                Array[j] = temp;
                i++;
                j--;
            }
        }

        return i;
    }

    public static void QuickSort(int[] array, int p, int r) {
        if (p < r) {
            int pivot = choosePivot(array, p);
            int q = partition(array, p, r, pivot);
            QuickSort(array, p, q - 1);
            QuickSort(array, q, r);
        }
    }
}
