package com.example.mopubadsexample.NativeAds.nativeAd

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.mopubadsexample.Constant
import com.example.mopubadsexample.MyMoPub
import com.example.mopubadsexample.R
import com.mopub.nativeads.*
import kotlinx.android.synthetic.main.activity_native_ad_single_view.*
import com.mopub.nativeads.NativeAd




class NativeAdSingleView : AppCompatActivity() {


    private lateinit var moPubNativeNetworkListener: MoPubNative.MoPubNativeNetworkListener

    private lateinit var moPubNativeEventListener: NativeAd.MoPubNativeEventListener

    private lateinit var moPubNative: MoPubNative

    private lateinit var adapterHelper: AdapterHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_native_ad_single_view)

        MyMoPub().init(this, Constant.MoPub.NATIVE_AD_UNIT)

        Handler(Looper.getMainLooper()).postDelayed({
            initAds()
        }, 200)
    }


    private fun initAds() {
        moPubNativeEventListener = object : NativeAd.MoPubNativeEventListener{
            override fun onClick(view: View?) {
                // Click tracking.
                native_ad_status.text = "Native ad recorded a click."
            }

            override fun onImpression(view: View?) {
                // Impress is recorded - do what is needed AFTER the ad is visibly shown here.
                native_ad_status.text = "Native ad recorded an impression."
            }

        }

        moPubNativeNetworkListener = object : MoPubNative.MoPubNativeNetworkListener {
            override fun onNativeLoad(nativeAd: NativeAd?) {
                adapterHelper = AdapterHelper(this@NativeAdSingleView, 0, 3)
                val v = adapterHelper.getAdView(null, native_ad_frame,nativeAd,ViewBinder.Builder(0).build())
                nativeAd!!.setMoPubNativeEventListener(moPubNativeEventListener)
                native_ad_frame.addView(v)
                native_ad_status.text = "Native ad has loaded."
            }

            override fun onNativeFail(errorCode: NativeErrorCode) {
                native_ad_status.text = "Native ad failed to load with error: $errorCode"
            }
        }

        moPubNative = MoPubNative(this, Constant.MoPub.NATIVE_AD_UNIT, moPubNativeNetworkListener)

        val viewBinder = ViewBinder.Builder(R.layout.mopub_native_ad_view)
            .titleId(R.id.mopub_native_ad_title)
            .textId(R.id.mopub_native_ad_text)
            .mainImageId(R.id.mopub_native_ad_main_imageview)
            .iconImageId(R.id.mopub_native_ad_icon)
            .callToActionId(R.id.mopub_native_ad_cta)
            .privacyInformationIconImageId(R.id.mopub_native_ad_privacy)
            .build()

        val adRenderer = MoPubStaticNativeAdRenderer(viewBinder)
        moPubNative.registerAdRenderer(adRenderer)
        moPubNative.makeRequest()
        native_ad_status.text = "Native ad is loading."
    }

    override fun onDestroy() {
        moPubNative.destroy()
        super.onDestroy()
    }
}
