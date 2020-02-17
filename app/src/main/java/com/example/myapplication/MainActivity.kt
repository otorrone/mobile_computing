package com.example.mobilecomputing_lecture5

import android.app.*
import android.content.*
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intentFilter = IntentFilter("trigger_toast")
        val broadcastReceiver = BackgroundTask()
        registerReceiver(broadcastReceiver, intentFilter)

        toast.setOnClickListener {
            Toast.makeText(applicationContext, "This is a toast", Toast.LENGTH_SHORT).show()
        }

        notification.setOnClickListener {
            val intentToast = Intent("trigger_toast")
            val pendingIntent = PendingIntent.getBroadcast(applicationContext, 0, intentToast, 0)

            val notification = Notification.Builder(applicationContext)
                .setContentTitle("Title")
                .setContentText("Notification example")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_stat_hello)
                .setPriority(Notification.PRIORITY_DEFAULT)

            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                val notificationChannel = NotificationChannel("MyNotChannel", "MyApp", NotificationManager.IMPORTANCE_DEFAULT)
                notificationManager.createNotificationChannel(notificationChannel)

                notification.setChannelId("MyNotChannel")
            }

            notificationManager.notify(1234, notification.build())
        }

        alertdialog.setOnClickListener {
            val alertDialog = AlertDialog.Builder(this).create()
            alertDialog.setTitle("Title")
            alertDialog.setMessage("This is VERY important. Do you agree?")
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes!") { dialog, which ->
                Toast.makeText(applicationContext, "You said yes!", Toast.LENGTH_SHORT).show()
            }
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE,"No") { _, _ ->
                Toast.makeText(applicationContext, "Nooooo", Toast.LENGTH_SHORT).show()
            }
            alertDialog.show()
        }

        snack.setOnClickListener {
            val mySnackBar = Snackbar.make(mainContainer, "Hello?", Snackbar.LENGTH_SHORT)
                .setAction("Hi!") {
                    Toast.makeText(applicationContext, "Nice to meet you!", Toast.LENGTH_SHORT).show()
                }
                .show()
        }
    }

    class BackgroundTask : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action.equals("trigger_toast")) {
                context?.startActivity(Intent(context, MainActivity::class.java))
                Toast.makeText(context, "Hello from the background", Toast.LENGTH_LONG).show()
            }
        }
    }
}