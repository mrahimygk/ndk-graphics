package ir.mrahimy.ndkgraphics.pages

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Point
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RelativeLayout
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

        setContentView(
            RelativeLayout(this).apply {
                val bg = BasicBitmapView(this@RandomActivity, displaySize.x, displaySize.y)
                addView(bg)
                addView(
                    Button(this@RandomActivity).apply {
                        text = "Refresh"
                        setOnClickListener {
                            bg.invalidate()
                        }
                    },
                    RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
                    ).apply {
                        addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE)
                        addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE)
                    }
                )
            }
        )
    }

    class BasicBitmapView(
        context: Context,
        width: Int,
        height: Int
    ) : View(context) {
        private var mBitmap: Bitmap? = null

        private external fun ndkRenderRandom(bitmap: Bitmap?)

        init {
            mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
        }

        override fun onDraw(canvas: Canvas) {
            mBitmap?.eraseColor(Color.BLACK)
            ndkRenderRandom(mBitmap)
            canvas.drawBitmap(mBitmap!!, 0f, 0f, null)
        }
    }
}