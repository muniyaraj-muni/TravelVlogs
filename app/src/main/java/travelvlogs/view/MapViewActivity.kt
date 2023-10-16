package travelvlogs.view

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.example.travelvlogs.R
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapViewActivity : AppCompatActivity(),OnMapReadyCallback {

    private var latitude : Double? = null
    private var longitude : Double? = null
    private lateinit var gMap : GoogleMap

    private val requestLocationPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            showLocation()
        } else {
            Toast.makeText(applicationContext,"Location Permission is required",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map_view)

        latitude = intent.getDoubleExtra("latitude", 0.0)
        longitude = intent.getDoubleExtra("longitude", 0.0)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map_view)  as SupportMapFragment
        mapFragment.getMapAsync(this)
        checkLocationPermission()

    }

    private fun checkLocationPermission() {
        if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            showLocation()
        }else{
            requestLocationPermissionLauncher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    @SuppressLint("MissingPermission")
    private fun showLocation() {
        val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        fusedLocationProviderClient.lastLocation.addOnSuccessListener {
            if(it != null){
                latitude = it.latitude
                longitude = it.longitude
                gMap.addMarker(MarkerOptions().position(LatLng(latitude!!,longitude!!)).title("Your Location"))
                gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(latitude!!,longitude!!),15.0f))
            }
        }.addOnCanceledListener {
            Toast.makeText(applicationContext,"cancel",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onMapReady(map: GoogleMap) {
        gMap = map
        if(latitude != null  && longitude != null){

        }
    }
}