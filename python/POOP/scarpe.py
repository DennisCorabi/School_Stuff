from tkinter import *
from tkinter import messagebox
import sqlite3

class Scarpa:
    def __init__(self,magazzino, marca, modello, taglia):
        self.marca=marca
        self.taglia=taglia
        self.modello=modello
        magazzino.inventario_scarpe.append(self)


        conn=sqlite3.connect("scarpe.db")
        cursor=conn.cursor()
        command="INSERT INTO {} VALUES(\"{}\",\"{}\",{})".format(magazzino.nome,self.marca,self.modello,self.taglia)
        print(command)
        cursor.execute(command)
        conn.commit()
        conn.close()
        
class Magazzino:
    def __init__(self,nome,capienza):
        self.nome=nome
        self.capienza=capienza
        self.inventario_scarpe=[]

#FUNCTIONS

"""
AGGIUNGI SCARPA: aggiungi una scarpa nel magazzino correntemente in uso (DA FARE UN'ELENCO DI TUTTI I MAGAZZINI)
"""
def add_scarpa():
    scarpa=Scarpa(magazzino,form_marca.get(),form_modello.get(),form_taglia.get())
    form_marca.delete(0,END)
    form_modello.delete(0,END)
    form_taglia.delete(0,END)
"""
def delete_window(oid_list): 

    conn=sqlite3.connect("scarpe.db")
    cursor=conn.cursor()

    scelta=IntVar()
    window=Toplevel()
    frame=LabelFrame(window,text="Rimuovi scarpa")
    frame.grid(column="0",row="0",padx="5",pady="5")
    cont=0

    for element in oid_list:
        command="SELECT * FROM {} WHERE oid={}".format(magazzino.nome,element[0])
        cursor.execute(command)
        temp_list=cursor.fetchall()
        for colonna in temp_list:
            Radiobutton(frame,text="{}; {}; {}".format(colonna[0],colonna[1],colonna[2]),variable=scelta,value=cont).grid(column="0",row=cont, padx="5",pady="5")
        cont=cont+1

    submit_bt=Button(frame,text="Invia...").grid(row=cont+1,column="0",padx="5",pady="5")
    #RIUSCIRE A FAR TORNARE IL VALORE DELLA SCELTA
    
        #TODOs
"""        
def delete_scarpa():
    conn=sqlite3.connect("scarpe.db")
    cursor=conn.cursor()
    command="SELECT oid FROM {} WHERE marca=\"{}\" AND modello=\"{}\"".format(magazzino.nome,form_marca_del.get(),form_modello_del.get())
    cursor.execute(command)
    oid_list=cursor.fetchall()

    if len(oid_list)==1:
        for element in oid_list[0]:
            oid=element
    else:
        #RISOLVERE IL CONFLITTO SU CHE SCARPA ELIMINARE
        pass
            
    colonne=("marca","modello","taglia")
    for element in colonne:
        command="UPDATE {} SET {}=NULL WHERE oid={}".format(magazzino.nome,element,oid)
        cursor.execute(command)

    conn.commit()
    conn.close()
    
    

"""
CREA UN NUOVO MAGAZZINO (ovvero uno oggetto della classe 'Magazzino'
e la rispettiva tabella (con lo stesso nome) nel database)
"""

def create():
    global magazzino
    magazzino=Magazzino(form_creamagazzino.get(),300)

    conn=sqlite3.connect("scarpe.db")
    cursor=conn.cursor()
    command="CREATE TABLE {}(marca text, modello text, taglia integer)".format(magazzino.nome)
    cursor.execute(command)
    conn.commit()
    conn.close()
    messagebox.showinfo("Informazione","Il magazzino e' stato creato.")
    frame_newmagazzino.destroy()
    view_bt=Button(root,text="Visualizza database").grid(row="1",column="0",padx="5",pady="5")

    view_magazzino()
    
def fetch_tables():
    global lista
    conn=sqlite3.connect("scarpe.db")
    cursor=conn.cursor()
    cursor.execute("SELECT name FROM sqlite_master WHERE type='table' AND name NOT LIKE 'sqlite_%'")
    conn.commit()
    lista=cursor.fetchall()

def fetch_scarpe():
    conn=sqlite3.connect("scarpe.db")
    cursor=conn.cursor()
    command="SELECT * FROM {}".format(magazzino.nome)
    cursor.execute(command)
    lista=cursor.fetchall()
    return lista

