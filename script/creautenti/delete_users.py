#!/etc/bin/python3
import os
f=open("utenti.txt")
s=f.read()
s=s.split("\n")
for element in s:
	if len(element)>0:
		element=element.split(" ")
		os.system("sudo userdel "+element[0])
		print("l'utente "+element[0]+" e' stato eliminato.")

