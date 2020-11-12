package com.dev.dailypush.app.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moxy.MvpAppCompatFragment

abstract class AppFragment(private val contextView: Int) : MvpAppCompatFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDagger()
        super.onCreate(savedInstanceState)
    }

    protected open fun injectDagger() {}

    protected fun createView(inflater: LayoutInflater, container: ViewGroup?) : View =
        inflater.inflate(contextView, container, false)

    protected fun<R> findSubView(view: View, viewRes: Int) : R =
        view.findViewById(viewRes)
}