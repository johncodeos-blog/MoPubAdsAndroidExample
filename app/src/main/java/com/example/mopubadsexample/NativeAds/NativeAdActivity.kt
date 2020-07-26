package com.example.mopubadsexample.NativeAds

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mopubadsexample.NativeAds.nativeAd.NativeAdSingleView
import com.example.mopubadsexample.NativeAds.nativeAdRecyclerView.NativeAdRecyclerView
import com.example.mopubadsexample.NativeAds.NativeAdViewPager.NativeAdViewPager
import com.example.mopubadsexample.R
import kotlinx.android.synthetic.main.activity_native_ad.*


class NativeAdActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_native_ad)

        native_single_view_btn.setOnClickListener {
            val intent = Intent(this, NativeAdSingleView::class.java)
            startActivity(intent)
        }


        native_recycerview_btn.setOnClickListener {
            val intent = Intent(this, NativeAdRecyclerView::class.java)
            startActivity(intent)
        }


        native_viewpager_btn.setOnClickListener {
            val intent = Intent(this, NativeAdViewPager::class.java)
            startActivity(intent)
        }
    }
}
