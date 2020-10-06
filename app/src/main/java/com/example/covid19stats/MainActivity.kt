package com.example.covid19stats

import android.Manifest
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.*
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.io.IOException
import java.util.*

class MainActivity : AppCompatActivity() , LocationListener {
    private var mLocationManager: LocationManager? = null
    private var checkRefresh:Boolean = false
    private var homePage:Fragment = HomeFragment()
    private var worldPage:Fragment = WorldFragment()
    private var listPage:Fragment = ListFragment()
    private var currentFragment:Fragment = HomeFragment()
    private lateinit var bottomNavigationView:BottomNavigationView
    private var iso:String = "US"
    private var it:Int = R.id.nav_home
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigationView = findViewById(R.id.bottomBar)
        permissionCheck()
        createFragment()
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {
                    supportFragmentManager.beginTransaction().hide(currentFragment).show(
                        homePage
                    ).commit()
                    currentFragment = homePage
                    true
                }
                R.id.nav_world -> {
                    supportFragmentManager.beginTransaction().hide(currentFragment).show(
                        worldPage
                    ).commit()
                    currentFragment = worldPage
                    true
                }
                R.id.nav_list -> {
                    supportFragmentManager.beginTransaction().hide(currentFragment).show(
                        listPage
                    ).commit()
                    currentFragment = listPage
                    true
                }
                else -> false
            }
        }
    }
    private fun permissionCheck(){
        mLocationManager = this.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this, arrayOf<String?>(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.INTERNET
                ), 1
            )
        } else {
               val location=  mLocationManager!!.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            if(location != null) {getLocation(location)}else {
                mLocationManager!!.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    100,
                    0f,
                    this
                )
            }
        }
    }
    fun countryISO(): String { return iso }
    private fun createFragment(){
        supportFragmentManager.beginTransaction().apply {
            add(R.id.frame1, homePage).hide(homePage)
            add(R.id.frame1, worldPage).hide(worldPage)
            add(R.id.frame1, listPage).hide(listPage)
        }.commit()
        bottomNavigationView.selectedItemId = it.let { supportFragmentManager.beginTransaction().show(
            homePage
        ).commit() }
    }
    private fun refreshFragment(){
        supportFragmentManager.beginTransaction().detach(homePage).attach(homePage).hide(homePage).commitAllowingStateLoss();
        supportFragmentManager.beginTransaction().detach(worldPage).attach(worldPage).hide(worldPage).commitAllowingStateLoss();
        supportFragmentManager.beginTransaction().detach(listPage).attach(listPage).hide(listPage).commitAllowingStateLoss();
        bottomNavigationView.selectedItemId = it.let { supportFragmentManager.beginTransaction().show(homePage).commitAllowingStateLoss();}
    }
    private fun dialogDetectLocation(){
        val mGPSDialog:Dialog
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.gpsTitle)
        builder.setMessage(R.string.gpsMessage)
        builder.setPositiveButton(R.string.gpsConfirm) { _, _ -> startActivityForResult(
            Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS), 0) }.setNegativeButton(R.string.gpsCancel) { _, _ -> }
        mGPSDialog = builder.create()
        mGPSDialog.show()
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(requestCode == 1){
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                permissionCheck()
                supportFragmentManager.beginTransaction().detach(homePage).attach(homePage).hide(homePage).commit()
                supportFragmentManager.beginTransaction().detach(worldPage).attach(worldPage).hide(worldPage).commit()
                supportFragmentManager.beginTransaction().detach(listPage).attach(listPage).hide(listPage).commit()
                bottomNavigationView.selectedItemId = it.let { supportFragmentManager.beginTransaction().show(
                    homePage
                ).commit() }
            }else{
                dialogDetectLocation()
            }
        }
    }
    private fun getIso2(latitude:Double,longitude:Double){
        val geoCoder = Geocoder(this, Locale.UK)
        try {
            val addresses: List<Address> = geoCoder.getFromLocation(latitude, longitude, 1)
            val obj = addresses[0]
            iso = obj.countryCode
            Log.d("TAG","main : $iso")
            if(checkRefresh) {
                checkRefresh = false
                refreshFragment()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
    private fun getLocation(location: Location){ getIso2(location.latitude,location.longitude) }
    override fun onLocationChanged(p0: Location) { getIso2(p0.latitude,p0.longitude) }
    override fun onProviderEnabled(provider: String) {
        Log.d("TAG","main : EnableCheck")
        checkRefresh = true
        permissionCheck()
    }
    override fun onProviderDisabled(provider: String) {
        if (provider == LocationManager.GPS_PROVIDER) {
            Log.d("TAG","main : DisableCheck")
            dialogDetectLocation()
        }
    }
}