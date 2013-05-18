import "Type.sc"

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

double dot(p1: Point3D, p2: Point3D) = p1.x * p2.x + p1.y * p2. y + p1.z * p2.z

double len(p: Point3D) = sqrt(sqr(p.x) + sqr(p.y) + sqr(p.z))

double dist(p1: Point3D, p2: Point3D) = len(p1 - p2)

Point3D unit(p: Point3D) = Point3D(p.x/k, p.y/k, p.z/k) where
    k := len(p)

double angle(v1: Point3D, v2: Point3D) = v1 `dot` v2 / len(v1) / len(v2)
