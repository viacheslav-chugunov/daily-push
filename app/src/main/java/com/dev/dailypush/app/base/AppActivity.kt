package com.dev.dailypush.app.base

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.dev.dailypush.R
import moxy.MvpAppCompatActivity

abstract class AppActivity(private val contextView: Int) : MvpAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDagger()
        super.onCreate(savedInstanceState)
        setTheme(App.appTheme)
        setContentView(contextView)
        setupToolbar()
    }

    protected open fun setupToolbar() {}

    protected open fun setBackground(view: LinearLayout) {
        view.setBackgroundDrawable(ContextCompat.getDrawable(this, App.getBackground()))
    }

    protected open fun setBackground(view: ConstraintLayout) {
        view.setBackgroundDrawable(ContextCompat.getDrawable(this, App.getBackground()))
    }

    protected open fun injectDagger() {}
}