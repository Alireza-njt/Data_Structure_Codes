// name : Alireza Nejati
// Student ID : 4011262156


package ac.um.ds;

public class SelectionSort<T extends Comparable<T>> implements ISort<T> {
    @Override
    public void Sort(T[] A) {
        // Write your code here
        for (int i=A.length ; i>1 ; i--) {
            int max_number = 0 ;
            for (int j=0 ; j<i ; j++) {
                if (A[j].compareTo(A[max_number]) == 1) {
                    max_number = j;
                }
            }
            T temp = A[i-1] ;
            A[i-1] = A[max_number];
            A[max_number] = temp ;
        }
    }
}
