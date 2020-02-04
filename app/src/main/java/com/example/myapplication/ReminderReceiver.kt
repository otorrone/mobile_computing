package com.example.myapplication

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent


class ReminderReceiver : BroadcastReceiver() {

    Override fun onReceive(context: Context, intent: Intent) {

        val text = intent.getStringExtra("message)")

        context.toast(text!!)
    }

}