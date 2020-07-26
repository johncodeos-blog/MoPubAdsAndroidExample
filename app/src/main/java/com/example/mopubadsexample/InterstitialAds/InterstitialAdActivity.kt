package com.example.mopubadsexample.InterstitialAds

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.mopubadsexample.Constant
import com.example.mopubadsexample.MyMoPub
import com.example.mopubadsexample.R
import com.mopub.mobileads.MoPubErrorCode
import com.mopub.mobileads.MoPubInterstitial
import kotlinx.android.synthetic.main.activity_interstitial_ad.*

class InterstitialAdActivity : AppCompatActivity() {

    private lateinit var moPubInterstitial: MoPubInterstitial

    private lateinit var moPubInterstitialInterstitialAdListener: MoPubInterstitial.InterstitialAdListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interstitial_ad)
        MyMoPub().init(this, Constant.MoPub.INTERSTITIAL_AD_UNIT)

        Handler().postDelayed({
            initAds()
        }, 200)
    }

    private fun initAds() {

        moPubInterstitial = MoPubInterstitial(this, Constant.MoPub.INTERSTITIAL_AD_UNIT)
        interstitial_ad_status.text = "Interstitial ad is loading."
        moPubInterstitial.load()


        moPubInterstitialInterstitialAdListener =
            object : MoPubInterstitial.InterstitialAdListener {
                override fun onInterstitialLoaded(interstitial: MoPubInterstitial?) {
                    //Do something when insterstitial is loaded
                    interstitial_ad_status.text = "Interstitial ad is loaded."

                    //Show the Ad if the Interstitial is loaded
                    yourAppsShowInterstitialMethod()
                }

                override fun onInterstitialShown(interstitial: MoPubInterstitial?) {
                    //Do something when insterstitial is shown
                    interstitial_ad_status.text = "Interstitial ad has show."
                }

                override fun onInterstitialFailed(interstitial: MoPubInterstitial?,errorCode: MoPubErrorCode?) {
                    //Do something when insterstitial is failed
                    interstitial_ad_status.text = "Interstitial ad failed to load with error: $errorCode"
                }

                override fun onInterstitialDismissed(interstitial: MoPubInterstitial?) {
                    //Do something when insterstitial is dismissed
                    interstitial_ad_status.text = "Interstitial ad has dismissed."
                }

                override fun onInterstitialClicked(interstitial: MoPubInterstitial?) {
                    //Do something when insterstitial is clicked
                    interstitial_ad_status.text = "Interstitial ad clicked."
                }
            }

        moPubInterstitial.interstitialAdListener = moPubInterstitialInterstitialAdListener

    }


    private fun yourAppsShowInterstitialMethod() {
        if (moPubInterstitial.isReady) {
            moPubInterstitial.show()
        } else {
            // Caching is likely already in progress if `isReady()` is false.
            // Avoid calling `load()` here and instead rely on the callbacks on moPubInterstitialInterstitialAdListener
        }
    }

    override fun onDestroy() {
        moPubInterstitial.destroy()
        super.onDestroy()
    }
}
