#!/usr/bin/python3
import os
nome="ciccio.txt"
path="/home/dennis/shared/scuola/TPS/script"
str="ciao mondo!sommone"
cont=False
lista=os.listdir(path)
lista.sort()
for file in lista:
	if (nome==file):
		cont=True
if (cont==False):
	print("non e' stato trovato nessun file con lo stesso nome nel percorso selezionato.")
	os.chdir(path)
	f=open(nome,"w")
	print("e' stato creato un nuovo file con lo stesso nome nelle percorso"+" "+path)
else:
	print("e' stato trovato il file scelto nel percorso"+" "+path)
	f=open(nome,"w")
scelta=int(input("vuoi scriverci qualcosa al suo interno? [Y=1/N=0]: "))
if (scelta==1):
	f.write("sommmone")
	f.close()

