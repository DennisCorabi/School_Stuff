import argoscuolanext

session=argoscuolanext.Session("sg26426","corabidennis","votiprima")
lista=session.oggi()
print(lista)
print(lista.nuoviElementi)
