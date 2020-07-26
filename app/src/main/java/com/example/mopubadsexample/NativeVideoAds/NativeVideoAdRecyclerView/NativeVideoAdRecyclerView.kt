package com.example.mopubadsexample.NativeVideoAds.nativeVideoAdRecyclerView

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mopubadsexample.Constant
import com.example.mopubadsexample.MyMoPub
import com.example.mopubadsexample.R
import com.mopub.nativeads.*
import kotlinx.android.synthetic.main.activity_native_video_ad_recycler_view.*

class NativeVideoAdRecyclerView : AppCompatActivity() {

    lateinit var itemsCells: ArrayList<String?>
    //lateinit var adapter: NativeVideoAd_RVAdapter
    private lateinit var moPubAdapter: MoPubRecyclerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_native_video_ad_recycler_view)
        MyMoPub().init(this, Constant.MoPub.NATIVE_VIDEO_AD_UNIT)

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
//        adapter =
//            NativeVideoAd_RVAdapter(itemsCells)
//        native_video_ad_rv.adapter = adapter
//        initAds()
//        adapter.notifyDataSetChanged()
    }

    private fun setRVLayoutManager() {
        val mLayoutManager = LinearLayoutManager(native_video_ad_rv.context)
        native_video_ad_rv.layoutManager = mLayoutManager
        native_video_ad_rv.setHasFixedSize(true)
    }

    private fun initAds() {
        //moPubAdapter = MoPubRecyclerAdapter(this, adapter)
        val mediaViewBinder = MediaViewBinder.Builder(R.layout.mopub_native_video_ad_view)
            .mediaLayoutId(R.id.mopub_native_video_ad_media_layout)
            .iconImageId(R.id.mopub_native_video_ad_icon)
            .titleId(R.id.mopub_native_video_ad_title)
            .textId(R.id.mopub_native_video_ad_text)
            .privacyInformationIconImageId(R.id.mopub_native_video_ad_privacy)
            .build()

        val moPubVideoNativeAdRenderer = MoPubVideoNativeAdRenderer(mediaViewBinder)

        // You should still register a renderer for static native ads as well.
        val staticViewBinder = ViewBinder.Builder(R.layout.mopub_native_ad_view)
            .titleId(R.id.mopub_native_ad_title)
            .textId(R.id.mopub_native_ad_text)
            .mainImageId(R.id.mopub_native_ad_main_imageview)
            .iconImageId(R.id.mopub_native_ad_icon)
            .callToActionId(R.id.mopub_native_ad_cta)
            .privacyInformationIconImageId(R.id.mopub_native_ad_privacy)
            .build()

        val moPubStaticNativeAdRenderer = MoPubStaticNativeAdRenderer(staticViewBinder)
        moPubAdapter.registerAdRenderer(moPubStaticNativeAdRenderer)
        moPubAdapter.registerAdRenderer(moPubVideoNativeAdRenderer)
        native_video_ad_rv.adapter = moPubAdapter
        moPubAdapter.loadAds(Constant.MoPub.NATIVE_VIDEO_AD_UNIT)
    }

    override fun onDestroy() {
        moPubAdapter.destroy()
        super.onDestroy()
    }

}
