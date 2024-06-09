package capstone.prject.express.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import capstone.prject.express.R

class FaceActivity : AppCompatActivity() {

    private lateinit var buttonstartface : Button
    private lateinit var buttonhandstart : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_face)

        buttonstartface = findViewById(R.id.button_startface)
        buttonhandstart = findViewById(R.id.button_starthand)

        buttonstartface.setOnClickListener {
            val intent = Intent(this,FaceDetailActivity::class.java)
            startActivity(intent)
            finish()
        }

        buttonhandstart.setOnClickListener {
            val intent = Intent(this,HandActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}