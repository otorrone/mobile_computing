package com.example.myapplication

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.room.Room
//import com.example.myapp1.MainActivity
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast


class ReminderReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        val uid = intent.getIntExtra("uid", 0)
        val text = intent.getStringExtra("message)")

        //context.toast(text!!)

        MainActivity.showNotification(context,text!!)

        doAsync {
            val db = Room.databaseBuilder(context, AppDatabase::class.java, "reminders").build()
            db.reminderDao().delete(uid)
            db.close()
        }
    }

}