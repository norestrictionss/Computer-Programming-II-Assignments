#include<stdio.h>
#define SIZE 10
#include<math.h>
#include<string.h>

// STUDENT NAME: BARIÞ GÝRAY AKMAN
// STUDENT ID: 150121822
// AIM OF THAT HOMEWORK IS FINDING BEST WAY TO REACH DESTINATION BY METRO STATIONS.


// STRUCTS
typedef struct MetroStation{
	char name[SIZE*2];
	double x;
	double y;
}MetroStation;

typedef struct MetroLine{
	char color[SIZE];
	int i;
	MetroStation MetroStations[SIZE];
	
}MetroLine;
typedef struct MetroSystem{
	char name[SIZE*2];
	MetroLine MetroLines[SIZE];
}MetroSystem;


int equals(MetroStation s1, MetroStation s2);
int hasStation(MetroLine, MetroStation);
void addStation(MetroLine*, MetroStation);
MetroStation getFirstStop(MetroLine);
MetroStation getPreviousStop(MetroLine, MetroStation);
MetroStation getNextStop(MetroLine, MetroStation);
void addLine(MetroSystem*, MetroLine);
void printLine(MetroLine);
void printPath(MetroStation[]);
double getDistanceTravelled(MetroStation[]);
MetroStation findNearestStation(MetroSystem, double, double);
void getNeighboringStations(MetroSystem, MetroStation, MetroStation []);
void findPath(MetroStation, MetroStation, MetroStation[]);
void recursiveFindPath(MetroStation, MetroStation, MetroStation[], MetroStation[]);
int isExist(MetroStation[], MetroStation);
int findLength(MetroStation metroStations[]);

// ATTENTION: SOME ARRAYS HAVE BEEN CREATED ACCORDING TO THE SIZE DEFINED IN THE PREPROCESSING STEP.
//Declare a MetroSystem with the name of istanbul and an empty content.
MetroSystem istanbul = {"istanbul", '\0'};

int main()
{
	int i;
	double myX=1, myY=2; 
	double goalX=62, goalY=45; 
	
	// define 3 metro lines, 9 metro stations, and an empty myPath
	MetroLine red={'\0'}, blue={'\0'}, green={'\0'};
	MetroStation s1, s2, s3, s4, s5, s6, s7, s8, s9;
	MetroStation myPath[SIZE]={'\0'};
	
	strcpy(red.color, "red"); 
	strcpy(blue.color, "blue");
	strcpy(green.color, "green");

	
	strcpy(s1.name, "Haydarpasa"); 		s1.x=0; 	s1.y=0;
	strcpy(s2.name, "Sogutlucesme"); 	s2.x=10; 	s2.y=5;
	strcpy(s3.name, "Goztepe"); 		s3.x=20; 	s3.y=10;
	strcpy(s4.name, "Kozyatagi"); 		s4.x=30; 	s4.y=35;
	strcpy(s5.name, "Bostanci"); 		s5.x=45; 	s5.y=20;
	strcpy(s6.name, "Kartal"); 			s6.x=55; 	s6.y=20;
	strcpy(s7.name, "Samandira"); 		s7.x=60; 	s7.y=40;
	strcpy(s8.name, "Icmeler"); 		s8.x=70; 	s8.y=15;
	
	//Add several metro stations to the given metro lines.
	addStation(&red, s1); addStation(&red, s2); addStation(&red, s3); addStation(&red, s4); addStation(&red, s5); addStation(&red, s8);
	
	addStation(&blue, s2); addStation(&blue, s3); addStation(&blue, s4); addStation(&blue, s6); addStation(&blue, s7);
	
	addStation(&green, s2); addStation(&green, s3); addStation(&green, s5); addStation(&green, s6); addStation(&green, s8);

	// Add red, blue, green metro lines to the Istanbul metro system.
	addLine(&istanbul, red);
	addLine(&istanbul, blue);
	addLine(&istanbul, green);
	
	// print the content of the red, blue, green metro lines
	printLine(red);
	printLine(blue);
	printLine(green);
	

		
	// find the nearest stations to the current and target locations
	MetroStation nearMe = findNearestStation(istanbul, myX, myY);
	MetroStation nearGoal = findNearestStation(istanbul, goalX, goalY);
	
	printf("\n");
	
	printf("The best path from %s to %s is:\n", nearMe.name, nearGoal.name);
	
	// if the nearest current and target stations are the same, then print a message and exit.
	if(equals(nearMe, nearGoal)){
		printf("It is better to walk!\n");
		return 0;
	}

	// Calculate and print the myPath with the minimum distance travelled from start to target stations.
	findPath(nearMe, nearGoal, myPath);
	if(strlen(myPath[0].name) == 0)
		printf("There is no path on the metro!\n");
	else{
		printPath(myPath);
	}
	
	return 0;

}
MetroStation getPreviousStop(MetroLine metroLine, MetroStation metroStation){
	int i;
	MetroStation empty_station={'\0'};
	if(equals(metroLine.MetroStations[0], metroStation))
		return empty_station;
	else{
		for(i=0;i<sizeof(metroLine.MetroStations)/sizeof(metroLine.MetroStations[0]);i++)
		if(equals(metroLine.MetroStations[i+1], metroStation))
			return metroLine.MetroStations[i];
	}
	
			
}

