package com.example.mopubadsexample

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mopubadsexample.BannerAds.BannerAdActivity
import com.example.mopubadsexample.InterstitialAds.InterstitialAdActivity
import com.example.mopubadsexample.NativeAds.NativeAdActivity
import com.example.mopubadsexample.NativeVideoAds.NativeVideoAdActivity
import com.example.mopubadsexample.RewardedVideoAds.RewardedVideoAdActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        banner_btn.setOnClickListener {
            val intent = Intent(this, BannerAdActivity::class.java)
            startActivity(intent)
        }


        interstitial_btn.setOnClickListener {
            val intent = Intent(this, InterstitialAdActivity::class.java)
            startActivity(intent)
        }


        native_btn.setOnClickListener {
            val intent = Intent(this, NativeAdActivity::class.java)
            startActivity(intent)
        }


        native_video_btn.setOnClickListener {
            val intent = Intent(this, NativeVideoAdActivity::class.java)
            startActivity(intent)
        }

        rewarded_video_btn.setOnClickListener {
            val intent = Intent(this, RewardedVideoAdActivity::class.java)
            startActivity(intent)
        }

    }

}
