# creautenti.py

Questo script crea una serie di utenti dati i loro nomi e le loro password, informazioni che vengono prelevate in automatico da un file di testo.
questo file senza estensione può essere modificato, aggiungendo così utenti  da creare, può essere modificato seguendo una serie di regole (LEGGI SOTTO).

# utenti.txt

questo il file di testo da cui CREAUTENTI.PY preleva gli username degli utenti che si vuole creare e le rispettive password.
questo file di testo è liberamente modificabile (ovvero possono essere aggiunti altri utenti da creare),  purchè si rispetti un detemrminato formato:

  FORMATO DEL TESTO DA INSERIRE: <Nome> <Password> 
  Dopo il nome e dopo la password DEVE essere inserito uno spazio.
  
# delete_users.py

Questo programma permette di elimare gli utenti appena creati grazie all'utilizzo di CREAUTENTI.PY.
Si può considerare come il programma descritto sopra, ma inverso: prelevando nomi e password di utenti da UTENTI.TXT, elimina dal tuo sistema operativo questi utenti.

# creatutto.py

Programma in Python che ti permette di creare una serie di file e directory prelevando il loro percorso assoluto (compreso il loro nome) dal file TUTTO.
il funzionamento del programma e' semplice: per ogni riga del file TUTTO, se il primo carattere e' una D, allora crea una directory con il percorso descritto subito dopo.
Se e' una F invece, crea un file prendendo come percorso il resto della riga.


