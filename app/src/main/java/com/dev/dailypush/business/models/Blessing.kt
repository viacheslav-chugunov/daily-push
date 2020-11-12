package com.dev.dailypush.business.models

import com.dev.dailypush.R
import com.dev.dailypush.data.db.helper.BlessingHelper
import com.dev.dailypush.data.repository.BlessingRepository

data class Blessing(val drawable: Int,
                    val title: Int,
                    val description: Int,
                    val unlockInfo: Int) {

    companion object {
        fun getAll() : List<Blessing> =
            listOf(
                Blessing(
                    R.drawable.none_blessing,
                    R.string.none_blessing_title,
                    R.string.none_blessing_description,
                    -1
                ),
                Blessing(
                    R.drawable.risk_blessing,
                    R.string.score_blessing_title,
                    R.string.score_blessing_description,
                    R.string.score_blessing_unlocking
                ),
                Blessing(
                    R.drawable.gold_blessing,
                    R.string.coins_blessing_title,
                    R.string.coins_blessing_description,
                    R.string.coins_blessing_unlocking
                ),
                Blessing(
                    R.drawable.infinity_blessing,
                    R.string.contest_blessing_title,
                    R.string.contest_blessing_description,
                    R.string.contest_blessing_unlocking
                ),
                Blessing(
                    R.drawable.simplification_blessing,
                    R.string.luck_blessing_title,
                    R.string.luck_blessing_description,
                    R.string.luck_blessing_unlocking
                ),
                Blessing(
                    R.drawable.mystery_blessing,
                    R.string.mystery_blessing_title,
                    R.string.mystery_blessing_description,
                    R.string.mystery_blessing_unlocking
                )
            )

        fun unlocked(repository: BlessingRepository) : List<Boolean> =
            listOf(
                repository.isBlessingUnlocked(BlessingHelper.Blessings.NONE),
                repository.isBlessingUnlocked(BlessingHelper.Blessings.SCORE),
                repository.isBlessingUnlocked(BlessingHelper.Blessings.COINS),
                repository.isBlessingUnlocked(BlessingHelper.Blessings.CONTEST),
                repository.isBlessingUnlocked(BlessingHelper.Blessings.LUCK),
                repository.isBlessingUnlocked(BlessingHelper.Blessings.MYSTERY)
            )
    }

}