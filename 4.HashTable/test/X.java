/* Copyright (C) Sayed Kamaledin Ghiasi-Shirazi, Ferdowsi Univerity of Mashhad
 * 23 Azar 1403(Hijri shamsi)
 * 13 Dec 2024
 * Author: Sayed Kamaledin Ghiasi-Shirazi, Ali Moghaddaszadeh, Melika Zaihi, MohammadMahdi Rahneshin, Sajad zirak
 *
 * 
 */
public class X implements Comparable<X> {
    private int value;

    public X(int n) {
        value = n;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(X A) {
        if (value < A.getValue())
            return -1;
        else if (value > A.getValue())
            return 1;
        else
            return 0;
    }

    public String toString(){
        return String.valueOf(value);
    }

}
