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

Point3D (+) (p1: Point3D, p2: Point3D) = Point3D(x, y, z) where
    x := p1.x + p2.x
    y := p1.y + p2.y
    z := p1.z + p2.z

Point3D (-) (p1: Point3D, p2: Point3D) = Point3D(x, y, z) where
    x := p1.x - p2.x
    y := p1.y - p2.y
    z := p1.z - p2.z

Point3D (*)<T>(p1: Point3D, t: T) = Point3D(x, y, z) where
    x := p1.x * t
    y := p1.y * t
    z := p1.z * t

Point3D (/)<T>(p1: Point3D, t: T) = Point3D(x, y, z) where
    x := p1.x / t
    y := p1.y / t
    z := p1.z / t

eye := Point3D(0, 0, 0)

double dot(p1: Point3D, p2: Point3D) = p1.x * p2.x + p1.y * p2. y + p1.z * p2.z

double len(p: Point3D) = sqrt(sqr(p.x) + sqr(p.y) + sqr(p.z))

Point3D unit(p: Point3D) = Point3D(p.x/k, p.y/k, p.z/k) where
    k := len(p)

Point3D shoot(ray: Ray, k: double) = ray.p0 + ray.v * k

double angle(v1: Point3D, v2: Point3D) = v1 `dot` v2 / len(v1) / len(v2)

Point3D mirrorVec(p: Point3D, plane: Plane) = p + n * t where
    n := unit(plane.n)
    t := -2 * p`dot`n

Point3D mirror(p: Point3D, plane: Plane) = p0 + p1 where
    p0 := plane.p0
    p1 := (p - p0) `mirrorVec` plane

Ray mirror(ray: Ray, plane: Plane) = Ray(p0, v) where
    p0 := ray.p0 `mirror` plane
    v  := ray.v  `mirrorVec` plane

double cross(ray: Ray, ball: Ball)
    a := ray.v `dot` ray.v
    b := 2 * (ray.p0 - ball.p0) `dot` ray.v
    c := (ray.p0 - ball.p0) `dot` (ray.p0 - ball.p0) - sqr(ball.r)
    x1, x2: double
    flag: bool
    (flag, x1, x2) = roots(a, b, c)
    return match
        | !flag  => -1
        | x1 <= 0 and x2 <= 0  => -1
        | x1 > 0  and x2 <= 0  => x1
        | x2 > 0  and x1 <= 0  => x2
        | x1 < x2 => x1
        | x1 > x2 => x2

double hit(ray: Ray, ball: Ball)
    t := ray `cross` ball
    if t < 0 then return 0
    p := ray `shoot` t
    plane := Plane(p, p - ball.p0)
    ray_new := ray `mirror` plane
    //printf("(%.2lf %.2lf %.2lf) -> (%.2lf %.2lf %.2lf)\n", ray_new.p0.x, ray_new.p0.y, ray_new.p0.z, ray_new.v.x, ray_new.v.y, ray_new.v.z)
    return ray_new.v `angle` (eye - ray_new.p0)
    