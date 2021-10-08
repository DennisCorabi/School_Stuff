#include<stdio.h>
#include<stdlib.h>


struct node{
    int elemento;
    int index;
    struct node * next;
};

void printList(node * testa);
node *  addlist(int element, node * head);
void addAt(int element, int index, node * head);
node * elementAt(int indice, node * head);

/*
FUNZIONE main:
    funzione principale utilizzata in questo programma sopratutto come luogo per i test."
    */

int main(void){
    node *lista = NULL;
    lista=addlist(1,lista);
    lista=addlist(10,lista);
    lista=addlist(5,lista);
    addAt(7,2,lista);
    printList(lista);
}

/*
FUNZIONE addlist:
    creazione di un nuovo nodo ed inserimento dello stesso nella lista la cui "testa"
    (ultimoè passata alla funzione come secondo argomento.*/

node* addlist(int element, node *head){
    node * newnode;                 //creazione nuovo nodo (da poi inserire nella lista).
    node *temp =head;               //creazione nodo temporaneo (utilizzato nel ciclo while per non toccare 'head'.)
    newnode = (node*) malloc(sizeof(struct node));  //alloco dello spazio nella ram per il nuovo nodo
    newnode->elemento =element; //inserisco come contenuto nel nodo il numero passato alla funzione come primo argomento.
    newnode->next = NULL;   //il nuovo nodo punterà ovviamente al vuoto (per ora).

    if (head == NULL) {             //se stiamo inserendo il primo elemento della lista, allora lo inserisco direttamente all'inizio della lista senza dover ricercare la "testa" di essa.
        newnode->index=0;           //sarà il primo nodo della lista, quindi 0.
        head = newnode;             //copio il contenuto del nuovo nodo (numero, indice e puntatore) nella testa della lista.
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
FUNZIONE addAt:
    sostituzione dell'elemento di un nodo dato il suo indice e la lista a cui appartiene.
*/

void addAt(int element, int indice, node * head){
    node * temp=elementAt(indice,head);
    temp->elemento=element;     //sostituisco il valore del nodo con l'indice ricercato.
}

/*
FUNZIONE printList:
    stampa tutti i nodi di una lista fino a quando non si arriva al nodo finale.*/
    
void printList(node * head){
    while (head!=NULL){             //finchè un nodo non punta a NULL (ovvero finchè non è l'ultimo nodo della lista).
        printf("%d: %d -> ",head->index,head->elemento);        //stampo indice e contenuto del nodo.
        head=head->next;        //passo al nodo successivo.
    }
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
    printf("numero trovato alla posizione %d: %d\n",indice,temp->elemento);     //stampa il contenuto del nodo all'indice ricercato.
    return temp;
}
