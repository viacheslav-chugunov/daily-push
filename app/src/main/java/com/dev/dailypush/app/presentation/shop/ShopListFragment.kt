package com.dev.dailypush.app.presentation.shop

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dev.dailypush.R
import com.dev.dailypush.app.adapter.ShopAdapter
import com.dev.dailypush.app.base.App
import com.dev.dailypush.app.base.AppListFragment
import com.dev.dailypush.data.repository.UserRepository
import javax.inject.Inject


class ShopListFragment : AppListFragment(R.layout.fragment_shop_list) {

    @Inject lateinit var repository: UserRepository

    data class ShopItem(val ticketsCount: Int, val price: Int)

    private val shopItems = listOf(
        ShopItem(1, 25),
        ShopItem(5, 110),
        ShopItem(10, 200),
        ShopItem(20, 350),
        ShopItem(50, 800)
    )

    private lateinit var recycler: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
        createListViewWithManager(inflater, container, context).also { recycler = it }

    override fun injectDagger() =
        App.appComponent.inject(this)

    fun updateAdapter(listener: ShopAdapter.Listener) =
        updateListAdapter(recycler, ShopAdapter(shopItems, repository, listener))
}