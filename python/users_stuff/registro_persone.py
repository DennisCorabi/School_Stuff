import time
import random
import datetime
from sqlite3.dbapi2 import connect
from tkinter import *
from typing import Collection, Counter

from tkinter import messagebox
from tkinter import filedialog
import sqlite3
import os


#CLASS ZONE
class utente:
    def __init__(self,nome,cognome,eta,creationdate):
        self.nome=nome
        self.cognome=cognome
        self.eta=eta


#FUNCTIONS ZONE

"""
CHECK FUNCTION: verifies if a user with same name, surname and age is already registered
"""

def check():

    #sqlite3
    conn=sqlite3.connect("users.db")
    cursor=conn.cursor()
    cursor.execute("SELECT * FROM users")
    utenti=cursor.fetchall()
    conn.commit()

    for element in utenti:
        if form_noun.get()==element[0] and form_surname.get()==element[1] and int(form_age.get())==element[2]:
            return True
"""
CLEAR ALL FUNCTION: Wipes out everything written in the name, surname and age forms
"""

def clear_all():
    form_noun.delete(0,END)
    form_surname.delete(0,END)
    form_age.delete(0,END)

"""
SUBMIT FUNCTION: Store the user info in a database ONLY if the three forms are compiled (first condition) 
                and if the user isn't already registed (second condition, if the CHECK FUNCTION return TRUE).
"""
def submit(key_bind):

    if len(form_noun.get())==0 or len(form_surname.get())==0 or len(form_age.get())==0:
            messagebox.showwarning("Error","Invalid entry.")
    else:
        result=check()
        if result==True:
            messagebox.showwarning("Warning","The user is already registered in the database.")
            clear_all()
            return
        else:
            creation_date=datetime.datetime.now()
            creation_date=creation_date.strftime("%H:%M:%S %d/%m/%Y %z")
            user=utente(form_noun.get(),form_surname.get(),form_age.get(),creation_date)

            conn=sqlite3.connect("users.db")
            cursor=conn.cursor()
            cursor.execute("INSERT INTO users VALUES (:name, :surname, :age, :date_creation, :date_lastupdate)", 
                {
                    'name':user.nome,
                    'surname':user.cognome,
                    'age':user.eta,
                    'date_creation':user.creationdate,
                    'date_lastupdate':user.creationdate
                }
            )
            conn.commit()
            messagebox.showinfo("Information","The user has been registered in the database.")

        clear_all()

"""
DESTROY_WINDOW FUNCTION: simply destroys all the window created by the process.
                        This function was intended to destroy only the window the user is currently using, nontheless it works :P
"""
def destroy_window(key_pressed,window):
    window.destroy()

"""
DELETE TABLE FUNCTION: Wipes out everything storaged in the 'users.db' database after previous confirmation.
"""
def delete_table():
    response=messagebox.askyesno("Warning","Do you want to delete the entire user database?")
    if response==True:

        conn=sqlite3.connect("users.db")
        cursor=conn.cursor()
        cursor.execute("drop table if exists users")
        conn.commit()
        cursor.execute("CREATE TABLE users(name text, surname text, age integer, date_creation text, date_lastupdate text)")
        conn.commit()
        
        messagebox.showinfo("Information","The registered users have been canceled.")

"""
VIEW_USERS FUNCTION: creates a windows where every user storaged in the database in showed
"""
def view_users():
    
    #TKINTER DELLA SECONDA FINESTRA

    usr_window=Toplevel()
    usr_window_frame=LabelFrame(usr_window,text="User List")
    usr_window_frame.grid(column="0",row="0",columnspan="2")

    usr_name_label=Label(usr_window_frame,text="NAME").grid(padx="5",row="0",column="0",pady="5")
    usr_surname_label=Label(usr_window_frame,text="SURNAME").grid(padx="5",row="0",column="1",pady="5")
    usr_age_label=Label(usr_window_frame,text="AGE").grid(padx="5",row="0",column="2",pady="5")
    usr_creationdate_label=Label(usr_window_frame,text="CREATION DATE").grid(padx="5",pady="5",row="0",column="3")
    usr_lastupdate_label=Label(usr_window_frame,text="LAST UPDATE").grid(padx="5",pady="5",row="0",column="4")

    conn=sqlite3.connect("users.db")
    cursor=conn.cursor()
    cursor.execute("SELECT * FROM users")
    utenti=cursor.fetchall()
    conn.commit()
    cont=0
    for elements in utenti:
        usr_name=Label(usr_window_frame,text=elements[0]).grid(row=cont+1,column="0",pady="5",padx="5")
        usr_surname=Label(usr_window_frame,text=elements[1]).grid(row=cont+1, column="1",pady="5",padx="5")
        usr_surname=Label(usr_window_frame,text=elements[2]).grid(row=cont+1, column="2",pady="5",padx="5")
        user_creationdate=Label(usr_window_frame,text=elements[3]).grid(row=cont+1,column="3",pady="5",padx="5")
        user_lastupdate=Label(usr_window_frame,text=elements[4]).grid(row=cont+1,column="4",pady="5",padx="5")
        cont=cont+1  

    exit_button=Button(usr_window,text="Close Window",command=lambda: destroy_window(Event,usr_window))
    exit_button.grid(row=cont+1,column="0",columnspan="2",pady="15")
    usr_window.bind('<Escape>',lambda event: destroy_window(Event,root))


