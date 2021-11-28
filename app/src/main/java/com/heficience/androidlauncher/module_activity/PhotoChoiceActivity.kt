package com.heficience.androidlauncher.module_activity

import android.content.Intent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.heficience.androidlauncher.R

class PhotoChoiceActivity : AppCompatActivity() {
    override fun onResume() {
        super.onResume()
        setContentView(R.layout.activity_photo_choice)

    }

    fun chooseGallery(@Suppress("UNUSED_PARAMETER")view: View) {
        val intent = Intent(this, GalleryActivity::class.java)
        startActivity(intent)
    }

    fun chooseCamera(@Suppress("UNUSED_PARAMETER")view: View) {
        var intent: Intent? = Intent("android.media.action.IMAGE_CAPTURE")
        startActivity(
            packageManager.getLaunchIntentForPackage(
                intent!!.resolveActivity(packageManager).packageName
            )
        )
    }

    fun goBack(@Suppress("UNUSED_PARAMETER")view: View) {
        this.finish()
    }
}