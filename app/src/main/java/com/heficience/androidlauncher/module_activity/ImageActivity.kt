package com.heficience.androidlauncher.module_activity

import android.annotation.SuppressLint
import android.net.Uri
import android.os.*
import android.view.MotionEvent
import android.view.View
import android.view.WindowInsets
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.heficience.androidlauncher.CONTACT_TO_SMS
import com.heficience.androidlauncher.R
import com.heficience.androidlauncher.databinding.ActivityImageBinding


class ImageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityImageBinding

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val uri = intent.getStringExtra("URI").toString()
        binding = ActivityImageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val image : ImageView = findViewById<View>(R.id.image) as ImageView
        image.postInvalidate()
        image.setImageURI(Uri.parse(uri))


        val back :Button = findViewById<View>(R.id.back_btn) as Button
        back.setOnClickListener {
            this.finish()
        }
    }
    fun goBack(@Suppress("UNUSED_PARAMETER")view: View) {
        this.finish()
    }
}