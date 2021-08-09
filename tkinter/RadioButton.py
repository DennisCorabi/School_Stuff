from tkinter import *


def submit(key_pressed):
    cont=int(form1.get())
    x=0
    scelta=IntVar()
    while x<cont:
        Radiobutton(frame,text="opzione {}".format(x+1),variable=scelta,value=x+1, command=lambda: esito(scelta.get())).grid(column="0",row=x, padx="5",pady="5")
        x=x+1


def clear_label():
    cls_label=Label(frame,text="                                     ")
    cls_label.grid(row=int(form1.get())+1,column="0")
    return 

def esito(value):
    if value!=6:
        clear_label()
        label_result=Label(frame,text="ho scelto l'opzione {}!".format(value))
        label_result.grid(row=int(form1.get())+1,column="0",padx="5",pady="5")
    else:
        clear_label()
        label_result=Label(frame,text="il valore 6 fa schifo.")
        label_result.grid(row=int(form1.get())+1,column="0",padx="5",pady="5")

root=Tk()
frame=LabelFrame(root)
frame.grid(column="0",row="1",padx="10",pady="10")

label1=Label(root,text="inserisci il numero di opzioni: ")
label1.grid(column="0",row="0",padx="10")

form1=Entry(root,width="35")
form1.grid(column="1",row="0")

button_submit=Button(root,text="Submit",padx="5",pady="5",command=lambda: submit(Event))
button_submit.grid(column="2",row="0",padx="10")
root.bind('<Return>',submit)


root.mainloop()