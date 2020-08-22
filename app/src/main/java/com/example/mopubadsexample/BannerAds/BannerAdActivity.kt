package com.example.mopubadsexample.BannerAds

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.mopubadsexample.Constant
import com.example.mopubadsexample.MyMoPub
import com.example.mopubadsexample.R
import com.mopub.mobileads.MoPubErrorCode
import com.mopub.mobileads.MoPubView
import kotlinx.android.synthetic.main.activity_banner_ad.*

class BannerAdActivity : AppCompatActivity() {

    private lateinit var moPubBanner: MoPubView

    private lateinit var moPubViewBannerAdListener: MoPubView.BannerAdListener

    private var isMediumRectangleAd = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_banner_ad)
        MyMoPub().init(this,Constant.MoPub.BANNER_AD_UNIT)

        Handler(Looper.getMainLooper()).postDelayed({
            initAds()
        }, 200)
    }


    private fun initAds() {
        moPubBanner = findViewById(R.id.banner_view)
        moPubBanner.setAdUnitId(Constant.MoPub.BANNER_AD_UNIT)
        moPubBanner.loadAd()

        banner_ad_status.text = "Banner ad is loading."

        moPubViewBannerAdListener = object : MoPubView.BannerAdListener {
            override fun onBannerExpanded(banner: MoPubView) {
                banner_ad_status.text = "Banner ad has expanded."
            }

            override fun onBannerLoaded(banner: MoPubView) {
                banner_ad_status.text = "Banner ad has loaded."
            }

            override fun onBannerCollapsed(banner: MoPubView) {
                banner_ad_status.text = "Banner ad has collapsed."
            }

            override fun onBannerFailed(banner: MoPubView, errorCode: MoPubErrorCode) {
                banner_ad_status.text = "Banner ad failed to load with error:$errorCode"
            }

            override fun onBannerClicked(banner: MoPubView?) {
                banner_ad_status.text = "Banner ad was clicked."
            }
        }

        moPubBanner.bannerAdListener = moPubViewBannerAdListener

        switch_banner_btn.setOnClickListener {
            if (!isMediumRectangleAd) {
                isMediumRectangleAd = true
                moPubBanner.setAdUnitId(Constant.MoPub.MEDIUM_RECT_AD_UNIT) //Ad Unit for Medium Rectangle Ad (300x250)
                moPubBanner.loadAd()
                switch_banner_btn.text = "Show Banner Ad"
            } else {
                isMediumRectangleAd = false
                moPubBanner.setAdUnitId(Constant.MoPub.BANNER_AD_UNIT) //Ad Unit for Banner Ad (320x50)
                moPubBanner.loadAd()
                switch_banner_btn.text = "Show Medium Rectangle Ad"
            }
        }


    }


    override fun onDestroy() {
        moPubBanner.destroy()
        super.onDestroy()
    }
}
