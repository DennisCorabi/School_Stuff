from tkinter import *

root=Tk()
root.title("calcolatrice")
frame=LabelFrame(root,text="Calcolatrice",padx="10",pady="10")
frame.grid(row="0",column="0")

casella=Entry(frame,width="35")
casella.grid(row="0",column="0",columnspan="4", padx="10")

#FUNCTIONS
def inserimento(evento,numero,):
    num=casella.get()+str(numero)
    casella.delete(0,END)
    casella.insert(0,num)
    
def clear():
    casella.delete(0,END)

def prima_var(evento,operazione):
    global var1
    var1=casella.get()
    casella.delete(0,END)
    global math
    math=operazione

def risultato(evento):
    var2=casella.get()
    # per debug: print("var1: {} var2: {}".format(var1,var2))
    casella.delete(0,END)

    if math=="somma":
        casella.insert(0,str(float(var1)+float(var2)))
    elif math=="sottrazione":
        casella.insert(0,str(float(var1)-float(var2)))
    elif math=="moltiplicazione":
        casella.insert(0,str(float(var1)*float(var2)))
    elif math=="divisione":
        casella.insert(0,str(float(var1)/float(var2)))


#bottoni

bt7=Button(frame,text="7",padx="50", pady="20", command=lambda: inserimento(Event,7))
bt8=Button(frame,text="8",padx="50", pady="20", command=lambda: inserimento(Event,8))
bt9=Button(frame,text="9",padx="50", pady="20", command=lambda: inserimento(Event,9))
bt4=Button(frame,text="4",padx="50", pady="20", command=lambda: inserimento(Event,4))
bt5=Button(frame,text="5",padx="50", pady="20", command=lambda: inserimento(Event,5))
bt6=Button(frame,text="6",padx="50", pady="20", command=lambda: inserimento(Event,6))
bt1=Button(frame,text="1",padx="50", pady="20", command=lambda: inserimento(Event,1))
bt2=Button(frame,text="2",padx="50", pady="20", command=lambda: inserimento(Event,2))
bt3=Button(frame,text="3",padx="50", pady="20", command=lambda: inserimento(Event,3))
bt0=Button(frame,text="0",padx="50", pady="20", command=lambda: inserimento(Event,0))

btdot=Button(frame,text=".", padx="50", pady="20", command=lambda: inserimento(Event,"."))
btadd=Button(frame, text="+",padx="50",pady="20", command=lambda: prima_var(Event,"somma")) 
btsub=Button(frame, text="-",padx="50",pady="20", command=lambda: prima_var(Event,"sottrazione"))
btmultiply=Button(frame, text="*",padx="50",pady="20", command=lambda: prima_var(Event,"moltiplicazione"))
btdiv=Button(frame, text="/",padx="50",pady="20", command=lambda: prima_var(Event,"divisione"))
btequal=Button(frame, text="=",padx="50",pady="20", command=risultato)
btcls=Button(frame, text="CE", padx="50", pady="20", command=clear)
#keybinds dei bottoni
root.bind('7',lambda event: inserimento(Event,7))
root.bind('8',lambda event: inserimento(Event,8))
root.bind('9',lambda event: inserimento(Event,9))
root.bind('4',lambda event: inserimento(Event,4))
root.bind('5',lambda event: inserimento(Event,5))
root.bind('6',lambda event: inserimento(Event,6))
root.bind('1',lambda event: inserimento(Event,1))
root.bind('2',lambda event: inserimento(Event,2))
root.bind('3',lambda event: inserimento(Event,3))
root.bind('0',lambda event: inserimento(Event,0))

root.bind('.',lambda event: inserimento(Event,"."))
root.bind(',',lambda event: inserimento(Event,"."))

root.bind('<Return>',risultato)
root.bind('+',lambda event: prima_var(Event,"somma"))
root.bind('-',lambda event: prima_var(Event,"sottrazione"))
root.bind('*',lambda event: prima_var(Event,"moltiplicazione"))
root.bind('/',lambda event: prima_var(Event,"divisione"))




#placement dei bottoni
bt7.grid(row="1",column="1")
bt8.grid(row="1",column="2")
bt9.grid(row="1",column="3")
bt4.grid(row="2",column="1")
bt5.grid(row="2",column="2")
bt6.grid(row="2",column="3")
bt1.grid(row="3",column="1")
bt2.grid(row="3",column="2")
bt3.grid(row="3",column="3")
bt0.grid(row="4",column="1")

btdot.grid(row="4", column="2")
btadd.grid(row="1",column="4")
btsub.grid(row="2",column="4")
btmultiply.grid(row="3",column="4")
btdiv.grid(row="4",column="4",)
btequal.grid(row="4",column="3",columnspan="1")
btcls.grid(row="0",column="4")




root.mainloop()