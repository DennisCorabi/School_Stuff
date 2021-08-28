import os

sons_list=list()
PROC=1

def find_sons(proc):
    for element in os.listdir("/proc"):
        if element.isdigit():
            status=open("/proc/{}/status".format(element),"r")
            lines=status.read()
            lines=lines.split("\n")
            for line in lines:
                line=line.split(":\t")
                if line[0]=="PPid" and line[1]==str(proc):
                    sons_list.append(element)

def find_nipoti(processo):
    cont=0
    print("\n\t{} --- Nipoti del processo {}:\n".format(processo,PROC))
    for element in os.listdir("/proc"):
        if element.isdigit():
            status=open("/proc/{}/status".format(element),"r")
            lines=status.read()
            lines=lines.split("\n")
            for line in lines:
                line=line.split(":\t")
                if line[0]=="PPid" and line[1]==processo:
                    print("\t\t",element)
                    cont=cont+1
    if cont==0:
        print("\t\t")

                    

                    
def init():
    find_sons(PROC)
    print(PROC)
    for element in sons_list:
        find_nipoti(element)
            

if __name__=="__main__":
    init()