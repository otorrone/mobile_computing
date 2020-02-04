package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.jetbrains.anko.doAsync
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

            doAsync (

                val db = Rooom.databaseBuilder(applicationContext, AppDatabase::class.java, "Reminders").build()
                db.reminderDao().insert(reminder)
                db.close()

                finish()
            )
        }
    }
}
