import os
import sys

class Processo():
    sons_list=[]
    def __init__(self,nome,pid):
        self.nome=nome
        self.pid=pid
        Processo.sons_list.append(self)

<<<<<<< HEAD
    def get_pid(self):
        return self.pid
    
    def get_nome(self):
        return self.nome
        

def parameter():
    if len(sys.argv)==2:
        global PROC
        if sys.argv[1].isdigit():
            PROC=Processo(find_name(),sys.argv[1])
        else:
            PROC=Processo(sys.argv[1],find_pid())
        init()

def find_name():
    for element in os.listdir("/proc"):
        if element.isdigit():
            status=open("/proc/{}/status".format(element),"r")
=======
def find_sons(proc):
    for processo in os.listdir("/proc"):
        if processo.isdigit():
            status=open("/proc/{}/status".format(processo),"r")
>>>>>>> b6206d3c1830aaf88949b30e361e06ee2582e517
            lines=status.read()
            lines=lines.split("\n")
            for line in lines:
                line=line.split(":\t")
<<<<<<< HEAD
                if line[0]=="Name":
                    proc_name=line[1]
                if line[0]=="Pid" and line[1]==sys.argv[1]:
                    return proc_name
    print("Non e' stato trovato alcun processo con il pid {}.".format(sys.argv[1]))

def find_pid():
    for element in os.listdir("/proc"):
        if element.isdigit():
            status=open("/proc/{}/status".format(element),"r")
=======
                if line[0]=="PPid" and line[1]==str(proc):
                    sons_list.append(processo)

def find_nipoti(processo):
    cont=0
    print("\n\t{} --- Nipoti del processo {}:\n".format(processo,PROC))
    for processo in os.listdir("/proc"):
        if processo.isdigit():
            status=open("/proc/{}/status".format(processo),"r")
>>>>>>> b6206d3c1830aaf88949b30e361e06ee2582e517
            lines=status.read()
            lines=lines.split("\n")
            for line in lines:
                line=line.split(":\t")
<<<<<<< HEAD
                if line[0]=="Pid":
                    proc_pid=line[1]
                if line[0]=="Name" and line[1]==sys.argv[1]:
                    return proc_pid
    print("Non e' stato trovato alcun processo con il nome {}.".format(sys.argv[1]))
=======
                if line[0]=="PPid" and line[1]==processo:
                    print("\t\t",processo)
                    cont=cont+1
    if cont==0:
        print("\t\t")
>>>>>>> b6206d3c1830aaf88949b30e361e06ee2582e517

def find_sons():
    for element in os.listdir("/proc"):
        if element.isdigit():
            status=open("/proc/{}/status".format(element),"r")
            lines=status.read()
            lines=lines.split("\n")
            for line in lines:
                line=line.split(":\t")
                if line[0]=="Name":
                    proc_name=line[1]
                if line[0]=="PPid" and line[1]==PROC:
                    process=Processo(proc_name,element)

def find_nipoti(processo):
    for element in os.listdir("/proc"):
        if element.isdigit():
            status=open("/proc/{}/status".format(element),"r")
            lines=status.read()
            lines=lines.split("\n")
            for line in lines:
                line=line.split(":\t")
                if line[0]=="Name":
                    proc_name=line[1]
                if line[0]=="PPid" and line[1]==processo.pid:
                    print("\n|___{} (PID: {})\n   |_____{}".format(processo.nome,processo.pid,proc_name))
                    
def init():
<<<<<<< HEAD
    find_sons()
    print(PROC.nome)
    for element in Processo.sons_list:
        find_nipoti(element)
=======
    find_sons(PROC)
    print(PROC)
    for processo in sons_list:
        find_nipoti(processo)
>>>>>>> b6206d3c1830aaf88949b30e361e06ee2582e517
            

if __name__=="__main__":
    parameter()