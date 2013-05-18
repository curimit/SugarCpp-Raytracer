main.ppg: main
	./main
	rm *.h *.cpp *.o

main: main.cpp Const.cpp Point3D.cpp Ray.cpp Type.cpp Image.cpp Common.cpp Logic.cpp
	clang++ -std=c++11 -O2 *.cpp -c
	clang++ -std=c++11 -O2 *.o -o main

main.cpp: main.sc
	SugarCpp main.sc

Const.cpp: Const.sc
	SugarCpp Const.sc

Point3D.cpp: Point3D.sc
	SugarCpp Point3D.sc

Ray.cpp: Ray.sc
	SugarCpp Ray.sc

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