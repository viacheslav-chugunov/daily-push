package com.dev.dailypush.app.presentation.contest

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.dev.dailypush.*
import com.dev.dailypush.app.adapter.TranslateAdapter
import com.dev.dailypush.app.base.App
import com.dev.dailypush.app.base.AppActivity
import com.dev.dailypush.app.presentation.analytics.AnalyticsActivity
import com.dev.dailypush.business.extensions.bindToolbar
import com.dev.dailypush.business.extensions.startActivityInNewTask
import com.dev.dailypush.app.presentation.main.MainActivity
import com.dev.dailypush.app.presentation.user.ScoreFragment
import com.dev.dailypush.business.extensions.showToast
import com.dev.dailypush.business.models.TranslateOption
import com.dev.dailypush.data.db.helper.BlessingHelper
import com.dev.dailypush.data.repository.BlessingRepository
import kotlinx.android.synthetic.main.activity_contest.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class ContestActivity : AppActivity(R.layout.activity_contest), ContestView {

    @Inject lateinit var blessingRepository: BlessingRepository
    @Inject @InjectPresenter lateinit var presenter: ContestPresenter

    @ProvidePresenter fun providePresenter() = presenter

    object Extra { const val LANG_TO_TRANSLATE = "lang to translate" }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBackground(bg_container)
        hidePrevRightAnswer()
        val lang = intent.extras!!.getString(Extra.LANG_TO_TRANSLATE)!!
        presenter.setup(lang)
        presenter.updateContest()
    }

    override fun injectDagger() =
        App.appComponent.inject(this)

    override fun setupToolbar() =
        bindToolbar(upButton = true)

    override fun updateContest(answer: String, options: List<TranslateOption>, listener: TranslateAdapter.Listener) {
        val translateList = supportFragmentManager.findFragmentById(R.id.translate_list) as TranslateListFragment
        word_to_translate.text = answer
                translateList.updateAdapter(answer, options, listener)
    }

    override fun updateScore() {
        val fr = supportFragmentManager.findFragmentById(R.id.score_fragment) as ScoreFragment
        fr.update()
    }

    override fun hidePrevRightAnswer() {
        prev_cardView.visibility = View.GONE
    }

    override fun showPrevRightAnswer(option: TranslateOption) {
        prev_cardView.visibility = View.VISIBLE
        prev_word.text = option.word
        prev_translate.text = option.translate
    }

    override fun showToastIfCannotPurchase() =
        showToast(R.string.cannot_purchase, Toast.LENGTH_SHORT)

    override fun showToastOnUnlockScoreBlessing() =
        showToast(R.string.score_blessing_unlocked, Toast.LENGTH_LONG)

    override fun showToastOnUnlockCoinsBlessing() =
        showToast(R.string.coins_blessing_unlocked, Toast.LENGTH_LONG)

    override fun showToastOnUnlockContestBlessing() =
        showToast(R.string.contest_blessing_unlocked, Toast.LENGTH_LONG)

    override fun startActivity(newActivity: Class<*>) =
        onClickQuitFromContestButton(null)

    fun onClickQuitFromContestButton(view: View?) {
        val intent = Intent(this, AnalyticsActivity::class.java)
        val extra = AnalyticsActivity.Extra
        intent.putExtra(extra.WRONG_OPTIONS, presenter.usersWrongOptions.toTypedArray())
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (blessingRepository.getBlessing() == BlessingHelper.Blessings.LUCK)
            return super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.contest, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id._60_percent -> {
                presenter.updateContestFor60Percent()
                true
            }
            R.id._40_percent -> {
                presenter.updateContestFor40Percent()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    override fun onBackPressed() =
        startActivityInNewTask(MainActivity::class.java)

}