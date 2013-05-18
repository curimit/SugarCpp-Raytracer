import "Common.sc"
       "Image.sc"
       "Color.sc"

Color trace(p: Point3D)
    ray := Ray(eye, p - eye)
    ball := Ball(Point3D(10, 10, 100), 90)

    k := hit(ray, ball) `max` 0.1

    return Color(k, k, k)


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
        for ct <- 0 to @length / core_count
            t := ct * core_count + core_id
            if t >= @length then break
            i := t / picWidth
            j := t % picWidth
            x := double(j - picWidth / 2) / factor
            y := double(i - picHeight / 2) / factor
            @image[t] = trace(Point3D(x, y, 0.1))

    void renderImage(core_count: int)
        pool: vector<thread>
        for i <- 0 to core_count - 1
            pool.push_back(thread(&Engine::renderThread, this, i, core_count))
        for &x <- pool
            x.join()
        outputImage("main.ppg", image, picWidth, picHeight)

