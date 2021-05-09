#include <jni.h>
#include <string>
#include <android/bitmap.h>

void fill_bitmap(AndroidBitmapInfo *ptr, uint8_t *pixels);

void set_pixel(AndroidBitmapInfo *ptr, uint8_t *pixels, int x, int y, unsigned int val);

void fill_bitmap(AndroidBitmapInfo *ptr, uint8_t *pixels) {
    for (int i = 50; i < 100; i++) {
        for (int j = 50; j < 100; j++) {
            set_pixel(ptr, pixels, i, j, 0xf0f0f0f0);
        }
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
Java_ir_mrahimy_ndkgraphics_bitmapfill_BasicBitmapActivity_00024BasicBitmapView_ndkRenderBasicBitmap(
        JNIEnv *env, jobject thiz, jobject bitmap) {
    AndroidBitmapInfo info = {0};
    uint8_t *pixelBuf;
    uint8_t a, r, g, b;

    AndroidBitmap_getInfo(env, bitmap, &info);

    AndroidBitmap_lockPixels(env, bitmap, (void **) &pixelBuf);

    fill_bitmap(&info, pixelBuf);

    AndroidBitmap_unlockPixels(env, bitmap);
}