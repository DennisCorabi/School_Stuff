import os

sons_list=list()
PROC=1

def find_sons(proc):
    for processo in os.listdir("/proc"):
        if processo.isdigit():
            status=open("/proc/{}/status".format(processo),"r")
            lines=status.read()
            lines=lines.split("\n")
            for line in lines:
                line=line.split(":\t")
                if line[0]=="PPid" and line[1]==str(proc):
                    sons_list.append(processo)

def find_nipoti(processo):
    cont=0
    print("\n\t{} --- Nipoti del processo {}:\n".format(processo,PROC))
    for processo in os.listdir("/proc"):
        if processo.isdigit():
            status=open("/proc/{}/status".format(processo),"r")
            lines=status.read()
            lines=lines.split("\n")
            for line in lines:
                line=line.split(":\t")
                if line[0]=="PPid" and line[1]==processo:
                    print("\t\t",processo)
                    cont=cont+1
    if cont==0:
        print("\t\t")

                    

                    
def init():
    find_sons(PROC)
    print(PROC)
    for processo in sons_list:
        find_nipoti(processo)
            

if __name__=="__main__":
    init()