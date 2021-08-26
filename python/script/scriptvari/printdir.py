import os
import sys


def parametri():
    if len(sys.argv)==2:
        return sys.argv[1]
    else:
        return "."

def percorso(path):
    lista=os.listdir(path)
    dirs=list()
    backup=os.getcwd()
    print("CWD prima : ",os.getcwd())
    os.chdir(path)
    print("CWD dopo : ",os.getcwd())
    for element in lista:
        print(element)
        if os.path.isdir(element):
            dirs.append(element)
    for element in dirs:
        print("\n\n\n\n")
        print(element)
        percorso(element)

def main():
    path=parametri()
    percorso(path)

if __name__=="__main__":
    main()