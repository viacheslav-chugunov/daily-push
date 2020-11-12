package com.dev.dailypush.app.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.dev.dailypush.R
import com.dev.dailypush.app.presentation.shop.ShopListFragment
import com.dev.dailypush.data.repository.UserRepository

class ShopAdapter(private val shopItems: List<ShopListFragment.ShopItem>,
                  private val repository: UserRepository,
                  private val listener: Listener
) : RecyclerView.Adapter<ShopAdapter.ViewHolder>() {

    interface Listener {
        fun onSuccessfulPurchase()
        fun onFailedPurchase()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shop_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = shopItems[position]
        holder.ticketsCount.text = item.ticketsCount.toString()
        holder.ticketsPrice.text = item.price.toString()
        holder.layout.setOnClickListener {
            if (repository.spendCoins(item.price)) {
                repository.addTickets(item.ticketsCount)
                listener.onSuccessfulPurchase()
            } else {
                listener.onFailedPurchase()
            }
        }
    }

    override fun getItemCount(): Int = shopItems.count()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val layout: CardView = view.findViewById(R.id.item_layout)
        val ticketsCount: TextView = view.findViewById(R.id.tickets_count)
        val ticketsPrice: TextView = view.findViewById(R.id.tickets_price)
    }
}