package com.dev.dailypush.business.extensions

import androidx.fragment.app.FragmentPagerAdapter
import com.dev.dailypush.app.presentation.main.MainActivity
import kotlinx.android.synthetic.main.activity_main.*

fun MainActivity.setViewPagerAdapter(adapter: FragmentPagerAdapter) {
    view_pager.adapter = adapter
}