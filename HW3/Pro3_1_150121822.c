#include<stdio.h>

/* STUDENT NAME: BARIÞ GÝRAY AKMAN 
	STUDENT ID: 150121822
	MAIN OBJECTIVE OF THAT HOMEWORK IS PRINTING NUMBER OF EARS OF BUNNIES DEPENDING ON AN INPUT 
	VALUE. */
	
// This function provides us to print number of ears for each bunnies standing on a line.
// Function holds the previous value. Because previous value is added to current value.
int printSum(int n, int current_position, int previous){
	
	// If current line is equal to 0, below is printed and function itself is returned.
	if(current_position==0 && current_position<=n){
		 printf("\nbunnyEars%d(0) -> 0\n", n);
		 return printSum(n, ++current_position, 0);
	}
	// If modulo 2 of current line is equal to 0, the value which is sum of 3 and previous value is displayed.
	// As a second, function itself is returned. Current line(current_position) is incremented by 1.
	//Moreover, previous value is incremented by 3.
	else if(current_position % 2==0 && current_position<=n){
		printf("bunnyEars%d(%d) -> %d\n", n, current_position, previous+3);
		return printSum(n, ++current_position, previous+3);
	}
	// If modulo 2 of current line is not equal to 0, the value which is sum of 2 and previous value is displayed.
	// As a second step, function itself is returned. Current line(current_position) is incremented by 1.
	// Moreover, previous value is incremented by 2.
	else if(current_position<=n){
		printf("bunnyEars%d(%d) -> %d\n", n, current_position, previous+2);
		return printSum(n, ++current_position, previous+2);
	}
	   
	   
}
int main(void){
	int n; // This variable refers to the number of lines.
	// n must be postiive. Otherwise, program won't work properly.
	printf("Please enter the number of lines (n=): "); 
	scanf("%d",&n);
	printSum(n, 0, 0);
	
}
