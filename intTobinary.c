#include <stdio.h>
#include <stdlib.h>
#include <string.h>


long inttoBi(long n);
int hammingDist(char str1[], char str2[]);
void addLeadingZero(char str1[], char str2[]);


int main(int argc, char* argv[]) {

    if (argc >= 3) {

        int x = atol(argv[1]); //set the command output as x.
        int y = atol(argv[2]); // set the command output as y.
        char a[256];  //initialize the string for first bit string.
        char b[256];  // initialize the string for second bit string.

        //convert the return int value to a string.
        sprintf(a,"%ld", inttoBi(x));
        sprintf(b,"%ld", inttoBi(y));

        //add zeros to the shorter string.
        addLeadingZero(a, b);
        //find the hamming distance.
        int hd = hammingDist(a, b);

        //print out the bit strings and the hamming distance.
        printf("%s is the bit string for %d\n", a, x);
        printf("%s is the bit string for %d\n", b, y);
        printf("%d is the Hamming distance between the bit strings\n", hd);

    } else {
        //make sure there are only 3 lines of command line args
        printf("Error:  this program requires 3 command line args\n");

    }

    return 0;

}

// method that add zero to the shorter string.
void addLeadingZero(char str1[] , char str2[]) {
    int len1 = strlen(str1); //find the string length of string 1.
    int len2 = strlen(str2);// find the string length of string 2.
    int diff = (len1 > len2) ? len1 : len2; //find the max difference between two string length.

    // Prepend zeros to the shorter string
    while (len1 < diff) {
        for (int i = len1; i >= 0; i--) {
            str1[i + 1] = str1[i]; // expend the shorter array length.
            //until there is no difference in lengths.
        }

        str1[0] = '0'; //add zeros to it.
        len1++;
    }// loop stops when max diff is reached.

    while (len2 < diff) { //same concept as above.

        for (int i = len2; i >= 0; i--) {
            str2[i + 1] = str2[i];
        }

        str2[0] = '0';
        len2++;
    }

}

// function to calculate Hamming distance
int hammingDist(char str1[], char str2[]) {
    int i = 0, num = 0;
    while (str1[i] != '\0') {
        if (str1[i] != str2[i]) {
            num++;//count how many times a char in str1 is not equal to a char in str2.
        }//increment it.

        i++; //incrment the array size.
    }

    return num; //return the incremented value

}

//find the bit string of inputted integer.
long inttoBi(long n) {
    long bin = 0;
    long input = n;
    long i = 1;

    while (input > 0) {
        bin += (input % 2) * i; //algorthim to find the bit string.
        input /= 2;
        i *= 10;
    } // loop ends when the input has reached zero or one.

    return bin;
}
