#!/usr/bin/python3

import os




def main():
    i=0
    for i in range(0,33,1):
        subnet=pow(2,32)-pow(2,i)               #trovo la subnet mask
        rete=pow(2,i)                           #trovo la grandezza della rete
        print("prefisso: /"+str(32-i)+"\tmaschera di sottorete:"+str(subnet)+"\t grandezza della rete:",pow(2,i))   #printo il mondo.

if __name__=="__main__":
    main()
