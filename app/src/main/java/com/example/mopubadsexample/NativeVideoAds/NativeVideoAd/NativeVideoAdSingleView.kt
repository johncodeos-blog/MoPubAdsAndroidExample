package com.example.mopubadsexample.NativeVideoAds.nativeVideoAd

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.mopubadsexample.MyMoPub
import com.example.mopubadsexample.R
import com.mopub.nativeads.*
import kotlinx.android.synthetic.main.activity_native_video_ad_single_view.*
import android.view.View
import com.example.mopubadsexample.Constant


class NativeVideoAdSingleView : AppCompatActivity() {

    private lateinit var moPubNativeVideoNetworkListener: MoPubNative.MoPubNativeNetworkListener

    private lateinit var moPubNativeVideoEventListener: NativeAd.MoPubNativeEventListener

    private lateinit var moPubNativeVideo: MoPubNative

    private lateinit var adapterHelper: AdapterHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_native_video_ad_single_view)
        MyMoPub().init(this, Constant.MoPub.NATIVE_VIDEO_AD_UNIT)
        Handler().postDelayed({
            initAds()
        }, 200)

    }

    private fun initAds() {
        moPubNativeVideoEventListener = object : NativeAd.MoPubNativeEventListener{
            override fun onClick(view: View?) {
                // Click tracking.
                native_video_ad_status.text = "Native video ad recorded a click."
            }

            override fun onImpression(view: View?) {
                // Impress is recorded - do what is needed AFTER the ad is visibly shown here.
                native_video_ad_status.text = "Native video ad recorded an impression."
            }

        }


        moPubNativeVideoNetworkListener = object : MoPubNative.MoPubNativeNetworkListener {
            override fun onNativeLoad(nativeAd: NativeAd?) {
                adapterHelper = AdapterHelper(this@NativeVideoAdSingleView, 0, 3)
                val v = adapterHelper.getAdView(null, native_video_ad_frame,nativeAd,ViewBinder.Builder(0).build())
                nativeAd!!.setMoPubNativeEventListener(moPubNativeVideoEventListener)
                native_video_ad_frame.addView(v)
                native_video_ad_status.text = "Native video ad has loaded."
            }

            override fun onNativeFail(errorCode: NativeErrorCode) {
                native_video_ad_status.text = "Native video ad failed to load with error: $errorCode"
            }
        }

        moPubNativeVideo = MoPubNative(this, Constant.MoPub.NATIVE_VIDEO_AD_UNIT, moPubNativeVideoNetworkListener)


        val mediaViewBinder = MediaViewBinder.Builder(R.layout.mopub_native_video_ad_view)
            .mediaLayoutId(R.id.mopub_native_video_ad_media_layout)
            .iconImageId(R.id.mopub_native_video_ad_icon)
            .titleId(R.id.mopub_native_video_ad_title)
            .textId(R.id.mopub_native_video_ad_text)
            .privacyInformationIconImageId(R.id.mopub_native_video_ad_privacy)
            .build()

        val moPubVideoNativeAdRenderer = MoPubVideoNativeAdRenderer(mediaViewBinder)
        moPubNativeVideo.registerAdRenderer(moPubVideoNativeAdRenderer)
        moPubNativeVideo.makeRequest()
        native_video_ad_status.text = "Native video ad is loading."
    }


    override fun onDestroy() {
        moPubNativeVideo.destroy()
        super.onDestroy()
    }
}
