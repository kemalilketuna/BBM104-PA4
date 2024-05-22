import os 

def zip():
    os.system("rm -rf src")
    os.system("mkdir src")
    os.system("cp *.java src/")
    os.system("zip -r -0 b2220356127.zip src")
    os.system("rm -rf src")

if __name__ == "__main__":
    zip()