package com.dev.dailypush.app.presentation.blessing

import android.os.Bundle
import com.dev.dailypush.R
import com.dev.dailypush.app.base.AppActivity
import com.dev.dailypush.business.extensions.bindToolbar
import kotlinx.android.synthetic.main.activity_blessing.*

class BlessingActivity : AppActivity(R.layout.activity_blessing) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBackground(bg_container)
    }

    override fun setupToolbar() =
        bindToolbar(title = getString(R.string.blessings), upButton = true)
}