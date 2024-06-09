package capstone.prject.express.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import capstone.prject.express.R

class MainActivity : AppCompatActivity() {

    private lateinit var buttonface :Button
    private lateinit var buttonhand : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        buttonface = findViewById(R.id.Btn_face)
        buttonhand = findViewById(R.id.Btn_hands)

        buttonface.setOnClickListener {
            val intent = Intent(this,FaceActivity::class.java)
            startActivity(intent)

        }

        buttonhand.setOnClickListener {
            val intent = Intent(this,HandActivity::class.java)
            startActivity(intent)
        }

    }
}