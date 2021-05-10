#include <jni.h>
#include <string>
#include <android/bitmap.h>
#include <jni.h>

void fill_bitmap(AndroidBitmapInfo *ptr, uint8_t *pixels);

void set_pixel(AndroidBitmapInfo *ptr, uint8_t *pixels, int x, int y, unsigned int val);

void fill_bitmap(AndroidBitmapInfo *ptr, uint8_t *pixels) {
    float pi = 3.1415;
    for (float x = 0; x < pi; x += 0.01) {
        float y = sin(2 * pi * x);
        float c = ptr->width / 6 * x;
        float l = 80 * y + ptr->height / 3;
        set_pixel(ptr, pixels, c, l, 0xf5555555);
    }
}

void set_pixel(AndroidBitmapInfo *ptr, uint8_t *pixels, int x, int y, unsigned int val) {
    pixels[y * ptr->stride + x * 4 + 0] = (val >> 16) & 0xff;
    pixels[y * ptr->stride + x * 4 + 1] = (val >> 8) & 0xff;
    pixels[y * ptr->stride + x * 4 + 2] = (val >> 0) & 0xff;
    pixels[y * ptr->stride + x * 4 + 3] = (val >> 24) & 0xff;
}

extern "C"
JNIEXPORT void JNICALL
Java_ir_mrahimy_ndkgraphics_pages_SineActivity_00024BasicBitmapView_ndkRenderSine(
        JNIEnv *env,
        jobject thiz,
        jobject bitmap) {

    AndroidBitmapInfo info = {0};
    uint8_t *pixelBuf;
    uint8_t a, r, g, b;

    AndroidBitmap_getInfo(env, bitmap, &info);

    AndroidBitmap_lockPixels(env, bitmap, (void **) &pixelBuf);

    fill_bitmap(&info, pixelBuf);

    AndroidBitmap_unlockPixels(env, bitmap);
}