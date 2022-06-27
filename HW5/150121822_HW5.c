#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<ctype.h>

// AIM OF THAT PROJECT IS SORTING SONGS ACCORDING TO THEIR DURATIONS, ALPHABETIC COMBINATIONS AND CHRONOLOGICAL CIRCUMSTANCES.
// LINKED LIST APPROACH HAS BEEN UTILIZED.
// WARNING: IT TAKES TIME TO GENERATE THE RANDOM LIST. SO YOU MUST WAIT FOR A WHILE TO BE GENERATED COMPLETELY.


typedef struct Song{
	
	char songName[25];
	unsigned int duration;
	struct Song* chrono_next;
	struct Song* alpha_next;
	struct Song* duration_next;
	struct Song* random_next;
	
}Song;
		

// This function prints songs according to chronological, alphabetical, random and duration-time order.
void printList(Song* chrono_head, Song *alpha_head, Song* duration_head, Song* random_head){
	int i=1;
	
	printf("The list in chronological order: \n");
	while(chrono_head!=NULL){
		printf("%d. %s %d%s%d\n", i, chrono_head->songName, chrono_head->duration/60, ":",chrono_head->duration%60);
		chrono_head=chrono_head->chrono_next;
		i++;
	}
	printf("\nThe list in alphabetical order: \n");
	i=1;
	while(alpha_head!=NULL){
		printf("%d. %s %d%s%d\n", i, alpha_head->songName, alpha_head->duration/60, ":", alpha_head->duration%60);
		alpha_head=alpha_head->alpha_next;
		i++;
	}
	i=1;
	printf("\nThe list in duration-time order: \n");
	while(duration_head!=NULL){
		printf("%d. %s %d%s%d\n", i, duration_head->songName, duration_head->duration/60, ":", duration_head->duration%60);
		duration_head=duration_head->duration_next;
		i++;
	}
	i=1;
	printf("\nThe list in random order: \n");
	while(random_head!=NULL){
		printf("%d. %s %d%s%d\n", i, random_head->songName, random_head->duration/60, ":", random_head->duration%60);
		random_head=random_head->random_next;
		i++;
	}
	i=1;
	
		
	
}

// As it's name, this function counts total nodes in linked list.
int countNodes(Song* head){
	int i=0;
	while(head!=NULL){
		i++;
		head=head->chrono_next;
	}
	return i;
	
}
// This function finds and returns the target node according to index parameter.
Song *findNode(Song* head, int index){
	
	int i;
	for(i=0;i<index;i++)
		head=head->chrono_next;
	return head;
}


// This function stands for checking the linked list if temp already exists in randomly created linked list. If it isn't used, random linked list won't be generated completely.
int checkRandomList(Song* head, Song* temp){
	
	while(head!=NULL){

		if(head==temp)
			return 0;
		head=head->random_next;
	}

	return 1;
}

// This function creates random linked list.
Song *createRandomList(Song *head){
	int total_nodes=countNodes(head);
	int i=0;
	Song *random_tail=NULL;
	int all_values[total_nodes];

	Song *random_head=NULL;
	while(i<total_nodes){
		
		srand(time(0));
		int index=rand()%(total_nodes);
		
		Song *random_node=findNode(head, index);
		
		
		if(i==0){
			random_head=random_node;
			random_tail=random_node;
			
		}
		else{
		
			
			while(!checkRandomList(random_head, random_node)){
			
				srand(time(0));
				index=rand()%(total_nodes);
				random_node=findNode(head, index);
					

			}
			
			random_tail->random_next=random_node;
			random_tail=random_tail->random_next;
			
		}
		
		i++;
		
	}
	
	return random_head;
	
}
// This function constructs node according to parameters given.
Song *createNode(char *songName, char* duration){

	char *token;
	Song *Node=malloc(sizeof(Song));
	strcpy(Node->songName, songName);
	int minute, seconds;
	token=strtok(duration, ":");
	minute=atoi(token);
	Node->alpha_next=NULL;
	Node->duration_next=NULL;
	Node->random_next=NULL;
	Node->chrono_next=NULL;
	token=strtok(NULL, ":");
	seconds=atoi(token);
	Node->duration=minute*60+seconds;
	
	return Node;
}

