package com.example.mopubadsexample.NativeAds.NativeAdViewPager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.mopub.nativeads.MoPubStreamAdPlacer

class CustomPagerAdapter(fm: FragmentManager,streamAdPlacer: MoPubStreamAdPlacer): FragmentStatePagerAdapter(fm) {

    private val mStreamAdPlacer: MoPubStreamAdPlacer = streamAdPlacer

    private val ITEM_COUNT = 30

    init{
        streamAdPlacer.setItemCount(ITEM_COUNT)
    }

    override fun getItemPosition(`object`:Any):Int {
        // This forces all items to be recreated when invalidate() is called on the ViewPager.
        return POSITION_NONE
    }

    override fun getCount(): Int {
        return mStreamAdPlacer.getAdjustedCount(ITEM_COUNT)
    }


    override fun getItem(position: Int): Fragment {
        mStreamAdPlacer.placeAdsInRange(position - 5, position + 5)
        if (mStreamAdPlacer.isAd(position)) {
            return AdFragment.newInstance(position, mStreamAdPlacer)
        } else {
            return ContentFragment.newInstance(mStreamAdPlacer.getOriginalPosition(position))
        }
    }

    override fun getPageTitle(position:Int):CharSequence {
        if (mStreamAdPlacer.isAd(position)){
            return "Advertisement"
        }else{
            return "Content Item " + mStreamAdPlacer.getOriginalPosition(position)
        }
    }
}