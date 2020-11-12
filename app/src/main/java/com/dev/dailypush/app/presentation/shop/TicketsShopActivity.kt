package com.dev.dailypush.app.presentation.shop

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.dev.dailypush.R
import com.dev.dailypush.app.adapter.ShopAdapter
import com.dev.dailypush.app.base.App
import com.dev.dailypush.app.base.AppActivity
import com.dev.dailypush.app.presentation.main.MainActivity
import com.dev.dailypush.app.presentation.user.StockFragment
import com.dev.dailypush.business.ad.InterstitialAd
import com.dev.dailypush.business.extensions.bindToolbar
import com.dev.dailypush.business.extensions.showToast
import com.dev.dailypush.business.extensions.startActivityInNewTask
import com.dev.dailypush.data.db.helper.BlessingHelper
import com.dev.dailypush.data.repository.BlessingRepository
import com.dev.dailypush.data.repository.UserRepository
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class TicketsShopActivity : AppActivity(R.layout.activity_tickets_shop), ShopAdapter.Listener {

    @Inject lateinit var blessingRepository: BlessingRepository
    @Inject lateinit var userRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBackground(bg_container)
        val fr = supportFragmentManager.findFragmentById(R.id.shop_fragment) as ShopListFragment
        fr.updateAdapter(this)
    }

    override fun injectDagger() =
        App.appComponent.inject(this)

    override fun setupToolbar() =
        bindToolbar(title = getString(R.string.tickets_shop), upButton = true)

    override fun onSuccessfulPurchase() {
        val fr = supportFragmentManager.findFragmentById(R.id.score_fragment) as StockFragment
        fr.update()
        tryToUnlockMysteryBlessing()
    }

    private fun tryToUnlockMysteryBlessing() {
        if (!blessingRepository.isBlessingUnlocked(BlessingHelper.Blessings.MYSTERY) &&
            userRepository.getTicketsCount() >= 50) {
            blessingRepository.unlockBlessing(BlessingHelper.Blessings.MYSTERY)
            showToast(R.string.mystery_blessing_unlocked, Toast.LENGTH_SHORT)
        }
    }

    override fun onFailedPurchase() =
        showToast(R.string.cannot_purchase, Toast.LENGTH_SHORT)

    fun getFreeTicketOnClick(view: View) =
        InterstitialAd(this, ::onAdClosed, ::onAdFailedToLoad).run()

    private fun onAdClosed() {
        userRepository.addTickets(1)
        val fr = supportFragmentManager.findFragmentById(R.id.score_fragment) as StockFragment
        fr.update()
    }

    private fun onAdFailedToLoad() =
        showToast(R.string.cannot_get_free_ticket, Toast.LENGTH_SHORT)

    override fun onBackPressed() =
        startActivityInNewTask(MainActivity::class.java)
}