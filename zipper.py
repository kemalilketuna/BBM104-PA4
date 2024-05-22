import os 

def zip():
    os.system("cp *.java src/")
    os.system("zip -r -0 b2220356127.zip src")