// This function deletes target node from linked lists.
void deleteNode(Song ** chrono_head, Song** alpha_head, Song** duration_head, Song** random_head, char *songName){
	
	
	Song*previous=*chrono_head;
	Song* next=*chrono_head;
	Song* deleted_one=NULL;
	Song* temp=*chrono_head;
	// This loop continues until target song is found.
	while(temp!=NULL && strcmp(songName, next->songName)!=0){
			
		previous=temp;
		next=temp->chrono_next;
		temp=temp->chrono_next;
					
			
	}
	// If target song is equal to head, head will be deleted from linked list. Same logic has been used for other linked lists.
	if(next==previous){
		deleted_one=next;
		*chrono_head=(*chrono_head)->chrono_next;
	}
		
	// If next isn't equal to head and isn't NULL, previous and next of the target node will be attached. And target node will be freed.
	// Same logic has been used at the other linked lists.
	else if(next!=NULL){
		deleted_one=next;
					
		previous->chrono_next=next->chrono_next;
	}

	temp=*alpha_head;
	previous=*alpha_head;
	next=*alpha_head;
	while(temp!=NULL && strcmp(songName, next->songName)!=0){
		previous=temp;
		next=temp->alpha_next;
		temp=temp->alpha_next;
	}
	if(next==previous){
		deleted_one=next;
		*alpha_head=(*alpha_head)->alpha_next;
	}
		
	else if(next!=NULL){
		deleted_one=next;
		previous->alpha_next=next->alpha_next;

	}

		

	temp=*duration_head;
	previous=*duration_head;
	next=*duration_head;
	while(temp!=NULL && strcmp(songName, next->songName)!=0){
		previous=temp;
		next=temp->duration_next;
		temp=temp->duration_next;
	}
	if(next==previous){
		deleted_one=next;
		*duration_head=(*duration_head)->duration_next;
	}
	else if(next!=NULL){
		deleted_one=next;
		previous->duration_next=next->duration_next;

	}

		
	temp=*random_head;
	previous=*random_head;
	next=*random_head;
	while(temp!=NULL && strcmp(songName, next->songName)!=0){
		previous=temp;
		next=temp->random_next;
		temp=temp->random_next;
	}
	if(next==previous){
		deleted_one=next;
		*random_head=(*random_head)->random_next;
		free(deleted_one);
	}
	else if(next!=NULL){

		deleted_one=next;
		previous->random_next=next->random_next;
		free(deleted_one);
	}
	// If next is equal to NULL, it seems that conditions haven't been satisfied.
	// So it means target node doesn't exist. 
	if(next==NULL)
		printf("\nSong couldn't found in the playlist");
		
		
}

// This function inserts node to linked lists.

