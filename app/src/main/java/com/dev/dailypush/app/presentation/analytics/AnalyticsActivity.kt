package com.dev.dailypush.app.presentation.analytics

import android.os.Bundle
import com.dev.dailypush.R
import com.dev.dailypush.app.base.AppActivity
import com.dev.dailypush.app.presentation.main.MainActivity
import com.dev.dailypush.business.extensions.bindToolbar
import com.dev.dailypush.business.extensions.startActivityInNewTask
import com.dev.dailypush.business.extensions.updateAdapter
import kotlinx.android.synthetic.main.activity_main.*

class AnalyticsActivity : AppActivity(R.layout.activity_analytics) {

    object Extra { const val WRONG_OPTIONS = "wrong options" }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBackground(bg_container)
        updateAdapter()
    }

    override fun setupToolbar() =
        bindToolbar(upButton = true)

    override fun onBackPressed() =
        startActivityInNewTask(MainActivity::class.java)
}