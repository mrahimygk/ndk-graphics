package ir.mrahimy.ndkgraphics.pages

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Point
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity

class SineActivity : AppCompatActivity() {

    companion object {
        init {
            System.loadLibrary("bitmap-sine")
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
                val bg = BasicBitmapView(this@SineActivity, displaySize.x, displaySize.y)
                addView(bg)

                val upButton = Button(this@SineActivity).apply {
                    text = "\u2191"
                    alpha = 0.75f
                    setOnClickListener {
                        bg.run {
                            heightPositionRatio -= 0.01
                            invalidate()
                        }
                    }
                }

                val downButton = Button(this@SineActivity).apply {
                    text = "\u2193"
                    alpha = 0.75f
                    setOnClickListener {
                        bg.run {
                            heightPositionRatio += 0.01
                            invalidate()
                        }
                    }
                }

                addView(downButton,
                    RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
                    ).apply {
                        addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE)
                        addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE)
                    })

                addView(upButton,
                    RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
                    ).apply {
                        addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE)
                        addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE)
                    })


                val leftButton = Button(this@SineActivity).apply {
                    text = "\u2190"
                    alpha = 0.75f

                    setOnClickListener {
                        bg.run {
                            widthFrequency += 0.1
                            invalidate()
                        }
                    }

                }

                val rightButton = Button(this@SineActivity).apply {
                    text = "\u2192"
                    alpha = 0.75f
                    setOnClickListener {
                        bg.run {
                            widthFrequency -= 0.1
                            invalidate()
                        }
                    }
                }

                addView(rightButton,
                    RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
                    ).apply {
                        addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE)
                        addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE)
                    })

                addView(leftButton,
                    RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
                    ).apply {
                        addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE)
                        addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE)
                    })
            }
        )
    }

    class BasicBitmapView(
        context: Context,
        width: Int,
        height: Int
    ) : View(context) {
        private var mBitmap: Bitmap? = null

        var heightPositionRatio: Double = 1.0 / 3.0
        var widthFrequency: Double = 6.0

        private external fun ndkRenderSine(
            bitmap: Bitmap?,
            heightPositionRatio: Double,
            widthFrequency: Double
        )

        init {
            mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
        }

        override fun onDraw(canvas: Canvas) {
            mBitmap?.eraseColor(Color.BLACK)
            ndkRenderSine(mBitmap, heightPositionRatio, widthFrequency)
            canvas.drawBitmap(mBitmap!!, 0f, 0f, null)
        }
    }
}