package com.dev.dailypush.business.extensions

import com.dev.dailypush.R
import com.dev.dailypush.app.presentation.analytics.AnalyticsActivity
import com.dev.dailypush.app.presentation.analytics.AnalyticsListFragment
import com.dev.dailypush.business.models.TranslateOption

fun AnalyticsActivity.updateAdapter() =
    (supportFragmentManager.findFragmentById(R.id. wrong_answers_fragment) as AnalyticsListFragment)
        .updateAdapter(getOptions())


private fun AnalyticsActivity.getOptions() =
    (intent.extras!!.getSerializable(AnalyticsActivity.Extra.WRONG_OPTIONS) as Array<TranslateOption>)
        .toList()