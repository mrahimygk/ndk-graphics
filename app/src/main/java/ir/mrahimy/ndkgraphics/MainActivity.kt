package ir.mrahimy.ndkgraphics

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import ir.mrahimy.ndkgraphics.pages.LineActivity
import ir.mrahimy.ndkgraphics.pages.RandomActivity
import ir.mrahimy.ndkgraphics.pages.SineActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.sine).setOnClickListener {
            startActivity(Intent(this@MainActivity, SineActivity::class.java))
        }

        findViewById<Button>(R.id.random).setOnClickListener {
            startActivity(Intent(this@MainActivity, RandomActivity::class.java))
        }

        findViewById<Button>(R.id.line).setOnClickListener {
            startActivity(Intent(this@MainActivity, LineActivity::class.java))
        }

        findViewById<Button>(R.id.random_lines).setOnClickListener {
            startActivity(Intent(this@MainActivity, LineActivity::class.java).apply {
                putExtra(LineActivity.IS_RANDOM, true)
            })
        }
    }
}