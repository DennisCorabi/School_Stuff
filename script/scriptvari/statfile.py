import os
import datetime as dt
from os.path import commonprefix
import sys

def parametri():
    if len(sys.argv)==2:
        return sys.argv[1]
    else:
        sys.exit()
def main():
    file=parametri()
    par=os.stat(file)
    par=list(par)
    print(par)

if __name__=="__main__":
    main()