def view_magazzino(finestra):
    frame_visualizza=LabelFrame(finestra,text="Visualizza {}".format(magazzino.nome))
    frame_visualizza.grid(row="0",column="0", padx="5",pady="5")
    lista=fetch_scarpe()

    label_marca=Label(frame_visualizza,text="MARCA").grid(padx="5",row="0",column="0",pady="5")
    label_modello=Label(frame_visualizza,text="MODELLO").grid(padx="5",row="0",column="1",pady="5")
    label_taglia=Label(frame_visualizza,text="TAGLIA").grid(padx="5",row="0",column="2",pady="5")
    cont=0
    for element in lista:
        scarpa_marca=Label(frame_visualizza,text=element[0]).grid(row=cont+1,column="0",pady="5",padx="5")
        scarpa_modello=Label(frame_visualizza,text=element[1]).grid(row=cont+1, column="1",pady="5",padx="5")
        scarpa_taglia=Label(frame_visualizza,text=element[2]).grid(row=cont+1, column="2",pady="5",padx="5")
        cont=cont+1
    update_db=Button(finestra,text="Aggiorna",command=lambda: view_magazzino(finestra)).grid(padx="5",row="1",column="0",columnspan="3",pady="5")

def new_window():
    window=Toplevel()
    view_magazzino(window)
"""   
#GUI
FRONT-END DEL PROGRAMMA.

IN ORDINE:
    -ROOT
    -FRAME: contiene tutti i widget concerni l'inserimento di scarpe
        -INSERIMENTO MARCA: label & entry
        -INSERIMENTO MODELLO: label & entry
        -INSERIMENTO TAGLIA: label & entry
        -SUBMIT BUTTON: per inserire la nuova scarpa nel database
        
    -CREAZIONE NUOVO MAGAZZINO: frame_add, label, entry & submit button
"""
root=Tk()
root.title("Gestione scarpe")


"""
FRAME 0,0 AGGIUNTA SCARPE
"""
frame_add=LabelFrame(root,text="Aggiungi scarpe")
frame_add.grid(column="0",row="0",padx="5",pady="5")
label_marca=Label(frame_add,text="Inserisci la marca: ").grid(column="0",row="0",padx="5",pady="5")
form_marca=Entry(frame_add)
form_marca.grid(column="1",row="0",padx="5",pady="5")
label_modello=Label(frame_add,text="Inserisci il modello: ").grid(column="0",row="1",padx="5",pady="5")
form_modello=Entry(frame_add)
form_modello.grid(column="1",row="1",padx="5",pady="5")
label_taglia=Label(frame_add,text="Inserisci la taglia: ").grid(column="0",row="2",padx="5",pady="5")
form_taglia=Entry(frame_add)
form_taglia.grid(column="1",row="2",padx="5",pady="5")
add_magazzino=Button(frame_add,text="Aggiungi al magazzino", command=add_scarpa).grid(column="0",columnspan="3",row="3",padx="5",pady="10")

"""
FRAME 0,1 RIMOZIONE SCARPE
"""
frame_delete=LabelFrame(root,text="Rimuovi scarpe")
frame_delete.grid(column="1",row="0",padx="5",pady="5")
label_marca_del=Label(frame_delete,text="Inserisci la marca: ").grid(column="0",row="0",padx="5",pady="5")
form_marca_del=Entry(frame_delete)
form_marca_del.grid(column="1",row="0",padx="5",pady="5")
label_modello_del=Label(frame_delete,text="Inserisci il modello: ").grid(column="0",row="1",padx="5",pady="5")
form_modello_del=Entry(frame_delete)
form_modello_del.grid(column="1",row="1",padx="5",pady="5")
del_magazzino=Button(frame_delete,text="Rimuovi dal magazzino", command=delete_scarpa).grid(column="0",columnspan="3",row="2",padx="5",pady="10")

"""
FRAME 1,0 CREAZIONE NUOVO MAGAZZINO SE NON GIA' ESISTENTE
"""

fetch_tables()
if len(lista)==0:
    frame_newmagazzino=LabelFrame(root,text="Crea magazzino")
    frame_newmagazzino.grid(row="1",column="0",columnspan="2",padx="5",pady="5")
    label_creamagazzino=Label(frame_newmagazzino,text="Inserisci il nome del magazzino: ").grid(column="0",row="1",padx="5",pady="5")
    form_creamagazzino=Entry(frame_newmagazzino)
    form_creamagazzino.grid(column="1",row="1",padx="5",pady="5")
    button_creamagazzino=Button(frame_newmagazzino,text="Invia",command=create).grid(column="2",row="1",padx="5",pady="5")
else:
    for element in lista[0]:
        magazzino=Magazzino(element,300)
    view_bt=Button(root,text="Visualizza database", command=new_window).grid(row="1",column="0",padx="5",pady="5")

root.mainloop()

"""
IDEE DA ULTIMARE:
    -creare una lista dove si possono vedere e rendere attivi tutti i magazzini salvati nel database. (FATTO)
    -dare gli strumenti all'utente di selezionare, divide per colonne e rimuovere le scarpe (PRINCIPALE)
    -riuscire ad aggiornare la visualizzazione delle scarpe
"""