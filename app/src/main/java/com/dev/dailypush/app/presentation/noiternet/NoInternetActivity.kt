package com.dev.dailypush.app.presentation.noiternet

import android.os.Bundle
import android.os.Handler
import com.dev.dailypush.R
import com.dev.dailypush.app.base.AppActivity
import com.dev.dailypush.app.presentation.split.SplitActivity
import com.dev.dailypush.business.extensions.hasInternetPermission
import com.dev.dailypush.business.extensions.startActivityInNewTask
import kotlinx.android.synthetic.main.activity_no_internet.*


class NoInternetActivity : AppActivity(R.layout.activity_no_internet) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBackground(bg_container)
        runAppOnConnection()
    }

    private fun runAppOnConnection() {
        Handler().postDelayed({
            if (hasInternetPermission())
                startActivityInNewTask(SplitActivity::class.java)
            else
                runAppOnConnection()
        }, 1000)
    }
}