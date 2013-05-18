main.ppg: main
	./main
	rm *.h *.cpp *.o

main: main.cpp Color.cpp Type.cpp Image.cpp Common.cpp Logic.cpp
	g++ -std=c++11 -pthread -O2 *.cpp -c
	g++ -std=c++11 -pthread -O2 *.o -o main

main.cpp: main.sc
	SugarCpp main.sc

Color.cpp: Color.sc
	SugarCpp Color.sc

Type.cpp: Type.sc
	SugarCpp Type.sc

Image.cpp: Image.sc
	SugarCpp Image.sc

Common.cpp: Common.sc
	SugarCpp Common.sc

Logic.cpp: Logic.sc
	SugarCpp Logic.sc

clean:
	rm *.h *.cpp *.o