package ir.mrahimy.ndkgraphics.pages

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Point
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class RandomActivity : AppCompatActivity() {

    companion object {
        init {
            System.loadLibrary("bitmap-random")
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val display = windowManager.defaultDisplay
        val displaySize = Point()
        display.getSize(displaySize)

        setContentView(BasicBitmapView(this@RandomActivity, displaySize.x, displaySize.y))
    }

    class BasicBitmapView(
        context: Context,
        width: Int,
        height: Int
    ) : View(context) {
        private var mBitmap: Bitmap? = null

        var heightPositionRatio: Double = 1.0 / 3.0
        var widthFrequency: Double = 6.0

        private external fun ndkRenderRandom(
            bitmap: Bitmap?,
            heightPositionRatio: Double,
            widthFrequency: Double
        )

        init {
            mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
        }

        override fun onDraw(canvas: Canvas) {
            mBitmap?.eraseColor(Color.BLACK)
            ndkRenderRandom(mBitmap, heightPositionRatio, widthFrequency)
            canvas.drawBitmap(mBitmap!!, 0f, 0f, null)
        }
    }
}