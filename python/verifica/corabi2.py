import os
import sys
def parametri():
    if len(sys.argv)>=3:
        return sys.argv[1],sys.argv[2]
    else:
        print("inserisci due parametri, uno il percorso e l'altro la stringa da cercare.")
        sys.exit()

def main():
    path,stringa=parametri()
    path="/home/dennis/projects/verifica/"+path+"/"         #non ho capito il funzionamento ma l'ho adattato a dove si trova il file compresso nel mio computer
    lista=os.listdir(path)
    for element in lista:
        cont=0
        if os.path.isfile(path+element):
            f=open(path+element)
            lista2=f.read()
            lista2=lista2.split("\n")
            for riga in lista2:
                if len(riga)>0:
                    riga=riga.split(" ")
                    for parola in riga:
                        parola=parola.lower()
                        if parola==stringa:
                            cont=cont+1
            if cont>0:
                print("la stringa "+stringa+" e' stata trovata ",cont," volte nel file "+path+element)
            else:
                print("la stringa non e' stata trovata nel file "+path+element)
                
if __name__=="__main__":
    main()