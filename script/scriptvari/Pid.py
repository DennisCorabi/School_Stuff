import os

def stampa_pid(proc):
    for element in proc:
        f=open("/proc/"+element+"/status","r")
        lista=f.read()
        pid=list()
        lista=lista.split("\n")
        for riga in lista:
            if "Pid:" in riga:
                pid.append(riga.split(":\t"))
        for element in pid:
            if element[0]=="Pid":
                print("Pid del processo: ",element[1])
def main():
    lista=os.listdir("/proc")
    proc=list()
    for element in lista:
        if element.isdigit():
            proc.append(element)
    stampa_pid(proc)

if __name__=="__main__":
    main()