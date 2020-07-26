package com.example.mopubadsexample.NativeAds.NativeAdViewPager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.example.mopubadsexample.Constant
import com.example.mopubadsexample.MyMoPub
import com.example.mopubadsexample.R
import com.mopub.nativeads.*
import kotlinx.android.synthetic.main.activity_native_ad_view_pager.*

class NativeAdViewPager : AppCompatActivity() {

    lateinit var mPagerAdapter: CustomPagerAdapter
    lateinit var moPubNativeAdLoadedListener: MoPubNativeAdLoadedListener
    lateinit var adPlacer: MoPubStreamAdPlacer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_native_ad_view_pager)
        MyMoPub().init(this, Constant.MoPub.NATIVE_AD_UNIT)

        initAds()


        //** Set the adapter of the ViewPager
        setAdapter()

    }


    private fun initAds() {

        moPubNativeAdLoadedListener = object : MoPubNativeAdLoadedListener {
            override fun onAdRemoved(position: Int) {
                native_ad_pager.invalidate()
                mPagerAdapter.notifyDataSetChanged()
                Log.d("MoPub","Native ad has been removed")
            }

            override fun onAdLoaded(position: Int) {
                native_ad_pager.invalidate()
                mPagerAdapter.notifyDataSetChanged()
                Log.d("MoPub","Native ad has been loaded")
            }

        }


        // Set up a renderer for a static native ad.
        val moPubStaticNativeAdRenderer = MoPubStaticNativeAdRenderer(ViewBinder.Builder(R.layout.mopub_native_ad_view)
            .titleId(R.id.mopub_native_ad_title)
            .textId(R.id.mopub_native_ad_text)
            .mainImageId(R.id.mopub_native_ad_main_imageview)
            .iconImageId(R.id.mopub_native_ad_icon)
            .callToActionId(R.id.mopub_native_ad_cta)
            .privacyInformationIconImageId(R.id.mopub_native_ad_privacy)
            .build()
        )

        // This ad placer is used to automatically insert ads into the ViewPager.
        adPlacer = MoPubStreamAdPlacer(this)

        // The first renderer that can handle a particular native ad gets used.
        adPlacer.registerAdRenderer(moPubStaticNativeAdRenderer)
        adPlacer.setAdLoadedListener(moPubNativeAdLoadedListener)
    }

    private fun setAdapter() {
        mPagerAdapter = CustomPagerAdapter(supportFragmentManager, adPlacer)
        native_ad_pager.adapter = mPagerAdapter

        Handler().postDelayed({
            adPlacer.loadAds(Constant.MoPub.NATIVE_AD_UNIT)
            Log.d("MoPub","Native ad is loading.")
        },200)
    }


    override fun onDestroy() {
        // You must call this or the ad adapter may cause a memory leak.
        adPlacer.destroy()
        super.onDestroy()
    }

    override fun onResume() {
        // MoPub recommends to reload ads when the user returns to a view.
        adPlacer.loadAds(Constant.MoPub.NATIVE_AD_UNIT)
        super.onResume()
    }
}
