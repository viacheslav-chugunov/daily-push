package com.dev.dailypush.app.presentation.blessing

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dev.dailypush.R
import com.dev.dailypush.app.adapter.BlessingAdapter
import com.dev.dailypush.app.base.App
import com.dev.dailypush.app.base.AppFragment
import com.dev.dailypush.app.base.AppListFragment
import com.dev.dailypush.business.models.Blessing
import com.dev.dailypush.data.db.helper.BlessingHelper.Blessings.COINS
import com.dev.dailypush.data.db.helper.BlessingHelper.Blessings.CONTEST
import com.dev.dailypush.data.db.helper.BlessingHelper.Blessings.LUCK
import com.dev.dailypush.data.db.helper.BlessingHelper.Blessings.MYSTERY
import com.dev.dailypush.data.db.helper.BlessingHelper.Blessings.NONE
import com.dev.dailypush.data.db.helper.BlessingHelper.Blessings.SCORE
import com.dev.dailypush.data.repository.BlessingRepository
import moxy.MvpAppCompatFragment
import javax.inject.Inject

class BlessingListFragment : AppListFragment(R.layout.fragment_blessing_list) {

    @Inject lateinit var repository: BlessingRepository

    private lateinit var recycler: RecyclerView

    private val listeners = listOf(
        object : BlessingAdapter.Listener { override fun onBlessingSelected() = setBlessing(NONE) },
        object : BlessingAdapter.Listener { override fun onBlessingSelected() = setBlessing(SCORE) },
        object : BlessingAdapter.Listener { override fun onBlessingSelected() = setBlessing(COINS) },
        object : BlessingAdapter.Listener { override fun onBlessingSelected() = setBlessing(CONTEST) },
        object : BlessingAdapter.Listener { override fun onBlessingSelected() = setBlessing(LUCK) },
        object : BlessingAdapter.Listener { override fun onBlessingSelected() = setBlessing(MYSTERY) }
    )

    override fun injectDagger() =
        App.appComponent.inject(this)

    private fun setBlessing(blessing: String) {
        repository.setBlessing(blessing)
        updateAdapter()

//        val selectedPos = when(repository.getBlessing()) {
//            NONE -> 0
//            SCORE -> 1
//            COINS -> 2
//            CONTEST -> 3
//            LUCK -> 4
//            else -> 5
//        }
//
//        println(selectedPos)
//        println(blessing)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?, ): View? {
        recycler = createListView(inflater, container)
        recycler.layoutManager = object : LinearLayoutManager(context) {
            override fun canScrollVertically(): Boolean = false
        }
        updateAdapter()
        return recycler
    }

    private fun updateAdapter() {
        val selectedPos = when(repository.getBlessing()) {
            NONE -> 0
            SCORE -> 1
            COINS -> 2
            CONTEST -> 3
            LUCK -> 4
            else -> 5
        }
        updateListAdapter(recycler, BlessingAdapter(context!!, Blessing.getAll(), listeners,
            Blessing.unlocked(repository), selectedPos))
    }
}