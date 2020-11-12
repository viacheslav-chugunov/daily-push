package com.dev.dailypush.app.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.dev.dailypush.R
import com.dev.dailypush.app.base.App
import com.dev.dailypush.business.models.Blessing

class BlessingAdapter(private val context: Context,
                      private val blessings: List<Blessing>,
                      private val listeners: List<Listener>,
                      private val unlocked: List<Boolean>,
                      private val selectedPos: Int,
) : RecyclerView.Adapter<BlessingAdapter.ViewHolder>() {

    interface Listener { fun onBlessingSelected() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.blessing_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setupImageButton(position)
        holder.setupTitle(position)
        holder.setupDescription(position)
    }

    override fun getItemCount(): Int = blessings.count()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imageButton: ImageButton = view.findViewById(R.id.image_button)
        private val title: TextView = view.findViewById(R.id.title)
        private val description: TextView = view.findViewById(R.id.description)

        fun setupImageButton(position: Int) {
            if (unlocked[position]) {
                if (selectedPos == position) {
                    imageButton.setBackgroundColor(context.resources.getColor(App.getWindowBackgroundColor()))
                }
                imageButton.setImageDrawable(ContextCompat.getDrawable(context, blessings[position].drawable))
                imageButton.setOnClickListener { listeners[position].onBlessingSelected() }
            } else {
                imageButton.setImageDrawable(ContextCompat.getDrawable(context,
                    R.drawable.blessing_locked))
            }
        }

        fun setupTitle(position: Int) {
            title.text = context.getString(blessings[position].title)
        }

        fun setupDescription(position: Int) {
            description.text = if (unlocked[position])
                context.getString(blessings[position].description)
            else
                context.getString(blessings[position].unlockInfo)
        }
    }
}