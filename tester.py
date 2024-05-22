import os

def test():
    print("Testing...")
    os.system("javac MapAnalyzer.java && java MapAnalyzer BBM104_S24_PA4_Sample_IO_v1/i1.txt output.txt")
    os.system("diff output.txt BBM104_S24_PA4_Sample_IO_v1/o1.txt")
    os.system("javac MapAnalyzer.java && java MapAnalyzer BBM104_S24_PA4_Sample_IO_v1/i2.txt output.txt")
    os.system("diff output.txt BBM104_S24_PA4_Sample_IO_v1/o2.txt")
    os.system("javac MapAnalyzer.java && java MapAnalyzer BBM104_S24_PA4_Sample_IO_v1/i3.txt output.txt")
    os.system("diff output.txt BBM104_S24_PA4_Sample_IO_v1/o3.txt")
    print("Tests are done.")

if __name__ == "__main__":
    test()