#include <jni.h>
#include <string>
#include <android/bitmap.h>

void set_pixel(AndroidBitmapInfo *ptr, uint8_t *pixels, int x, int y, unsigned int val);

void set_pixel(AndroidBitmapInfo *ptr, uint8_t *pixels, int x, int y, unsigned int val) {
    pixels[y * ptr->stride + x * 4 + 0] = (val >> 16) & 0xff;
    pixels[y * ptr->stride + x * 4 + 1] = (val >> 8) & 0xff;
    pixels[y * ptr->stride + x * 4 + 2] = (val >> 0) & 0xff;
    pixels[y * ptr->stride + x * 4 + 3] = (val >> 24) & 0xff;
}