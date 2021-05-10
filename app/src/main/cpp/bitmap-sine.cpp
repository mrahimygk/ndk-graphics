#include <jni.h>
#include <string>
#include <android/bitmap.h>
#include "bitmap-basic.cpp"

void fill_bitmap(AndroidBitmapInfo *ptr, uint8_t *pixels) {
    float pi = 3.1415;
    for (float x = 0; x < pi; x += 0.01) {
        float y = sin(2 * pi * x);
        float c = ptr->width / 6 * x;
        float l = 80 * y + ptr->height / 3;
        set_pixel(ptr, pixels, c, l, 0xf5555555);
    }
}

extern "C"
JNIEXPORT void JNICALL
Java_ir_mrahimy_ndkgraphics_pages_SineActivity_00024BasicBitmapView_ndkRenderSine(
        JNIEnv *env,
        jobject thiz,
        jobject bitmap) {

    AndroidBitmapInfo info = {0};
    uint8_t *pixelBuf;

    AndroidBitmap_getInfo(env, bitmap, &info);

    AndroidBitmap_lockPixels(env, bitmap, (void **) &pixelBuf);

    fill_bitmap(&info, pixelBuf);

    AndroidBitmap_unlockPixels(env, bitmap);
}