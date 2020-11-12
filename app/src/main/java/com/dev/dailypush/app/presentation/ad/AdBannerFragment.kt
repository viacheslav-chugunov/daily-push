package com.dev.dailypush.app.presentation.ad

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dev.dailypush.R
import com.dev.dailypush.app.base.AppFragment
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds

class AdBannerFragment : AppFragment(R.layout.fragment_ad_banner) {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = createView(inflater, container)
        val adBanner = findSubView<AdView>(view, R.id.ad_banner)
        MobileAds.initialize(context) {}
        val adRequest = AdRequest.Builder().build()
        adBanner.loadAd(adRequest)
        return view
    }
}