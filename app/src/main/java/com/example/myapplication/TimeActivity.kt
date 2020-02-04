package com.example.myapplication

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import java.util.*

class TimeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time)


        time_create.setOnClickListener{

            val calendar = GregorianCalendar(
                datePicker.year,
                datePicker.month,
                datePicker.dayOfMonth,
                datePicker.currentHour,
                datePicker.curreentMinute
            )


            val reminder = Reminder (
                uid = null,
                time = calendar.timeInMillis,
                location = null,
                message = et_message.text.toString()
            )

            if (et_message.text.toString() != "" && (calendar.timeInMillis > System.currentTimeMillis())) {

                val reminder = Reminder (
                    uid = null,
                    time = calendar.timeInMillis,
                    location = null,
                    message = et_message.text.toString()
                )
                doAsync {

                    val db = Rooom.databaseBuilder(
                        applicationContext,
                        AppDatabase::class.java,
                        "Reminders"
                    ).build()
                    db.reminderDao().insert(reminder)
                    db.close()

                    set.Alarm(reminder.time, reminder.message)

                    finish()
                }
            } else {
                toast("wrong data")
            }


    }
    private fun setAlarm(time: Long, message: String) {

        val intent = Intent(this, ReminderReceiver::class.java)
        intent.putExtra("message", message)

        val pendingIntent = PendingIntent.getBroadcast(this, 1, intent, PendingIntent.FLAG_ONE_SHOT)

        val manager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        manager.setExact(AlarmManager.RTC, time, pendingIntent)

        runOnUiThread(toast("Reminder is created"))
    }
}
