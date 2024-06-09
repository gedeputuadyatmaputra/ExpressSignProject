package capstone.prject.express.ui

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import capstone.prject.express.R
import capstone.prject.express.classifier.hand.HandAssets
import capstone.prject.express.classifier.hand.HandClassifier

class HandDetailActivity : AppCompatActivity() {

    private lateinit var buttongallery: Button
    private lateinit var buttonanalize: Button
    private lateinit var buttoncamera: Button
    private lateinit var imageView: ImageView
    private lateinit var textView: TextView
    private lateinit var bitmap: Bitmap
    private lateinit var labels: List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_hand_detail)

        buttongallery = findViewById(R.id.btn_gallery_hand)
        buttonanalize = findViewById(R.id.btn_scanner_hand)
        buttoncamera = findViewById(R.id.btn_camera_hand)
        imageView = findViewById(R.id.image_hand)
        textView = findViewById(R.id.tv_result_hand)

        // Load labels using ModelLoader
        labels = HandAssets.loadLabels(this)

        buttongallery.setOnClickListener {
            val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(galleryIntent, 100)
        }

        buttonanalize.setOnClickListener {
            if (::bitmap.isInitialized) {
                analyzeImage()
            } else {
                Toast.makeText(this, "Please select an image first", Toast.LENGTH_SHORT).show()
            }
        }

        buttoncamera.setOnClickListener {
            openCamera()
        }
    }

    private fun openCamera() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE)
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA),
                CAMERA_PERMISSION_REQUEST_CODE
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            val uri: Uri? = data.data
            if (uri != null) {
                imageView.setImageURI(uri)
                bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, uri)
            }
        } else if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK && data != null) {
            val imageBitmap = data.extras?.get("data") as Bitmap
            imageView.setImageBitmap(imageBitmap)
            bitmap = imageBitmap
            analyzeImage()
        }
    }

    private fun analyzeImage() {
        val classifier = HandClassifier(assets, "handver2.tflite", "handlabel.txt", 224)
        val results = classifier.recognizeImage(bitmap)
        val resultText = results.joinToString(separator = "\n") { it.title }
        textView.text = resultText
    }

    companion object {
        private const val REQUEST_IMAGE_CAPTURE = 101
        private const val CAMERA_PERMISSION_REQUEST_CODE = 102
    }
}