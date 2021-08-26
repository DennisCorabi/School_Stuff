from tkinter import *
from tkinter import messagebox
import datetime
import time
import sqlite3
import os

#FUNCTION ZONE

def update(obj,old,new):
    conn=sqlite3.connect("users.db")
    cursor=conn.cursor()

    #dato il vecchio nome, preleva dalla tabella l'oid (la riga) dell'utente che ha quel nome
    command_fetch="SELECT *,oid FROM users WHERE {}=\"{}\"".format(obj,old)
    cursor.execute(command_fetch)
    conn.commit()
    oid=cursor.fetchall()
    #dato l'oid dell'utente di cui vogliamo cambiare dei dati, modifichiamo i dati, simple.
    if len(oid)==0:
        messagebox.showinfo("Warning", "there isn't such user storaged in our database whose {} is {}".format(obj,old))
    else:
        oid_list=oid[0]

        if len(oid)>1:
            messagebox.showinfo("Warning","Multiple users share the {} {}.\nOnly the first user whose {} is {} got his {} changed in {}.".format(obj,old,obj,old,obj,new))

        command_update="UPDATE users SET {}=\"{}\" WHERE oid={}".format(obj,new,oid_list[-1])
        print(command_update)
        #cursor.execute(command_update)

        #AGGIORNO LA DATA DI ULTIMA MODIFICA DELL'UTENTE 
        date_lastupdate=datetime.datetime.now()
        date_lastupdate=date_lastupdate.strftime("%H:%M:%S %d/%m/%Y %z")
        command_update_date="UPDATE users SET date_lastupdate=\"{}\" WHERE oid={}".format(date_lastupdate,oid_list[-1])
        #cursor.execute(command_update_date)
        conn.commit()

        #AGGIORNO LA TABELLA PRESENTE NELLA FINESTRA CON TUTTI GLI UTENTI


def update_age(name,age):
    conn=sqlite3.connect("users.db")
    cursor=conn.cursor()
    command_update="UPDATE users SET age={} WHERE name=\"{}\"".format(age,name)
    cursor.execute(command_update)

def destroy_window(Event):
    root.destroy()
    
#TKINTER ZONE
root=Tk()
root.title("Update users informations")
root.bind('<Escape>',destroy_window)
cont=0

"""
UPDATE NAME LABEL-FORM-SUBMIT BUTTON
"""
update_name_frame=LabelFrame(root,text="Update Name")
update_name_frame.grid(row="0",column="0", padx="10", pady="5")

old_name_label=Label(update_name_frame,text="OLD NAME:").grid(column="0",row="0",padx="5",pady="5")
old_name_form=Entry(update_name_frame)
old_name_form.grid(column="1",row="0",padx="5",pady="5")

new_name_label=Label(update_name_frame,text="NEW NAME:").grid(column="0",row="1",padx="5",pady="5")
new_name_form=Entry(update_name_frame)
new_name_form.grid(column="1",row="1",padx="5",pady="5")

submit_name=Button(update_name_frame,text="Submit",command=lambda: update("name",old_name_form.get(), new_name_form.get()))
submit_name.grid(column="0",columnspan="2",row="2",padx="5",pady="5")

"""
UPDATE SURNAME LABEL-FORM-SUBMIT BUTTON
"""
update_surname_frame=LabelFrame(root,text="Update Surname")
update_surname_frame.grid(row="1",column="0", padx="10", pady="5")

old_surname_label=Label(update_surname_frame,text="OLD SURNAME:").grid(column="0",row="0",padx="5",pady="5")
old_surname_form=Entry(update_surname_frame)
old_surname_form.grid(column="1",row="0",padx="5",pady="5")

new_surname_label=Label(update_surname_frame,text="NEW SURNAME:").grid(column="0",row="1",padx="5",pady="5")
new_surname_form=Entry(update_surname_frame)
new_surname_form.grid(column="1",row="1",padx="5",pady="5")

submit_surname=Button(update_surname_frame,text="Submit",command=lambda: update("surname",old_surname_form.get(), new_surname_form.get()))
submit_surname.grid(column="0",columnspan="2",row="2",padx="5",pady="5")


"""
UPDATE USER AGES LABEL-FORM-SUBMIT BUTTON
"""

update_age_frame=LabelFrame(root,text="Update Age")
update_age_frame.grid(row="2",column="0")

insert_name=Label(update_age_frame, text="USER NAME: ").grid(column="0",row="0",padx="5",pady="5")
insert_name_form=Entry(update_age_frame)
insert_name_form.grid(row="0",column="1", padx="5", pady="5")

insert_age=Label(update_age_frame,text="NEW AGE: ").grid(row="1",column="0")
insert_age_form=Entry(update_age_frame)
insert_age_form.grid(row="1",column="1", padx="5",pady="5")

submit_age=Button(update_age_frame,text="Submit", command=lambda: update_age(insert_name_form.get(),insert_age_form.get()))
submit_age.grid(row="3",column="0",columnspan="2",padx="5",pady="5")



"""
VIEW ALL USERS REGISTERED (NAME,SURNAME,AGE)
"""
show_info_frame=LabelFrame(root, text="Users saved informations")
show_info_frame.grid(column="1",row="0",padx="10")
show_names_label=Label(show_info_frame,text="NAME:").grid(padx="5",pady="5",column="0",row="0")
show_surnames_label=Label(show_info_frame,text="SURNAME:").grid(padx="5",pady="5",column="1",row="0")
show_ages_label=Label(show_info_frame,text="AGE:").grid(padx="5",pady="5",column="2",row="0")

conn=sqlite3.connect("users.db")
cursor=conn.cursor()
cursor.execute("SELECT name,surname,age FROM users")
infos=cursor.fetchall()
for element in infos:
    names_label=Label(show_info_frame, text=element[0]).grid(row=cont+1, column="0")
    surnames_label=Label(show_info_frame, text=element[1]).grid(row=cont+1, column="1")
    ages_label=Label(show_info_frame, text=element[2]).grid(row=cont+1, column="2")
    cont=cont+1

insertion_program=Button(root,text="Insert users", command=lambda: os.system("python3 users_stuff/registro_persone.py"))
insertion_program.grid(row="3",column="0", columnspan="2", pady="20")

root.mainloop()