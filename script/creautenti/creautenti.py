#!/usr/bin/python3
import os
def del_users(utenti):					#Function that deletes all the users that have just been created by the main function.
	cmd="sudo userdel"					#template of the command
	for element in utenti:
		os.system(cmd+" "+element[0])
	print("gli utenti sono stati eliminati.")		#advice that the users have been deleted

def main():
	f=open("utenti.txt")						#opening the file
	s=f.read()
	cmd="sudo useradd"
	lista=list()						#template of the command
	s=s.split("\n")							#creating an array of names and passwords
	for element in s:
		if element=='':
			s.remove(element)
		else:
			element=element.split(" ")
			lista.append(element)
	
	for element in lista:
		os.system(cmd+" "+element[0]+" -p "+element[1])
		print("L\' utente ",element[0], "e\' stato creato.")

	scelta=int(input("vuoi eliminare gli utenti appena creati? [SI=1,NO=0]: "))		#want to already delete the users?
	while scelta!=1 and scelta!=0:
		scelta=int(input("vuoi eliminare gli utenti appena creati? [SI=1,NO=0]: "))

	if scelta==1:
		del_users(lista)			#call to "del_users" function
	return


if __name__=="__main__":
	main()
