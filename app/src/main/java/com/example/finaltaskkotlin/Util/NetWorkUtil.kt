package com.example.finaltaskkotlin.Util

import android.content.Context
import android.net.ConnectivityManager

class NetWorkUtil {
    companion object{
        fun getConnectivityStatus(context: Context?):Boolean{
            val connectivityManager: ConnectivityManager =
                context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected
        }
    }
}