import "Logic.sc"
       "ctime"

int main()
    printf("Start Rendering...\n")

    start := clock()
    engine: Engine
    engine.renderImage(8)
    end := clock()

    printf("Total Time: %.0lf ms\n", double(end - start) / 1000.0)
