package ir.mrahimy.ndkgraphics.pages

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Point
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class SineActivity : AppCompatActivity() {

    companion object {
        init {
            System.loadLibrary("bitmap-sine")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val display = windowManager.defaultDisplay
        val displaySize = Point()
        display.getSize(displaySize)

        setContentView(BasicBitmapView(this, displaySize.x, displaySize.y))
    }

    class BasicBitmapView(
        context: Context,
        width: Int,
        height: Int
    ) : View(context) {
        private var mBitmap: Bitmap? = null

        private external fun ndkRenderSine(bitmap: Bitmap?)

        init {
            mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
        }

        override fun onDraw(canvas: Canvas) {
            ndkRenderSine(mBitmap)
            canvas.drawBitmap(mBitmap!!, 0f, 0f, null)
        }
    }
}