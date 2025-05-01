// name : Alireza Nejati
// Student ID : 4011262156

package ac.um.ds;

import java.util.LinkedList;
import java.util.Queue;

public class NaturalMergeSort<T extends Comparable<T>> implements ISort<T> {

    public void sort(T[] arr, int n) {
        Queue<Integer> points = new LinkedList<>();
        findBorders(arr, n, points);

        while (points.size() > 2) {
            int NEW_P = points.poll();
            int NEW_Q = -2025;
            int NEW_R = -1404;
            points.add(0);
            while (true) {
                NEW_Q = points.poll();
                NEW_R = points.poll();
                merge(arr, NEW_P, NEW_Q, NEW_R);
                int CURRENT_POINT = points.peek();
                if (CURRENT_POINT == n || CURRENT_POINT == 0) {
                    if (CURRENT_POINT == n) {
                        NEW_Q = NEW_R;
                        NEW_R = points.poll();
                        merge(arr, NEW_P, NEW_Q, NEW_R);
                    }
                    points.add(NEW_R);
                    break;
                }
                NEW_P = NEW_R;
                points.add(NEW_R);
            }
        }
    }

    public void findBorders(T[] arr, int n, Queue<Integer> points) {
        points.add(0);
        for (int i = 1; i < n; i++) {
            if (arr[i - 1].compareTo(arr[i]) > 0) {
                points.add(i);
            }
        }
        points.add(n);
    }

    public void merge(T[] arr, int p, int q, int r) {
        Queue<T> NEW_PART_OF_OUR_ARRAY = new LinkedList<>();
        int a0 = p;
        int a1 = q - 1;
        int a2 = q;
        int a3 = r - 1;
        while (a0 <= a1 && a2 <= a3) {
            if (arr[a0].compareTo(arr[a2]) <= 0) {
                NEW_PART_OF_OUR_ARRAY.add(arr[a0]);
                a0++;
            } else {
                NEW_PART_OF_OUR_ARRAY.add(arr[a2]);
                a2++;
            }
        }
        while (a0 <= a1) {
            NEW_PART_OF_OUR_ARRAY.add(arr[a0]);
            a0++;
        }
        while (a2 <= a3) {
            NEW_PART_OF_OUR_ARRAY.add(arr[a2]);
            a2++;
        }
        for (int i = p; i < r; i++) {
            arr[i] = NEW_PART_OF_OUR_ARRAY.poll();
        }
    }
}