package com.dev.dailypush.app.presentation.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.dev.dailypush.app.base.App
import com.dev.dailypush.R
import com.dev.dailypush.app.base.AppFragment
import com.dev.dailypush.data.repository.ScoreRepository
import javax.inject.Inject

class ScoreFragment : AppFragment(R.layout.fragment_score) {
    private lateinit var scoreView: TextView

    @Inject lateinit var repository: ScoreRepository

    override fun injectDagger() =
        App.appComponent.inject(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = createView(inflater, container)
        scoreView = findSubView(view, R.id.score_text)
        update()
        return view
    }

    fun update() {
        scoreView.text = repository.getScore().toString()
    }
}