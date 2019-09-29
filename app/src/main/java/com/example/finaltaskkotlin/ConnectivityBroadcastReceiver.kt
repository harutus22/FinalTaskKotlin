package com.example.finaltaskkotlin

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.finaltaskkotlin.Util.NetWorkUtil

class ConnectivityBroadcastReceiver: BroadcastReceiver(){
    override fun onReceive(p0: Context?, p1: Intent?) {
        var status: String
        var connected = NetWorkUtil.getConnectivityStatus(p0)
        if(!connected) {
            status = StaticValues.INTERNET_DISCONNECTED;
            isConnected = false
        } else {
            status = StaticValues.INTERNET_CONNECTED;
            isConnected = true
        }
        Toast.makeText(p0, status, Toast.LENGTH_SHORT).show();
    }

    companion object{
        private var isConnected = false

        fun getConnection() = isConnected
    }

}