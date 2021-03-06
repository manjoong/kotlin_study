package com.drj.a.myapplication

import android.app.Activity
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import com.google.android.gms.location.*

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.yesButton
import java.util.jar.Manifest

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var fusedLoctionProviderClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: MyLocationCallBack
    private val REQUEST_ACCESS_FINE_LOCATION = 1000

    private fun permissionCheck(cancel: () -> Unit, ok: () -> Unit ){
        if (ContextCompat.checkSelfPermission(this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.ACCESS_FINE_LOCATION)){
                cancel()
            }else {
                ActivityCompat.requestPermissions(this,
                        arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                        REQUEST_ACCESS_FINE_LOCATION)
            }
        }else{
            ok()
        }
    }

    private  fun showPermissionInfoDialog(){
        alert("현재 위치 정보를 얻으려면 위치 권한이 필합니다"){
            yesButton {
                ActivityCompat.requestPermissions(this@MapsActivity,
                        arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                        REQUEST_ACCESS_FINE_LOCATION)
            }
            noButton {  }
        }.show()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        locationInit()
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     *
     */

    private fun locationInit() {
        fusedLoctionProviderClient = FusedLocationProviderClient(this)

        locationCallback = MyLocationCallBack()

        locationRequest = LocationRequest()

        locationRequest.priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY

        locationRequest.interval = 10000

        locationRequest.fastestInterval = 5000
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    override fun onResume() {
        super.onResume()
        addLocationListener()
    }
    private fun addLocationListener(){
        fusedLoctionProviderClient.requestLocationUpdates(locationRequest,
                locationCallback,
                null)
    }

    inner class MyLocationCallBack : LocationCallback(){
        override fun onLocationResult(locationResult : LocationResult?) {
            super.onLocationResult(locationResult)

            val location = locationResult?.lastLocation

            location?.run {
                val latLng = LatLng(latitude, longitude)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17f))
            }
        }
    }
}
