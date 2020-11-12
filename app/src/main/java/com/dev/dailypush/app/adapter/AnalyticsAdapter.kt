package com.dev.dailypush.app.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.dev.dailypush.R
import com.dev.dailypush.business.models.TranslateOption


class AnalyticsAdapter(private val options: List<TranslateOption>) : RecyclerView.Adapter<AnalyticsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.analytics_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setOption(options[position])
    }

    override fun getItemCount(): Int = options.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val word: TextView = view.findViewById(R.id.word)
        private val translate: TextView = view.findViewById(R.id.translate)

        fun setOption(option: TranslateOption) {
            word.text = option.word
            translate.text = option.translate
        }
    }
}