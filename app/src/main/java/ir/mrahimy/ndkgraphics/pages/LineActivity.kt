package ir.mrahimy.ndkgraphics.pages

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Point
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class LineActivity : AppCompatActivity() {

    companion object {
        init {
            System.loadLibrary("bitmap-line")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val display = windowManager.defaultDisplay
        val displaySize = Point()
        display.getSize(displaySize)

        setContentView(
            BasicBitmapView(
                this@LineActivity,
                displaySize.x,
                displaySize.y,
                displaySize.x/4,
                displaySize.y/2,
                displaySize.x/4,
                200,
            )
        )
    }

    class BasicBitmapView(
        context: Context,
        width: Int,
        height: Int,
        private val x1: Int,
        private val y1: Int,
        private val x2: Int,
        private val y2: Int
    ) : View(context) {
        private var mBitmap: Bitmap? = null

        private external fun ndkRenderLine(
            bitmap: Bitmap?,
            x1: Int,
            y1: Int,
            x2: Int,
            y2: Int
        )

        init {
            mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
        }

        override fun onDraw(canvas: Canvas) {
            mBitmap?.eraseColor(Color.BLACK)
            ndkRenderLine(
                mBitmap,
                x1, y1,
                x2, y2
            )
            canvas.drawBitmap(mBitmap!!, 0f, 0f, null)
        }
    }
}