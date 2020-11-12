package com.dev.dailypush.app.presentation.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.dev.dailypush.app.presentation.blessing.BlessingActivity
import com.dev.dailypush.R
import com.dev.dailypush.app.presentation.addition.WordAdditionActivity
import com.dev.dailypush.app.adapter.UserDataAdapter
import com.dev.dailypush.app.base.App
import com.dev.dailypush.app.base.AppActivity
import com.dev.dailypush.app.presentation.shop.TicketsShopActivity
import com.dev.dailypush.business.consts.Lang.EN
import com.dev.dailypush.business.consts.Lang.RU
import com.dev.dailypush.business.extensions.*
import com.dev.dailypush.data.repository.UserRepository
import kotlinx.android.synthetic.main.activity_main.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class MainActivity : AppActivity(R.layout.activity_main), MainView {

    @Inject @InjectPresenter lateinit var presenter: MainPresenter

    @ProvidePresenter fun providePresenter() = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBackground(bg_container)
        setViewPagerAdapter(UserDataAdapter(this, supportFragmentManager))
    }

    override fun setupToolbar() =
        bindToolbar()

    override fun injectDagger() =
        App.appComponent.inject(this)

    fun onClickTranslateToRuButton(view: View) =
        presenter.startContestIfHaveTickets(this, RU)

    fun onClickTranslateToEnButton(view: View) =
        presenter.startContestIfHaveTickets(this, EN)

    fun onClickAdditionWordsButton(view: View) =
        startActivity(WordAdditionActivity::class.java)

    override fun showNoTicketsMessage() =
        showToast(R.string.no_ticket, Toast.LENGTH_SHORT)

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.tickets_shop -> { startActivity(TicketsShopActivity::class.java); true }
            R.id.blessing -> { startActivity(BlessingActivity::class.java); true }
            R.id.paint -> {
                presenter.updateTheme()
                startActivityInNewTask(MainActivity::class.java)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
}