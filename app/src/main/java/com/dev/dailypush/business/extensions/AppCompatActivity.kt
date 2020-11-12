package com.dev.dailypush.business.extensions

import android.content.Intent
import android.graphics.PorterDuff
import android.graphics.text.LineBreaker
import android.net.ConnectivityManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dev.dailypush.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

fun AppCompatActivity.bindToolbar(title: String = getString(R.string.app_name), upButton: Boolean = false) {
    setSupportActionBar(toolbar)
    toolbar_title.text = title
    supportActionBar?.setDisplayHomeAsUpEnabled(upButton)
}

fun AppCompatActivity.startActivity(newActivity: Class<*>) {
    startActivity(Intent(this, newActivity))
}

fun AppCompatActivity.startActivityInNewTask(newActivity: Class<*>) {
    val intent = Intent(this, newActivity)
    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
    startActivity(intent)
    finish()
}

fun AppCompatActivity.showToast(strRes: Int, duration: Int) {
    val toast = Toast.makeText(this, strRes, duration)
    toast.view.run {
        background.setColorFilter(getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_IN)
        findViewById<TextView>(android.R.id.message).setTextColor(getColor(R.color.colorAccent))
    }
    toast.show()
}

fun AppCompatActivity.justifyText(vararg items: TextView) {
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q)
        for (item in items)
            item.justificationMode = LineBreaker.JUSTIFICATION_MODE_INTER_WORD
}

fun AppCompatActivity.hasInternetPermission(): Boolean {
    val cm = getSystemService(AppCompatActivity.CONNECTIVITY_SERVICE) as ConnectivityManager
    var wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
    if (wifiInfo != null && wifiInfo.isConnected)
        return true
    wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
    if (wifiInfo != null && wifiInfo.isConnected)
        return true
    wifiInfo = cm.activeNetworkInfo
    return wifiInfo != null && wifiInfo.isConnected
}