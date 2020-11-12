package com.dev.dailypush.app.presentation.split

import android.os.Bundle
import android.os.Handler
import com.dev.dailypush.app.presentation.noiternet.NoInternetActivity
import com.dev.dailypush.R
import com.dev.dailypush.app.base.AppActivity
import com.dev.dailypush.app.presentation.main.MainActivity
import com.dev.dailypush.business.extensions.hasInternetPermission
import com.dev.dailypush.business.extensions.startActivityInNewTask
import kotlinx.android.synthetic.main.activity_split.*
import moxy.presenter.InjectPresenter

class SplitActivity : AppActivity(R.layout.activity_split), SplitView {

    @InjectPresenter lateinit var presenter: SplitPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBackground(bg_container)
        if (!hasInternetPermission())
            startActivityInNewTask(NoInternetActivity::class.java)
        else
            Handler().postDelayed({ presenter.tryToLoadAd(this) }, 200)
    }

    override fun startActivity() =
        startActivityInNewTask(MainActivity::class.java)
}