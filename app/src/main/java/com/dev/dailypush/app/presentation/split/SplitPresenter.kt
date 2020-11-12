package com.dev.dailypush.app.presentation.split

import android.content.Context
import com.dev.dailypush.business.ad.InterstitialAd
import moxy.MvpPresenter

class SplitPresenter : MvpPresenter<SplitView>() {

    fun tryToLoadAd(c: Context) =
        InterstitialAd.adOnSplit(c, viewState).run()
}