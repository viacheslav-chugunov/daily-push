package com.dev.dailypush.app.presentation.contest

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dev.dailypush.app.base.App
import com.dev.dailypush.R
import com.dev.dailypush.business.models.TranslateOption
import com.dev.dailypush.app.adapter.TranslateAdapter
import com.dev.dailypush.app.base.AppListFragment
import com.dev.dailypush.data.repository.AppWordRepository
import javax.inject.Inject

class TranslateListFragment : AppListFragment(R.layout.fragment_translate_list) {
    private lateinit var recycler: RecyclerView

    @Inject lateinit var repository: AppWordRepository

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
        createListViewWithManager(inflater, container, context).also { recycler = it }

    override fun injectDagger() =
        App.appComponent.inject(this)

    fun updateAdapter(answer: String, options: List<TranslateOption>, listener: TranslateAdapter.Listener)  =
        updateListAdapter(recycler, TranslateAdapter(answer, options, listener))
}