package com.example.mopubadsexample.NativeAds.nativeAdRecyclerView

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mopubadsexample.Constant
import com.example.mopubadsexample.MyMoPub
import com.example.mopubadsexample.R
import com.mopub.nativeads.MoPubRecyclerAdapter
import com.mopub.nativeads.MoPubStaticNativeAdRenderer
import com.mopub.nativeads.ViewBinder
import kotlinx.android.synthetic.main.activity_native_ad_recycler_view.*

class NativeAdRecyclerView : AppCompatActivity() {

    private lateinit var itemsCells: ArrayList<String?>
    private lateinit var adapter: NativeAd_RVAdapter
    private lateinit var moPubAdapter: MoPubRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_native_ad_recycler_view)
        //Initialize MoPub SDK
        MyMoPub().init(this, Constant.MoPub.NATIVE_AD_UNIT)

        //** Set the data for our ArrayList
        setItemsData()

        //** Set the adapter of the RecyclerView
        setAdapter()

        //** Set the Layout Manager of the RecyclerView
        setRVLayoutManager()

    }

    private fun setItemsData() {
        itemsCells = ArrayList()
        for (i in 0..40) {
            itemsCells.add("Item $i")
        }
    }

    private fun setAdapter() {
        adapter =
            NativeAd_RVAdapter(itemsCells)
        native_ad_rv.adapter = adapter
        Handler(Looper.getMainLooper()).postDelayed({
            initNativeAds()
        }, 200)
        adapter.notifyDataSetChanged()
    }

    private fun setRVLayoutManager() {
        val mLayoutManager = LinearLayoutManager(native_ad_rv.context)
        native_ad_rv.layoutManager = mLayoutManager
        native_ad_rv.setHasFixedSize(true)
    }

    private fun initNativeAds() {
        moPubAdapter = MoPubRecyclerAdapter(this, adapter)
        val myViewBinder = ViewBinder.Builder(R.layout.mopub_native_ad_view)
            .titleId(R.id.mopub_native_ad_title)
            .textId(R.id.mopub_native_ad_text)
            .mainImageId(R.id.mopub_native_ad_main_imageview)
            .iconImageId(R.id.mopub_native_ad_icon)
            .callToActionId(R.id.mopub_native_ad_cta)
            .privacyInformationIconImageId(R.id.mopub_native_ad_privacy)
            .build()
        val myRenderer = MoPubStaticNativeAdRenderer(myViewBinder)
        moPubAdapter.registerAdRenderer(myRenderer)
        native_ad_rv.adapter = moPubAdapter
        moPubAdapter.loadAds(Constant.MoPub.NATIVE_AD_UNIT)
    }


    override fun onDestroy() {
        moPubAdapter.destroy()
        super.onDestroy()
    }

}
