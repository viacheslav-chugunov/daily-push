package com.dev.dailypush.business.models

import java.io.Serializable

data class TranslateOption(var word: String, var translate: String) : Serializable {

    fun reverse() { word = translate.also { translate = word } }
}