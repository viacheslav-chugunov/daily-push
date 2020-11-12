package com.dev.dailypush.app.presentation.analytics

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dev.dailypush.R
import com.dev.dailypush.app.adapter.AnalyticsAdapter
import com.dev.dailypush.app.base.AppListFragment
import com.dev.dailypush.business.models.TranslateOption

class AnalyticsListFragment : AppListFragment(R.layout.fragment_analytics_list) {
    private lateinit var recycler: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
        createListViewWithManager(inflater, container, context).also { recycler = it }

    fun updateAdapter(options: List<TranslateOption>) =
        updateListAdapter(recycler, AnalyticsAdapter(options))
}