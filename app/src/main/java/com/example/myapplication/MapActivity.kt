package com.example.myapplication

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.room.Room
import com.example.mobilecomputing_lecture5.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import kotlinx.android.synthetic.main.activity_map.*
import mobile_computing.R
import org.jetbrains.anko.doAsync
import com.google.android.gms.maps.OnMapReadyCallback
import java.util.jar.Manifest

class MapActivity : AppCompatActivity(), OnMapReadyCallback{

    lateinit var gMap:GoogleMap
    lateinit var fusedLocationClient: FusedLocationProviderClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        (map_fragment as SupportMapFragment).getMapAsync(this)
        map_create.setOnClickListener {
            val reminder = Reminder(
                uid = null,
                time = null,
                location = "65.059640\n25.466246",
                message = "test"
            )

        doAsync {
            val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java,
                .build()

            db.reminderDao().insert(reminder)
            db.close()
        }

            finish()

        }
    }

    override fun onMapReady(map: GoogleMap?){
        gMap=map ?: return
        if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED) || (ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_COARSE_LOCATION)==PackageManager.PERMISSION_GRANTED){
            gMap.isMyLocationEnabled = true
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
            fusedLocationClient.locationAvailability.addOnSuccessListener {
                var latLong=
            }
        }

}
}
