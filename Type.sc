import "Common.sc"

class Color(r: double, g: double, b: double)

typedef Image = vector<Color>

class Point3D(x: double, y: double, z: double)

class Line(p0: Point3D, v: Point3D)

class Ray(p0: Point3D, v: Point3D)

class Object
class Ball(p0: Point3D, r: double) : Object
class Plane(p0: Point3D, n: Point3D) : Object

class Light(p0: Point3D, c: Color)

class Info(lights: vector<Light>)
class World(info: Info, list: vector<Object>)