void insertNode(Song **head, Song** alpha_head, Song** duration_head, Song** random_head,Song *new_one){
	
	
	// If our linked list is empty, head will be constructed first.
	if(*head==NULL){
			
		*head=new_one;
		*alpha_head=new_one;
		*duration_head=new_one;
		*random_head=new_one;
	}
	else{
		
		int minute, seconds;
		Song *temp=*head;
		Song* current=new_one;
		
		// Node at the last index is found.
		while(temp->chrono_next!=NULL)
			temp=temp->chrono_next;
			
		// Later on, new node is inserted at the end of it.
		temp->chrono_next=new_one;
		new_one->chrono_next=NULL;
		// If alphabetic precedence of the new node is more than alpha head, it will be inserted at the beginning of the list.
		if(strcmp((*alpha_head)->songName, new_one->songName)>0){
			
			new_one->alpha_next=*alpha_head;
			*alpha_head=new_one;
			
		}
		else{
			
			temp=*alpha_head;
			
			Song *previous=NULL, *next=NULL;
			// Position of the new node according to alphabetic precedence is specified.
			while(temp!=NULL && strcmp(current->songName, temp->songName)>=0){
				previous=temp;
				next=temp->alpha_next;
			temp=temp->alpha_next;
			   

			}
			// Lastly, new node is inserted at the end of the previous node.
			previous->alpha_next=current;
			current->alpha_next=next;
				
		}
		// Numerical difference of durations between the head of duration list and new node is defined.
		int num=(*duration_head)->duration-current->duration;
		// If duration of head is much bigger than new node's, new node will be inserted at the beginning of the linked list.
		if(num>0){

			current->duration_next=*duration_head;
			*duration_head=current;
		}
		else{
			temp=*duration_head;
			Song* previous=NULL, *next=NULL;
			int num2=current->duration-temp->duration; 
			

			// Position of the new node is adjusted according to conditions(duration precedence).
			while(temp!=NULL && num2>=0){
				
				previous=temp;
				next=temp->duration_next;
				temp=temp->duration_next;
				
				if(temp!=NULL)
					num2=current->duration-temp->duration;
				
			}
			// Lastly, new node is inserted between previous and next.
			previous->duration_next=current;
			current->duration_next=next;
		}
	}

	
}
// This function cleans the elements of random linked list completely.
void cleanRandomList(Song* head){
	while(head!=NULL){
		head->random_next=NULL;
		head=head->chrono_next;
	}
	
}
// This function process the file with strings given.
void processFile(Song* head, int i, char* holder, FILE *fPtr){
	fputs("	", fPtr);
	fputs(itoa(i, holder, 10), fPtr);
	fputs(". ", fPtr);
	fputs(head->songName, fPtr);

	fputs("		", fPtr);
	fputs(itoa(head->duration/60, holder, 10), fPtr);
	fputs(":", fPtr);
	fputs(itoa(head->duration%60, holder, 10), fPtr);
	fputs("\n", fPtr);
}
// This function prints the results generated on a file.
void writeFile(Song* chrono_head, Song* alpha_head, Song* duration_head, Song* random_head, FILE *fPtr){
	fputs("The list in choronological order:\n", fPtr);
	Song* temp=chrono_head;
	
	int i=1;
	char holder[100];
	while(chrono_head!=NULL){
		processFile(chrono_head, i, holder, fPtr);
		chrono_head=chrono_head->chrono_next;
		i++;
	}
	i=1;
	fputs("\nThe list in alphabetical order:\n", fPtr);
	while(alpha_head!=NULL){
		processFile(alpha_head, i, holder, fPtr);
		alpha_head=alpha_head->alpha_next;
		i++;
	}
	i=1;
	fputs("\nThe list in duration-time order:\n", fPtr);
	while(duration_head!=NULL){
		processFile(duration_head, i, holder, fPtr);
		duration_head=duration_head->duration_next;
		i++;
	}
	i=1;
	fputs("\nThe list in random order:\n", fPtr);
	while(random_head!=NULL){
		processFile(random_head, i, holder, fPtr);
		random_head=random_head->random_next;
		i++;
	}
}
int main(void){
	
	FILE *fPtr;
	char line[100];
	int i;
	Song *chrono_head=NULL, *alpha_head=NULL, *duration_head=NULL, *random_head=NULL, *temp=NULL, *previous=NULL;
	fPtr=fopen("songs.txt", "r");
	char songName[100];
	char duration[100];
	int choice=0;
	// It reads the file according to tokenization.
	while(fgets(line, 100, fPtr)){
		
		char *token=strtok(line, "	");
		strcpy(songName, token);
		
		token=strtok(NULL, "	");
		
		if(token!=NULL)
			strcpy(duration, token);
		
		Song *new_node=createNode(songName, duration);
		
		insertNode(&chrono_head, &alpha_head, &duration_head, &random_head ,new_node);

		
	}
	random_head=createRandomList(chrono_head);
	printList(chrono_head, alpha_head, duration_head, random_head);
	// Menu
	while(choice!=5){
		printf("\nEnter your choice: ");
		printf("\n	1 to insert a song into the list.");
		printf("\n	2 to delete a song from the list.");
		printf("\n	3 to print the songs in the list.");
		printf("\n	4 to print the songs to an output file.");
		printf("\n	5 to end.	\n     ? ");
		scanf("%d", &choice);
		printf("\n");
		if(choice==1){
			char information[100], duration[100], song[100];
			
			printf("\n\nEnter a song name with duration:\n");
			getchar();
			gets(information);

			char *token=strtok(information, "\t");

			strcpy(song, token);
			token=strtok(NULL, "\t");
			strcpy(duration, token);
			
			Song *new_node=createNode(song, duration);
			
			insertNode(&chrono_head, &alpha_head, &duration_head, &random_head, new_node);

		
		}
		else if(choice==2){
			char song[100];
			printf("Enter a song name:\n");
			getchar();
			gets(song);
			if(chrono_head!=NULL){
				deleteNode(&chrono_head, &alpha_head, &duration_head, &random_head, song);
				printf("\nThe song %s is deleted from the list!", song);
			}
			else printf("\nThere is no song current in the playlist.");
			

		
		}
		else if(choice==3){
			cleanRandomList(chrono_head);
			random_head=createRandomList(chrono_head);
			printList(chrono_head, alpha_head, duration_head, random_head);
			
		}
		
		else if(choice==4){
			printf("Enter a file name: ");
			char fileName[100];
			getchar();
			gets(fileName);
			FILE *filePtr2=fopen(fileName, "w");
			
			writeFile(chrono_head, alpha_head, duration_head, random_head, filePtr2);
			printf("Output is printed to the file %s", fileName);
			int node_count=countNodes(chrono_head);
			int i;
			
		}
		
	}
	
}
