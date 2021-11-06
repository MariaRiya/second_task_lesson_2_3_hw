package com.example.implicitintents

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun openWebsite(view: View) {

        val uri_webPage = Uri.parse(website_edittext.text.toString())
        val new_intent = Intent(Intent.ACTION_VIEW, uri_webPage)

        if (new_intent.resolveActivity(packageManager) != null) {
            startActivity(new_intent)
        }else{
            Log.d("ImplicitIntents", "Can't handle this!")
        }

    }
    fun openLocation(view: View) {

        val uri_loc = Uri.parse("geo:0,0?q=" + location_edittext.text.toString())
        val new_intent = Intent(Intent.ACTION_VIEW, uri_loc)

        if (new_intent.resolveActivity(packageManager) != null) {
            startActivity(new_intent)
        } else {
            Log.d("ImplicitIntents", "Can't handle this intent!")
        }

    }
    fun shareText(view: View) {

        val text_share = share_edittext.text.toString()
        val mimeType = "text/plain"
        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle(R.string.chooster_title)
                .setText(text_share)
                .startChooser()


    }

    fun takePicture(view: View) {

        val new_intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if(new_intent.resolveActivity(packageManager) != null) startActivity(new_intent)
        else             Log.d("ImplicitIntents", "Can't handle this intent!")

    }
}