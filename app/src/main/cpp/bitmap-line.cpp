#include <jni.h>
#include <string>
#include <android/bitmap.h>
#include "bitmap-basic.cpp"
#include <jni.h>

extern "C"
JNIEXPORT void JNICALL
Java_ir_mrahimy_ndkgraphics_pages_LineActivity_00024BasicBitmapView_ndkRenderLine(
        JNIEnv *env,
        jobject thiz,
        jobject bitmap,
        jint x1, jint y1,
        jint x2, jint y2
) {

    AndroidBitmapInfo info = {0};
    uint8_t *pixelBuf;

    AndroidBitmap_getInfo(env, bitmap, &info);

    AndroidBitmap_lockPixels(env, bitmap, (void **) &pixelBuf);

    draw_line(&info, pixelBuf, x1, y1, x2, y2, 0xffffffff);

    AndroidBitmap_unlockPixels(env, bitmap);
}

extern "C"
JNIEXPORT void JNICALL
Java_ir_mrahimy_ndkgraphics_pages_LineActivity_00024BasicBitmapView_ndkRenderRandomLines(
        JNIEnv *env, jobject thiz, jobject bitmap) {


    AndroidBitmapInfo info = {0};
    uint8_t *pixelBuf;

    AndroidBitmap_getInfo(env, bitmap, &info);

    AndroidBitmap_lockPixels(env, bitmap, (void **) &pixelBuf);

    int xx1 = info.width * ((double) rand() / (RAND_MAX));
    int yy1 = info.height * ((double) rand() / (RAND_MAX));

    for (int i = 0; i < 777; i++) {

        int xx2 = info.width * ((double) rand() / (RAND_MAX));
        int yy2 = info.height * ((double) rand() / (RAND_MAX));
        draw_line(&info, pixelBuf, xx1, yy1, xx2, yy2, 0xffffffff);
        xx1 =xx2;
        yy1=yy2;
    }

    AndroidBitmap_unlockPixels(env, bitmap);

}