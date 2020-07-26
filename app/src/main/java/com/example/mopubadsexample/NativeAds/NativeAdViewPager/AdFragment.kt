package com.example.mopubadsexample.NativeAds.NativeAdViewPager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mopub.nativeads.MoPubStreamAdPlacer

class AdFragment: Fragment() {

    lateinit var mAdPlacer: MoPubStreamAdPlacer


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val position = arguments!!.getInt(ARG_AD_POSITION)
        mAdPlacer.placeAdsInRange(position - 5, position + 5)
        return mAdPlacer.getAdView(position, null, container)
    }

    companion object {

        private val ARG_AD_POSITION = "ad_position"

        fun newInstance(adPosition:Int, mStreamAdPlacer: MoPubStreamAdPlacer):AdFragment {
            val fragment = AdFragment()
            fragment.mAdPlacer = mStreamAdPlacer
            val bundle = Bundle()
            bundle.putInt(ARG_AD_POSITION, adPosition)
            fragment.arguments = bundle
            return fragment
        }
    }
}