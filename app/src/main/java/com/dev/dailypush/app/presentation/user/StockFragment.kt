package com.dev.dailypush.app.presentation.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.dev.dailypush.R
import com.dev.dailypush.app.base.App
import com.dev.dailypush.app.base.AppFragment
import com.dev.dailypush.data.repository.UserRepository
import javax.inject.Inject


class StockFragment : AppFragment(R.layout.fragment_stock) {
    private lateinit var coins: TextView
    private lateinit var tickets: TextView
    private lateinit var ticketsImg: ImageView

    @Inject lateinit var repository: UserRepository

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        App.appComponent.inject(this)
        val view = createView(inflater, container)
        coins = findSubView(view, R.id.coins_text)
        tickets = findSubView(view, R.id.tickets_text)
        ticketsImg = findSubView(view, R.id.tickets_img)
        update()
        return view
    }

    fun update() {
        coins.text = repository.getCoinsCount().toString()
        tickets.text = repository.getTicketsCount().toString()
    }

}