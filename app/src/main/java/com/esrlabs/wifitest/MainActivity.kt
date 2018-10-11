package com.esrlabs.wifitest

import android.content.Context
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            getCurrentSsid(view.context)
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    fun getCurrentSsid(context: Context): String? {
        var ssid: String? = null
        val connManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo1 = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        val networkInfo = connManager.activeNetworkInfo
        Log.d("AAA", "networkInfo: $networkInfo")
        val wifiManager = context.getSystemService(Context.WIFI_SERVICE) as WifiManager

        val connectionInfo = wifiManager.connectionInfo
        if (networkInfo.isConnected) {
            if (connectionInfo != null && !TextUtils.isEmpty(connectionInfo.ssid)) {
                ssid = connectionInfo.ssid
            }
        }

        Log.d("AAA", "wifi name: $ssid")
        return ssid
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