// This function prints the path.
void printPath(MetroStation MetroStations[]){
	int i;
	for(i=0;i<SIZE;i++){
	  if(strcmp(MetroStations[i].name, "")!=0)
		printf("%d. %s\n", i+1, MetroStations[i].name);
	}
}
// This function prints the metro line entered as parameter.
void printLine(MetroLine metroLine){
	int i;
	printf("Metroline %s:   ", metroLine.color);
	for(i=0;i<sizeof(metroLine.MetroStations)/sizeof(metroLine.MetroStations[0]) ;i++){
		
		if(strcmp(metroLine.MetroStations[i+1].name, "")!=0)
			printf("%s, ", metroLine.MetroStations[i].name);
		else if(strcmp(metroLine.MetroStations[i+1].name, "")==0){
			printf("%s.\n", metroLine.MetroStations[i].name);
			break;
		}
			
	}
}
// This function adds line to the metrosystem entered as parameter.
void addLine(MetroSystem* metroSystem, MetroLine metroLine){
	int i=0;
	while(i<sizeof(metroSystem->MetroLines)/sizeof(metroSystem->MetroLines[0])){
		if(strcmp(metroSystem->MetroLines[i].color, "")==0){
			metroSystem->MetroLines[i]=metroLine;
			i=sizeof(metroSystem->MetroLines)/sizeof(metroSystem->MetroLines[0]);
		}
		i++;
	}
	
}
// This function adds station to the metroline entered as parameter.
void addStation(MetroLine *metroLine, MetroStation metroStation){
 	int i=0;
 	
 	while(i<sizeof(metroLine->MetroStations)/sizeof(metroLine->MetroStations[0])){
 		
 		if(strcmp(metroLine->MetroStations[i].name, "")==0){
 			 metroLine->MetroStations[i]=metroStation;	
 			 i=sizeof(metroLine->MetroStations)/sizeof(metroLine->MetroStations[0]);
		 }
		 i++;
	 }
	
}
// It checks whether two metro stations are same or not.
int equals(MetroStation s1, MetroStation s2){
	if(strcmp(s1.name, s2.name)==0)
		return 1;
	return 0;
}
// It checks whether metrolines have metro stations or not.
int hasStation(MetroLine metroLine, MetroStation metroStation){
	int i;
	for(i=0;i<sizeof(metroLine.MetroStations)/sizeof(metroLine.MetroStations[0]);i++)
		if(equals(metroStation, metroLine.MetroStations[i]))
			return 1;
	return 0;
}
// This function checks if metrostation array has a metrostation entered as parameter.
int isExist(MetroStation MetroStations[], MetroStation metroStation){
	int i=0;
	for(i=0;i<SIZE;i++){
		if(equals(MetroStations[i], metroStation)){
				
				return 1;
		}
	}
	
	return 0;
}
// This function checks total distance travelled by train station.
double getDistanceTravelled(MetroStation MetroStation[]){
	double sum=0;
	
	int i;

	if(findLength(MetroStation)>=2){
		for(i=0;i<findLength(MetroStation);i++){
			sum+=sqrt((MetroStation[i].x-MetroStation[i+1].x)*(MetroStation[i].x-MetroStation[i+1].x)+(MetroStation[i].y-MetroStation[i+1].y)*(MetroStation[i].y-MetroStation[i+1].y));
		}
		return sum;
	}
	
	return 0.0;
	
}
// This function looks for next stop.
MetroStation getNextStop(MetroLine metroLine, MetroStation metroStation){
	int i;
	MetroStation empty_station={'\0'};
	if(equals(metroLine.MetroStations[SIZE-1], metroStation))
		return empty_station;
	else{
		for(i=0;i<sizeof(metroLine.MetroStations)/sizeof(metroLine.MetroStations[0]);i++)
			if(equals(metroLine.MetroStations[i], metroStation))
				return metroLine.MetroStations[i+1];
	}
}
// This function looks for the first stop.
MetroStation getFirstStop(MetroLine metroLine){
	int i;
	MetroStation random_station={'\0'};
	for(i=0;i<sizeof(metroLine.MetroStations)/sizeof(metroLine.MetroStations[0]);i++)
		if(strcmp(metroLine.MetroStations[i].name, "")!=0)
			return metroLine.MetroStations[i];
	return random_station;
}
// This function looks for nearest station.
MetroStation findNearestStation(MetroSystem metroSystem, double x, double y){
	int i, j;
	int min=0;
	MetroStation temp;
	MetroStation holder;
	for(i=0;i<sizeof(metroSystem.MetroLines)/sizeof(metroSystem.MetroLines[0]);i++){
		temp=getFirstStop(metroSystem.MetroLines[i]);
		int j=0;
		 
		while(strcmp(temp.name, "")!=0){
			if(i==0 && j==0){
				min=sqrt(pow(temp.x-x, 2)+pow(temp.y-y, 2));
				j++;
				holder=temp;
			}
			
			else if(min>sqrt(pow(temp.x-x, 2)+pow(temp.y-y, 2))){
				min=sqrt((temp.x-x)*(temp.x-x)+(temp.y-y)*(temp.y-y));
				holder=temp;
			}
			temp=getNextStop(metroSystem.MetroLines[i], temp);
		}
		
	}
	return holder;
}
// This function looks neighbours of metrostation entered as parameter.
void getNeighboringStations(MetroSystem metroSystem, MetroStation metroStation, MetroStation neighboringStations[]){
	int i, j, k, t=0;

	for(j=0;j<sizeof(metroSystem.MetroLines)/sizeof(metroSystem.MetroLines[0]);j++){
			int a=0;
			for(k=0;k<sizeof(metroSystem.MetroLines[j].MetroStations)/sizeof(metroSystem.MetroLines[j].MetroStations[0]);k++){
				if(k<sizeof(metroSystem.MetroLines[j].MetroStations)/sizeof(metroSystem.MetroLines[j].MetroStations[0]) && equals(metroSystem.MetroLines[j].MetroStations[k], metroStation)){
					neighboringStations[t]=getPreviousStop(metroSystem.MetroLines[j], metroSystem.MetroLines[j].MetroStations[k]);
					neighboringStations[t+1]=getNextStop(metroSystem.MetroLines[j], metroSystem.MetroLines[j].MetroStations[k]);
					t+=2;
					
				}
				
			}
		}
	
	
}
// This function finds a best path.
void findPath(MetroStation start, MetroStation finish, MetroStation path[]){
	MetroStation partialPath[SIZE]={'\0'};

	recursiveFindPath(start, finish, partialPath, path);
	
}
// This function copies two array.
void copyArray(MetroStation array1[], MetroStation array2[]){
	int i;
	
	for(i=0;i<SIZE;i++){
		array1[i]=array2[i];
			
	}
		
	
}
// This function finds the first empty element and assigns start to metrostations[i].
void findStart(MetroStation metroStations[], MetroStation start){
	int i;
	for(i=0;i<SIZE;i++){
		if(strcmp(metroStations[i].name, "")==0){
			metroStations[i]=start;
			break;
		}
			
	}
}
// This function finds the last non-null element of metroStations element's index and return it.
int findLength(MetroStation metroStations[]){
	int i;
	for(i=0;i<SIZE;i++){
		if(strcmp(metroStations[i].name, "")==0)
			return i;
	}
}
// This function finds path in a recursive way.
void recursiveFindPath(MetroStation start, MetroStation finish, MetroStation partialPath[], MetroStation bestPath[]){
	int a;
	MetroStation neighbours[SIZE]={'\0'};
	getNeighboringStations(istanbul, start, neighbours);
	MetroStation duplicatePath[SIZE]={'\0'};	
		
	copyArray(duplicatePath, partialPath);
	if(isExist(partialPath, start)){
			return;	
	}
	else if(equals(start, finish)){
		findStart(duplicatePath, start);
		findStart(partialPath, start);
		
		if(getDistanceTravelled(bestPath)==0 || getDistanceTravelled(bestPath)>getDistanceTravelled(duplicatePath))
			copyArray(bestPath, duplicatePath);
		
		return;
	}
	else{
		findStart(duplicatePath, start);
		int k;
		for(k=0;k<SIZE;k++){
			if(strcmp(neighbours[k].name,"")!=0){
					recursiveFindPath(neighbours[k], finish, duplicatePath, bestPath);
					
			}
			
			
		}
	}
	
	
		
	
}





