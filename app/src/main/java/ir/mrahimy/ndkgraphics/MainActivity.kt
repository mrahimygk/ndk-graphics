package ir.mrahimy.ndkgraphics

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import ir.mrahimy.ndkgraphics.bitmapfill.BasicBitmapActivity
import ir.mrahimy.ndkgraphics.pages.Page115Activity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.basicBitmap).setOnClickListener {
            startActivity(Intent(this@MainActivity, BasicBitmapActivity::class.java))
        }

        findViewById<Button>(R.id.p115).setOnClickListener {
            startActivity(Intent(this@MainActivity, Page115Activity::class.java))
        }
    }
}