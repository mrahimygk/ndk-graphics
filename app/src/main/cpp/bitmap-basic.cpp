#include <jni.h>
#include <string>
#include <android/bitmap.h>

void set_pixel(AndroidBitmapInfo *ptr, uint8_t *pixels, int x, int y, unsigned int val) {
    pixels[y * ptr->stride + x * 4 + 0] = (val >> 16) & 0xff;
    pixels[y * ptr->stride + x * 4 + 1] = (val >> 8) & 0xff;
    pixels[y * ptr->stride + x * 4 + 2] = (val >> 0) & 0xff;
    pixels[y * ptr->stride + x * 4 + 3] = (val >> 24) & 0xff;
}

void draw_line(
        AndroidBitmapInfo *ptr, uint8_t *pixels,
        float x1, float y1,
        float x2, float y2,
        unsigned int color
) {

    const bool steep = (fabs(y2 - y1) > fabs(x2 - x1));
    if (steep) {
        std::swap(x1, y1);
        std::swap(x2, y2);
    }

    if (x1 > x2) {
        std::swap(x1, x2);
        std::swap(y1, y2);
    }

    const float dx = x2 - x1;
    const float dy = fabs(y2 - y1);

    float error = dx / 2.0f;
    const int ystep = (y1 < y2) ? 1 : -1;
    int y = (int) y1;

    const int maxX = (int) x2;

    for (int x = (int) x1; x <= maxX; x++) {
        if (steep) {
            set_pixel(ptr, pixels, y, x, color);
        } else {
            set_pixel(ptr, pixels, x, y, color);
        }

        error -= dy;
        if (error < 0) {
            y += ystep;
            error += dx;
        }
    }
}

void draw_rectangle(
        AndroidBitmapInfo *ptr, uint8_t *pixels,
        float x1, float y1,
        float x2, float y2,
        unsigned int color,
        bool fill
) {

    draw_line(ptr, pixels, x1, y1, x2, y1, color);
    draw_line(ptr, pixels, x1, y1, x1, y2, color);
    draw_line(ptr, pixels, x2, y1, x2, y2, color);
    draw_line(ptr, pixels, x1, y2, x2, y2, color);

    if (fill) {
        for (int i = x1; i < x2; ++i) {
            for (int j = y1; j < y2; ++j) {
                set_pixel(ptr, pixels, i, j, color);
            }
        }
    }

}

void step_line(
        AndroidBitmapInfo *ptr, uint8_t *pixels,
        float x1, float y1,
        float xStep, float yStep,
        unsigned int color,
        bool fill
) {
    draw_line(ptr, pixels, x1, y1, x1 + xStep, y1 + yStep, color);
}