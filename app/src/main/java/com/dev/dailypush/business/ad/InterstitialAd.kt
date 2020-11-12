package com.dev.dailypush.business.ad

import android.content.Context
import com.dev.dailypush.app.presentation.split.SplitView
import com.google.android.gms.ads.*
import com.google.android.gms.ads.InterstitialAd as GoogleInterstitial

class InterstitialAd(private val context: Context,
                     private val setOnAdClosed: () -> Unit,
                     private val setOnAdFailedToLoad: () -> Unit) {

    fun run() {
        val ad = setupInterstitialAd()
        ad.adListener = object : AdListener() {
            override fun onAdLoaded() = ad.show()
            override fun onAdClosed() = setOnAdClosed()
            override fun onAdFailedToLoad(p0: LoadAdError?) = setOnAdFailedToLoad()
        }
    }

    private fun setupInterstitialAd() : GoogleInterstitial {
        MobileAds.initialize(context) {}
        val ad = GoogleInterstitial(context)
        ad.adUnitId = "ca-app-pub-6875098488739325/7948029825"
        ad.loadAd(AdRequest.Builder().build())
        return ad
    }

    companion object {
        fun adOnSplit(c: Context, view: SplitView) : InterstitialAd {
            val startNextActivity = { view.startActivity() }
            return InterstitialAd(c, startNextActivity, startNextActivity)
        }
    }
}