package com.dev.dailypush.app.adapter

import android.content.Context
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.dev.dailypush.app.presentation.user.StockFragment
import com.dev.dailypush.app.presentation.user.ScoreFragment

class UserDataAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(pos: Int) = when (pos) {
        0 -> StockFragment()
        else -> ScoreFragment()
    }

    override fun getCount() = 2
}