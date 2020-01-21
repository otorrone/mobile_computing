package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab_time.setOnClickListener {
            val intent = Intent(applicationContext, TimeActivity::class.java)
            startActivity(intent)
        }

        fab_map.setOnClickListener {
            val intent = Intent(applicationContext, MapActivity::class.java)
            startActivity(intent)
        }
    }
}
