import os
import sys

global lista
listadir=list()

def parametri():
    if len(sys.argv)<=4 or len(sys.argv)>5:
        print("Inserisci quattro parametri.")
        sys.exit()
    else:
        global PARAMETERS
        PARAMETERS=(sys.argv[1],sys.argv[2],sys.argv[3],sys.argv[4])

def find_dirs(dir):
    for element in os.listdir(dir):
        path=dir+"/"+element
        if os.path.isdir(path) and element[0]!=".":
            listadir.append(path)
            find_dirs(path)
            
def find_file(dirs):
    for path in dirs:
        for files in os.listdir(path):
            filepath=path+"/"+files
            if os.path.isfile(filepath) and files[0]!=".":
                file=files.split(".")
                if file[-1]=="txt" and PARAMETERS[1]=="txt":
                    print(files)
                    modify_text(path+"/"+files)
                elif file[-1]==PARAMETERS[1]:
                    print(files)

def modify_text(filepath):
    file=open(filepath,"r")
    if PARAMETERS[2] in file.readlines():
        print("\tPAROLA ({}) TROVATA IN {}".format(PARAMETERS[2],filepath))



def init():
    parametri()
    listadir.append(PARAMETERS[0])
    print(PARAMETERS[0])
    find_dirs(PARAMETERS[0])
    find_file(listadir)

if __name__=="__main__":
    init()