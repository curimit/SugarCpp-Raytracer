import "Point3D.sc"
       "Const.sc"

Point3D shoot(ray: const Ray&, k: const double&) = ray.p0 + ray.v * k

Point3D mirrorVec(p: const Point3D&, plane: const Plane&) = p + n * t where
    n := unit(plane.n)
    t := -2 * p`dot`n

Point3D mirror(p: const Point3D&, plane: const Plane&) = p0 + p1 where
    p0 := plane.p0
    p1 := (p - p0) `mirrorVec` plane

Ray mirror(ray: const Ray&, plane: const Plane&) = Ray(p0, v) where
    p0 := ray.p0 `mirror` plane
    v  := ray.v  `mirrorVec` plane

double cross(ray: const Ray&, ball: const Ball&)
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

tuple<int, double> crossGroup(ray: const Ray&, balls: const vector<Ball>&)
    ans := 1e20
    id := -1
    for i <- 0 to balls.size() - 1
        ans = ans `min` (ray `cross` balls[i])
    return (id, ans)

tuple<bool, Ray> reflex(ray: const Ray&, ball: const Ball&)
    t := ray `cross` ball
    if t < 0 then return (false, Ray())
    p := ray `shoot` t
    plane := Plane(p, p - ball.p0)
    return (true, ray `mirror` plane)

tuple<bool, Ray> reflex(ray: const Ray&, balls: const vector<Ball>&)
    id: int
    t: double
    (id, t) = ray `crossGroup` balls
    if id == -1 then return (false, Ray())
    p := ray `shoot` t
    plane := Plane(p, p - balls[id].p0)
    return (true, ray `mirror` plane)