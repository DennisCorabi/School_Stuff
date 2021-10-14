#include<stdio.h>
#include<stdlib.h>


struct node{
    int elemento;
    int index;
    struct node * next;
};

node *  addlist(node * head);
void printList(node * head);
void addAt(node * head);
node * elementAt(int indice, node * head);
int sizeList(node * head);
void sortList(node * head);
node * addMany(node * head);

/*
FUNZIONE main:
    funzione principale utilizzata in questo programma come menu per accedere alle varie funzioni.
    */

int main(void){
    node *lista = NULL;     //inizializzazione di una lista vuota, per ora.
    int scelta;
    do{
        printf("\ncosa vuoi fare?\n");
        printf("1: aggiungi nodo alla lista.\n2:stampa contentuto di una lista.\n");
        printf("3: sostituisci un elemento in un nodo della lista\n4:ordina gli elementi della lista.\n");
        printf("5: inserisci tanti nodi quanti ne vuoi!\n");
        printf("0: esci dal programma.\n");
        printf("scelta: ");
        scanf("%d",&scelta);
        switch(scelta){
            case 1:
                lista=addlist(lista);
                printf("\n\nnodo aggiunto nella lista.\n\n");
                break;
            case 2:
                printList(lista);
                break;
            case 3:
                addAt(lista);
                printf("nodo sostituito.\n\n");
                break;
            case 4:
                sortList(lista);
                break;
            case 5:
                lista=addMany(lista);
            case 0:
                break;
            default:
                printf("inserisci un numero corrispondente alle varie opzioni!\n");
                break;
                
        }

    }while(scelta!=0);
}

/*
FUNZIONE addlist:
    creazione di un nuovo nodo ed inserimento dello stesso nella lista la cui "testa" 
    è passata alla funzione come primo argomento.*/

node* addlist(node *head){
    node * newnode;                 //creazione nuovo nodo (da poi inserire nella lista).
    node *temp =head;               //creazione nodo temporaneo (utilizzato nel ciclo while per non toccare 'head'.)
    newnode = (node*) malloc(sizeof(struct node));  //alloco dello spazio nella ram per il nuovo nodo
    printf("inserisci un numero: ");  //inserisco come contenuto nel nodo un numero scelto dall'utente.
    scanf("%d",&newnode->elemento);
    newnode->next = NULL;   //il nuovo nodo punterà al vuoto (per ora).

    if (head == NULL) {             //se stiamo inserendo il primo elemento della lista, allora lo inserisco direttamente all'inizio della lista senza dover ricercare la "testa" di essa.
        newnode->index=0;           //sarà il primo nodo della lista, quindi 0.
        head = newnode;             //copio il contenuto del nuovo nodo (numero, indice e puntatore) nella base della lista.
        return head;
    }
    int i=1;            //creo variabile che mi conterà quanti nodi ha la lista ad ogni inserimento
    while(temp->next != NULL){
        temp = temp->next;              //finchè non arrivo alla fine della lista, rimango nel ciclo while.
        i++;
    }
    newnode->index=i;               //i è un numero variabile
    temp->next = newnode;           //dopo aver trovato la fine della lista, aggiungo un altro nodo contenente le informazioni di newnode.
    return head;
}

/*
FUNZIONE addMany:
    creazione di tanti nodi quanti ne vuole inserire l'utente.
    Dopo aver chiesto il numero di nodi da creare, chiamata la funzione addlist tante volte quanti sono i nodi da creare.
*/

node * addMany(node * head){
    int cont;
    do{
        printf("quanti nodi vuoi inserire all'interno della lista (maggiori di 0) ? ");     //chiedo all'utente il numero di nodi da inserire.
        scanf("%d",&cont);
    }while(cont<=0);     

    for (int i=0;i<cont;i++){
        head=addlist(head);         //chiamo 'cont' volte la funzione che permette di creare ed inserire un nodo nella lista.
    }
    return head;
}

/*
FUNZIONE addAt:
    sostituzione dell'elemento di un nodo dato il suo indice e la lista a cui appartiene.
*/

void addAt(node * head){
    int index,size=sizeList(head),sostituto;

    do{
        printf("inserisci l'indice del nodo da ricercare (minore e uguale a %d): ",size-1); //rimane nel ciclo do-while finchè l'indice da ricercare non è minore della grandezza della lista.
        scanf("%d",&index);

    }while(index>size);

    printf("inserisci nuovo numero: ");         //inserisco il numero da sostituire
    scanf("%d",&sostituto);
  
    node * temp=elementAt(index,head);      //funzione che permette di ricercare un particolare nodo della lista in base alla sua posizione all'interno di essa.
    temp->elemento=sostituto;     //sostituisco il valore del nodo con l'indice ricercato.
}

/*
FUNZIONE printList:
    stampa tutti i nodi di una lista fino a quando non si arriva al nodo finale.*/
    
void printList(node * head){
    printf("\n\n");
    while (head!=NULL){             //finchè un nodo non punta a NULL (ovvero finchè non è l'ultimo nodo della lista).
        printf("%d: %d -> ",head->index,head->elemento);        //stampo indice e contenuto del nodo.
        head=head->next;        //passo al nodo successivo.
    }
    printf("\n\n");
}


/*
FUNZIONE elementAt:
    dato un indice n, ritorno alla funzione chiamante il nodo di posizione n all'interno della lista.
    Funzione molto utile utilizzabile per diverse funzioni.*/

node * elementAt(int indice, node * head){
    node * temp=head;
    while (indice!=temp->index){        //rimane nel ciclo while finchè non trova un nodo con l'indice desiderato.
        temp=temp->next;
    }
    return temp;        //ritorna il nodo che si trova nell'indice indicato.
}

/*
FUNZIONE sizeList:
    Ritorna alla funzione chiamante la grandezza della lista.
*/
int sizeList(node * head){
    int i=0;
    while (head!=NULL){
        head=head->next;            //rimane nel ciclo while finchè non arriva alla fine della lista (ovvero al nodo che punta a NULL)
        i++;
    }
    return i;       //ritorna la variabile contenente la grandezza della lista.
}

/*
FUNZIONE sortList:
    data una lista come parametro, preleva i numeri contenuti all'interno dei i nodi della lista 
    inserendoli all'interno di un vettore. Infine, li riordina con un semplice algoritmo di bubble sort.
*/

void sortList(node * head){


    node * temp=head;               //creo nodo temporaneo
    int size=sizeList(head);        //definisco la grandezza della lista grazie alla funzione sizelist
    int vet[size];

    for (int i=0;i<size;i++)        //for che controlla tutti i nodi della lista.
    {
        vet[i]=temp->elemento;          //inserisco l'elemento di un nodo all'interno del vettore.
        temp=temp->next;                //passa al prossimo nodo
    }

    for (int i=0;i<size;i++){
        for (int y=0;y<i;y++){
            if (vet[y]>vet[i])              //ALGORITMO DI BUBBLE SORT.
            {
                int temp=vet[i];
                vet[i]=vet[y];
                vet[y]=temp;
            }
        }
    }
    printf("\n\nelementi della lista ordinati: ");     
    for (int i=0;i<size;i++){           //stampo gli elementi del vettore, ora ordinati in modo crescente.
        printf("%d; ",vet[i]);
    }
    printf("\n\n");
}
