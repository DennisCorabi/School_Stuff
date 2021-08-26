from tkinter import *

lista_spesa=list()

def add_list():
    lista_spesa.append(spesa_form.get())
    ingredients=str()
    cont=0
    for element in lista_spesa:
        if len(element)>0:
            elements_label=Label(frame_ingredients,text="-"+element).grid(row=cont,column="0",padx="10",pady="5")
            cont=cont+1


def create_checkbox():
    x=IntVar()
    cont=0
    for element in lista_spesa:
        checkbox=Checkbutton(frame_checkboxes, variable=x, text=element, onvalue=cont+1, offvalue="0", padx="10", pady="5").grid(row=cont, column="0")
        cont+=cont+1


root=Tk()
root.title("Lista della spesa")
frame=LabelFrame(root, text="Spesa")
frame.pack()

frame_ingredients=LabelFrame(frame, text="Elementi")
frame_ingredients.grid(row="1",column="0")

frame_checkboxes=LabelFrame(frame)
frame_checkboxes.grid(row="1",column="1",padx="10",pady="10")

add_label=Label(frame,text="Aggiungi ingrediente: ").grid(row="0",column="0",padx="10")
spesa_form=Entry(frame, width="35")
spesa_form.grid(column="1",row="0",padx="5")

add_button=Button(frame, text="aggiungi...", command=add_list)
add_button.grid(row="0",column="2",padx="10",pady="10")


submit_button=Button(frame,text="Submit...", command=create_checkbox).grid(row="0",column="3",padx="10")

root.mainloop()