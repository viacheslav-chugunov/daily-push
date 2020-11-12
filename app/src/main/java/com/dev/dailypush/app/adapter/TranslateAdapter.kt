package com.dev.dailypush.app.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.dev.dailypush.R
import com.dev.dailypush.business.models.TranslateOption


class TranslateAdapter(private val answer: String,
                       private val options: List<TranslateOption>,
                       private val listener: Listener
) : RecyclerView.Adapter<TranslateAdapter.ViewHolder>() {

    interface Listener {
        fun onRightAnswer()
        fun onWrongAnswer()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.translate_word_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setOption(options[position])
        holder.setListener(listener)
    }

    override fun getItemCount(): Int = options.count()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val layout: LinearLayout = view.findViewById(R.id.word_to_translate_layout)
        private val wordToTranslate: TextView = view.findViewById(R.id.word_to_translate)
        private lateinit var option: TranslateOption

        fun setOption(option: TranslateOption) {
            this.option = option
            wordToTranslate.text = option.translate
        }

        fun setListener(listener: Listener) =
            layout.setOnClickListener {
                if (answer == option.word)
                    listener.onRightAnswer()
                else
                    listener.onWrongAnswer()
            }
    }
}