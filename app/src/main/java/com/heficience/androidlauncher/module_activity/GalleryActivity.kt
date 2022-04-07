package com.heficience.androidlauncher.module_activity

import android.R.attr
import android.database.MatrixCursor
import android.os.Build
import android.view.View
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.heficience.androidlauncher.data_class.PhotoDataClass
import com.heficience.androidlauncher.query.PhotoQuery
import android.app.Activity

import android.content.Intent

import android.R.attr.data
import android.net.Uri

import android.widget.AdapterView

import android.widget.AdapterView.OnItemClickListener
import com.heficience.androidlauncher.*
import android.os.Parcelable
import android.util.Log


class GalleryActivity : AppCompatActivity() {
    var state: Parcelable? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onResume() {
        super.onResume()
        setContentView(R.layout.activity_gallery)
        loadPhoto()
    }
    override fun onPause() {
        // Save ListView state @ onPause
        Log.d("LOG", "saving listview state")
        state =  findViewById<ListView>(R.id.gallery_list).onSaveInstanceState()
        super.onPause()
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun loadPhoto() {
        val galleryListView = findViewById<ListView>(R.id.gallery_list)

        val galleryList: MutableList<PhotoDataClass> =
            PhotoQuery().getAll(contentResolver)
        val mc = MatrixCursor(
            arrayOf(
                "_id",
                "photoUri"
            ), 20
        )
        val from = arrayOf(
            "_id",
            "photoUri")

        val to = intArrayOf(R.id.id_photo, R.id.gallery_picture)

        for (gallery in galleryList) {
            mc.addRow(arrayOf(gallery.id, gallery.photoURI))
        }

        val simple = SimpleCursorAdapter(this, R.layout.style_of_gallery_list, mc, from, to, 0)

        galleryListView.adapter = simple


        galleryListView.onItemClickListener = OnItemClickListener { parent, view, pos, id ->
            mc.moveToPosition(pos)
            val uri: String = mc.getString(mc.getColumnIndexOrThrow("photoUri"))
            val showImage = Intent(this, ImageActivity::class.java).apply {
                putExtra("URI", uri)
            }
            startActivity(showImage)
        }

        if(state != null) {
            Log.d("LOG", "trying to restore listview state");
            galleryListView.onRestoreInstanceState(state);
        }
    }

    fun goBack(@Suppress("UNUSED_PARAMETER")view: View) {
        this.finish()
    }
}