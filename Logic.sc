import "Common.sc"
       "Image.sc"
       "Point3D.sc"
       "Ray.sc"
       "Type.sc"
       "Const.sc"

Color trace(p: Point3D)
    ray := Ray(eye, p - eye)
    ball := Ball(Point3D(10, 10, 100), 90)

    ray_new: Ray
    flag: bool
    (flag, ray_new) = ray `reflex` ball
    k := ray_new.v `angle` (eye - ray_new.p0)
    k = pow(k, 1.8) * 0.9 + 0.1
    return flag ? Color(k, k, k) : cBackground

[public]
class Engine
    image: Image
    length: int
    factor: double

    Engine()
        @length = picWidth * picHeight
        @image = Image(@length)
        @factor = picWidth `min` picHeight

    void renderThread(core_id: int, core_count: int)
        for t <- 0 to @length / core_count, t => t * core_count + core_id, t < @length
            i := t / picWidth
            j := t % picWidth
            x := double(j - picWidth / 2) / factor
            y := double(i - picHeight / 2) / factor
            @image[t] = trace(Point3D(x, y, 0.1))

    void renderImage(core_count: int)
        renderThread(1, 1)
        outputImage("main.ppg", image, picWidth, picHeight)

