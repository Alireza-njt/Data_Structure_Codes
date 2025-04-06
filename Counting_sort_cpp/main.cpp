#include <iostream>
#include <stdio.h>

class Person {

private:
    std::string name;
    int grade;

public:

    Person() {}
    Person(std::string name, int grade) : name(name), grade(grade) {}

    int getGrade() {
        return grade;
    }

    std::string getName() {
        return name ;
    }
};

void printArray(Person arr[], int size) {
    for (int i = 0; i < size; i++) {
        std::cout << arr[i].getName() << " grade is " << arr[i].getGrade();
        printf("\n") ;
    }
    std::cout << std::endl;
}

void shiftRight(int arr[], int size) {
    for (int i = size - 1; i > 0; i--) {
        arr[i] = arr[i - 1];
    }
    arr[0] = 0;
}

void counting_sort_function (Person *unsorted_array , Person *sorted_array , int size , int max_number) {

    int *C = new int[max_number];
    for (int i = 0; i < max_number; i++) {
        C[i] = 0;
    }
    for (int i = 0; i < size; i++) {
        C[unsorted_array[i].getGrade()]++;
    }

    for (int i = 1; i < max_number; i++) {
        C[i] += C[i - 1];
    }

    shiftRight(C, max_number);

    for (int i = 0; i < size; i++) {
        sorted_array[C[unsorted_array[i].getGrade()]] = unsorted_array[i];
        C[unsorted_array[i].getGrade()]++;
    }
}

int main() {

    int size = 8 ;
    int max = 5 ;

    Person A[8] ;

    A[0] = Person("Alireza" , 2) ;
    A[1] = Person("Reza" , 5) ;
    A[2] = Person("Sarah" , 3) ;
    A[3] = Person("Soheil" , 0) ;
    A[4] = Person("Mojtaba" , 2) ;
    A[5] = Person("Hosein" , 3) ;
    A[6] = Person("Maryam" , 0) ;
    A[7] = Person("Sogand" , 3) ;

    printf("________________________________________________________________________________________________\n\n") ;
    printf("Unsorted List : \n") ;
    printArray(A , size) ;
    printf("________________________________________________________________________________________________\n") ;

    Person B[8];

    for (int i=0 ; i<8 ; i++) {
        B[i] = Person("NO_NAME" , 1000) ;
    }

    counting_sort_function(A , B , size , max + 1) ;

    printf("________________________________________________________________________________________________\n\n") ;
    printf("Sorted List : \n") ;
    printArray(B , size) ;
    printf("________________________________________________________________________________________________\n") ;


}
