import "Common.sc"

case class Color(r: double, g: double, b: double)

type Image = vector<Color>

case class Point3D(x: double, y: double, z: double)

case class Line(p0: Point3D, v: Point3D)

case class Ray(p0: Point3D, v: Point3D)

case class Object
case class Ball(p0: Point3D, r: double) : Object
case class Plane(p0: Point3D, n: Point3D) : Object

case class Light(p0: Point3D, c: Color)

case class Info(lights: vector<Light>)
case class World(info: Info, list: vector<Object>)