package com.dev.dailypush.app.base

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class AppListFragment(contextView: Int) : AppFragment(contextView) {

    protected fun createListViewWithManager(inflater: LayoutInflater, container: ViewGroup?, context: Context?): RecyclerView =
        createListView(inflater, container).apply { layoutManager = LinearLayoutManager(context) }

    protected fun createListView(inflater: LayoutInflater, container: ViewGroup?) : RecyclerView =
        createView(inflater, container) as RecyclerView

    protected fun updateListAdapter(recycler: RecyclerView, adapter: RecyclerView.Adapter<*>) {
        recycler.adapter = adapter
    }
}