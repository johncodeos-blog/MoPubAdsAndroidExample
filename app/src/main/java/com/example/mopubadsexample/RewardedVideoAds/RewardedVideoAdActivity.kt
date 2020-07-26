package com.example.mopubadsexample.RewardedVideoAds

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mopubadsexample.Constant
import com.example.mopubadsexample.MyMoPub
import com.example.mopubadsexample.R
import com.mopub.common.MoPubReward
import com.mopub.mobileads.MoPubErrorCode
import com.mopub.mobileads.MoPubRewardedVideoListener
import com.mopub.mobileads.MoPubRewardedVideos
import kotlinx.android.synthetic.main.activity_rewarded_video_ad.*


class RewardedVideoAdActivity : AppCompatActivity() {


    private lateinit var moPubRewardedVideoListener : MoPubRewardedVideoListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rewarded_video_ad)
        MyMoPub().init(this, Constant.MoPub.REWARDED_VIDEO_AD_UNIT)

        moPubRewardedVideoListener = object : MoPubRewardedVideoListener {
            override fun onRewardedVideoClosed(adUnitId: String) {
                rewarded_ad_status.text = "Rewarded ad has closed"
            }

            override fun onRewardedVideoCompleted(adUnitIds: MutableSet<String>, reward: MoPubReward) {
                //When the video ends, it triggers this callback in the background(before you press the close button)
                rewarded_ad_status.text = "Rewarded ad completed and the reward is: ${reward.amount}"
            }

            override fun onRewardedVideoPlaybackError(adUnitId: String, errorCode: MoPubErrorCode) {
                rewarded_ad_status.text = "Rewarded ad video playback error: $errorCode"
            }

            override fun onRewardedVideoLoadFailure(adUnitId: String, errorCode: MoPubErrorCode) {
                rewarded_ad_status.text = "Rewarded ad load failed: $errorCode"
            }

            override fun onRewardedVideoClicked(adUnitId: String) {
                rewarded_ad_status.text = "Rewarded ad clicked"
            }

            override fun onRewardedVideoStarted(adUnitId: String) {
                rewarded_ad_status.text = "Rewarded ad has started"
            }

            override fun onRewardedVideoLoadSuccess(adUnitId: String) {
                rewarded_ad_status.text = "Rewarded ad has loaded"
            }

        }

        MoPubRewardedVideos.setRewardedVideoListener(moPubRewardedVideoListener)

        load_rewarded_btn.setOnClickListener {
            MoPubRewardedVideos.loadRewardedVideo(Constant.MoPub.REWARDED_VIDEO_AD_UNIT)
            rewarded_ad_status.text = "Rewarded ad is loading"
        }

        check_rewarded_btn.setOnClickListener {
            val isAvailable =  MoPubRewardedVideos.hasRewardedVideo(Constant.MoPub.REWARDED_VIDEO_AD_UNIT)
            rewarded_ad_status.text = "Rewarded ad is available? $isAvailable"
        }

        show_rewarded_btn.setOnClickListener {
            MoPubRewardedVideos.showRewardedVideo(Constant.MoPub.REWARDED_VIDEO_AD_UNIT)
        }

    }



    override fun onDestroy() {
        MoPubRewardedVideos.setRewardedVideoListener(null)
        super.onDestroy()
    }
}
