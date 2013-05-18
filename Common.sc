import "string.h"
       "string"
       "iostream"
       "fstream"
       "stdlib.h"
       "stdio.h"
       "math.h"
       "vector"
       "tuple"
       "time.h"

using namespace std

[inline]
T sqr<T>(a: T) = a * a

tuple<bool, double, double> roots(a: double, b: double, c: double)
    delta := b*b-4*a*c
    if delta < 0 then return (false, 0, 0)
    x1 := 0.5 * (-b + sqrt(delta)) / a
    x2 := 0.5 * (-b - sqrt(delta)) / a
    return (true, x1, x2)