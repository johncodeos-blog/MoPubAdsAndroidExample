package com.example.mopubadsexample.NativeAds.NativeAdViewPager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mopubadsexample.R
import kotlinx.android.synthetic.main.fragment_native_ad_view_pager_content.view.*

class ContentFragment: Fragment() {

    companion object {

        private val ARG_SECTION_NUMBER = "section_number"

        fun newInstance(sectionNumber: Int): ContentFragment {
            val fragment = ContentFragment()
            val args = Bundle()
            args.putInt(ARG_SECTION_NUMBER, sectionNumber)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_native_ad_view_pager_content, container, false)
        val contentNumber = arguments!!.getInt(ARG_SECTION_NUMBER)
        rootView.native_ad_view_pager_content_text.text = "Item $contentNumber"
        return rootView
    }
}