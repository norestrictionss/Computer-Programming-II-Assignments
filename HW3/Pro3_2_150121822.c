#include<stdio.h>

/* STUDENT NAME: BARIÞ GÝRAY AKMAN 
	STUDENT ID: 150121822
	MAIN OBJECTIVE OF THAT HOMEWORK IS PRINTING THE SUPER DIGIT OF 
	THE NUMBER ENTERED BY USER. */

// This function prints the number entered by the user.
// This function also prints the number depending on repetition variable.
void printNumber(int n, int k){
	if(k>0){
		printf("%d", n);
		return printNumber(n, --k);
	}
}
// This function gets the sum of digits of number.
int getSum(int n){
    if(n>0)
    	return n%10+getSum(n/10);
}
// This function multiplies two numbers which are given as parameters.
/*This function has been created because in the PDF file, if multiplication must be used or not hasn't been declared clearly. 
It has been declared only for first question, but I have used for whole questions.*/
// There is simple condition. Both a and b mustn't be negative. Otherwise, function will not work properly.
int getMultiplication(int a, int b){
	if(b>0)
		return a+getMultiplication(a, --b);
	else 
		return 0;
}
// This function calculates the super digit of the number.
// Validator variable validates if superDigit function is parsed for a first time.
int superDigit(int n, int k, int validator){
	// If validator is equal to 0, sum of digits of number is found and it is multiplied by a repetition time.
	// The value found is our first sum of digits.
	// It continues until single-digit number is reached.
	if(validator==0){
		n=getMultiplication(getSum(n), k);
		
		validator=1;
	}
	n=getSum(n);
	// Recursion is maintained until n value is bigger than 9.
	if(n>9)
		return superDigit(n, k, validator);
	// After single-digit number is reached, result(n value) is returned.
	else return n;
}
int main(void){
	int n, k;  // n and k values are taken below.
	// n stands for number.
	// k stands for repetition times.
	// n and k mustn't be negative numbers.
	printf("Please enter a number (n=) : " );
	scanf("%d", &n);
	printf("\nPlease enter repetition factor (k=) : ");
	scanf("%d", &k);
	printf("Super digit of number ");
	printNumber(n, k);
	printf(" is %d", superDigit(n, k, 0));
}




