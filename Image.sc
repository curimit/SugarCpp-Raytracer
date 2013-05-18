import "Common.sc"
       "Type.sc"
       "Const.sc"

void outputImage(file_name: string, image: Image, width: int, height: int)
    fout := fopen(file_name.c_str(), "w")
    defer fclose(fout)

    fprintf(fout, "P3\n")
    fprintf(fout, "%d %d\n", width, height)
    fprintf(fout, "255\n")

    for c <- image
        r := int(c.r * 255.49)
        g := int(c.g * 255.49)
        b := int(c.b * 255.49)
        fprintf(fout, "%d %d %d\n", r, g, b)
