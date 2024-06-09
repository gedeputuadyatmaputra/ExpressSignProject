package capstone.prject.express.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import capstone.prject.express.R

class HandActivity : AppCompatActivity() {

    private lateinit var buttonface : Button
    private lateinit var buttonstart : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_hand)

        buttonstart = findViewById(R.id.button_hands)
        buttonface = findViewById(R.id.button_faces)

        buttonstart.setOnClickListener {
            val intent = Intent(this,HandDetailActivity::class.java)
            startActivity(intent)
            finish()
        }

        buttonface.setOnClickListener {
            val intent = Intent(this,FaceActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}