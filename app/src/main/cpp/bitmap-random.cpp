#include <jni.h>
#include <string>
#include <android/bitmap.h>
#include "bitmap-basic.cpp"

void fill_bitmap(
        AndroidBitmapInfo *ptr,
        uint8_t *pixels,
        jdouble height_position_ratio,
        jdouble width_frequency
) {

    for (int i = 0; i < 3000; i++) {
        int c = ptr->width * ((double) rand() / (RAND_MAX));
        int l = ptr->height * ((double) rand() / (RAND_MAX));
        int z = 2 * ((double) rand() / (RAND_MAX));
        set_pixel(ptr, pixels, c, l, 0xf0f0f0f0);
    }
}

extern "C"
JNIEXPORT void JNICALL
Java_ir_mrahimy_ndkgraphics_pages_RandomActivity_00024BasicBitmapView_ndkRenderRandom(
        JNIEnv *env,
        jobject thiz,
        jobject bitmap, jdouble height_position_ratio, jdouble width_frequency) {

    AndroidBitmapInfo info = {0};
    uint8_t *pixelBuf;

    AndroidBitmap_getInfo(env, bitmap, &info);

    AndroidBitmap_lockPixels(env, bitmap, (void **) &pixelBuf);

    fill_bitmap(&info, pixelBuf, height_position_ratio, width_frequency);

    AndroidBitmap_unlockPixels(env, bitmap);
}