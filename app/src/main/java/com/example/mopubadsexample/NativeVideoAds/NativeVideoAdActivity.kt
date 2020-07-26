package com.example.mopubadsexample.NativeVideoAds

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mopubadsexample.NativeVideoAds.nativeVideoAd.NativeVideoAdSingleView
import com.example.mopubadsexample.NativeVideoAds.nativeVideoAdRecyclerView.NativeVideoAdRecyclerView
import com.example.mopubadsexample.R
import kotlinx.android.synthetic.main.activity_native_video_ad.*


class NativeVideoAdActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_native_video_ad)

        native_video_single_view_btn.setOnClickListener {
            val intent = Intent(this, NativeVideoAdSingleView::class.java)
            startActivity(intent)
        }


        native_video_recycerview_btn.setOnClickListener {
            val intent = Intent(this, NativeVideoAdRecyclerView::class.java)
            startActivity(intent)
        }

    }
}