"""
IMPORT_TABLE FUNCTION:  from the file explorer, import all the users from another database in your pc to the one used in this program to store them.
"""
def import_table():

    #2° Database: Fetch all users
    root.filename=filedialog.askopenfilename(initialdir="/home/dennis",title="Open file")
    conn=sqlite3.connect(root.filename)
    cursor=conn.cursor()
    cursor.execute("SELECT * FROM users")
    uploaded_user=cursor.fetchall()
    conn.commit()

    #Main database: insert all the users that have been previously fetched 
    conn=sqlite3.connect("users.db")
    cursor=conn.cursor()
    for element in uploaded_user:
        cursor.execute("INSERT INTO users VALUES (:name, :surname, :age, :date_creation, :date_lastupdate)",
            {
                "name":element[0],
                "surname":element[1],
                "age":element[2],
                "date_creation":element[3],
                "date_lastupdate":element[4]

            }
        )
    conn.commit()
    conn.close()
    messagebox.showinfo("Information","The user table has been uploaded.")
    return

"""
TKINTER ZONE; In order: 
    -ROOT + MAIN LABELFRAME
    -LABEL NOUN + FORM NOUN
    -LABEL SURNAME + FORM
    -LABEL AGE
    -VARIOUS BUTTONS (SUBMIT BUTTON, CLEAR ALL FORMS BUTTON, DELETE DATABASE BUTTON, VIEW USERS BUTTON, ETC...)

"""
root=Tk()
root.title("Registration form")
frame=LabelFrame(root,padx="5",pady="5")
frame.pack()

#nome
label_noun=Label(frame,text="insert your name: ")
form_noun=Entry(frame,width="35")

label_noun.grid(row="0",column="0", padx="5")
form_noun.grid(row="0",column="1", padx="5", columnspan="3",pady="10")

#cognome
label_surname=Label(frame,text="insert your surname: ")
form_surname=Entry(frame,width="35")

label_surname.grid(row="1",column="0", padx="5")
form_surname.grid(row="1",column="1", padx="5", columnspan="3", pady="10")

#età
label_age=Label(frame,text="insert your age: ")
form_age=Entry(frame,width="35")

label_age.grid(row="2",column="0", padx="5")
form_age.grid(row="2",column="1", padx="5", columnspan="3",pady="10")


#submit
button_submit=Button(frame, text="Submit",command=lambda: submit(Event))
button_submit.grid(row="3",column="2",pady="5")
root.bind('<Return>',submit)

#clear all
button_cls=Button(frame,text="Clear", command=lambda: clear_all())
button_cls.grid(row="1", column="4", padx="15")

#view_users
button_vu=Button(frame,text="view registered users",command=view_users)
button_vu.grid(row="4",column="0", padx="10")

#import_table
button_import=Button(frame,text="Import users from file...",command=import_table)
button_import.grid(row="4",column="2",pady="5")

#delete_table
button_delete=Button(frame,text="Delete table", command=delete_table)
button_delete.grid(row="5",column="2",pady="5")

#Start_update_program
update_program=Button(frame,text="Update users",command=lambda: os.system("python3 users_stuff/update_db.py"))
update_program.grid(row="5",column="4", pady="5")

#exit_button
exit_button=Button(frame,text="Close Program",command=lambda: destroy_window(Event,root)).grid(row="4",column="4")
root.bind('<Escape>',lambda event: destroy_window(Event,root))

#update_program

root.mainloop()     #...to end'em all..