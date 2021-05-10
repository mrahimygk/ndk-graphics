package ir.mrahimy.ndkgraphics

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import ir.mrahimy.ndkgraphics.pages.SineActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.sine).setOnClickListener {
            startActivity(Intent(this@MainActivity, SineActivity::class.java))
        }
    }
}