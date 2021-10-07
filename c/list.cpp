#include<stdio.h>
#include<stdlib.h>


struct node{
    int elemento;
    struct node * next;
};

void printList(node * testa);
node * addlist(int element, node * head);
void addAt(int element, int index, node * head);

int main(void){
    node *lista = NULL;
    lista=addlist(1,lista);
    lista=addlist(3,lista);
    lista=addlist(10,lista);
    lista=addlist(4,lista);
    lista=addlist(5,lista);
    addAt(5,1,lista);

    
}

node * addlist(int element, node *head){
    while (head!=NULL){
        head=head->next;
    }
    head = (node*) malloc(sizeof(head));
    head->elemento=element;
    head->next=NULL;
    printList(head);
    return head;
}

void addAt(int element, int index, node * head){
    int i=0;
    if (i<=index){
        while (i!=index){
            head=head->next;                //TODO
            i++;
        }
        printf("numero in posizione %d: %d.\n",index,head->elemento);
    }
}
void printList(node * head){
    while (head!=NULL){
        printf("%d -> ",head->elemento);
        head=head->next;
    }
}
