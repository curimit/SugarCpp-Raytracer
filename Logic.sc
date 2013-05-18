import "Common.sc"
       "Image.sc"
       "Color.sc"

Color trace(p: Point3D)
    ray := Ray(eye, p - eye)
    ball := Ball(Point3D(10, 10, 100), 90)

    k := hit(ray, ball) `max` 0.1

    return Color(k, k, k)

void renderImage()
    image: Image
    factor := picWidth `min` picHeight    
    for i <- 0 to picHeight - 1, j <- 0 to picWidth - 1
        x := double(i - picHeight / 2) / double(factor)
        y := double(j - picWidth / 2) / double(factor)
        image.push_back(trace(Point3D(x, y, 0.1)))
    outputImage("main.ppg", image, picWidth, picHeight)

