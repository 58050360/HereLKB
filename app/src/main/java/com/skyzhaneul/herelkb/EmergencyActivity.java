package com.skyzhaneul.herelkb;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;

public class EmergencyActivity extends AppCompatActivity implements OnMapReadyCallback {
    public String data;
    GoogleMap map;
    private LocationManager locationManager;
    public double latitude,longtitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        data = getIntent().getExtras().getString("Email");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
        {   getSupportActionBar().setTitle("  ");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

//        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
//                != PackageManager.PERMISSION_GRANTED &&
//                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
//                        != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        final Location location = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
//        //onLocationChanged(location);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuLogout:
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(EmergencyActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
            case R.id.menuHome:
                Intent intent1 = new Intent(EmergencyActivity.this, MainActivity.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent1.putExtra("Email",data);
                startActivity(intent1);
                break;
            case android.R.id.home:
                finish();
                break;
            case R.id.menuProfile:
                Intent intent2 = new Intent(EmergencyActivity.this, ProfileActivity.class);
                intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent2.putExtra("Email", data);
                intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent2);
                break;
        }

        return true;
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        LatLng latkrabang_hospital1 = new LatLng(13.769215,100.732270);
        LatLng latkrabang_hospital2 = new LatLng(13.721765,100.707427);
        LatLng latkrabang_hospital3 = new LatLng(13.717718,100.706972);
        LatLng latkrabang_hospital4 = new LatLng(13.723004,100.715957);
        LatLng latkrabang_hospital5 = new LatLng(13.717286,100.717831);
        LatLng latkrabang_hospital6 = new LatLng(13.723329,100.722020);
        LatLng latkrabang_hospital7 = new LatLng(13.720756, 100.727437);
        LatLng latkrabang_hospital8 = new LatLng(13.721854, 100.784163);
        LatLng latkrabang_hospital9 = new LatLng(13.721643, 100.784183);
        LatLng latkrabang_hospital10 = new LatLng(13.755720, 100.798443);
        LatLng latkrabang_hospital11 = new LatLng(13.722488, 100.784134);
        LatLng latkrabang_hospital12 = new LatLng(13.729569, 100.777423);
        //LatLng latkrabang_hospital13 = new LatLng(latitude,longtitude);

        map.addMarker(new MarkerOptions().position(latkrabang_hospital1).title("สหคลินิกแพทย์เวชกรรมและแพทย์แผนไทย"));
        map.addMarker(new MarkerOptions().position(latkrabang_hospital2).title("สหคลินิกจุฬารัตน์ 7"));
        map.addMarker(new MarkerOptions().position(latkrabang_hospital3).title("โรงพยาบาล สิรินธร"));
        map.addMarker(new MarkerOptions().position(latkrabang_hospital4).title("คลินิกรักษ์สัตว์ มายด์แอคเคาท์"));
        map.addMarker(new MarkerOptions().position(latkrabang_hospital5).title("โรงพยาบาลบัวเกราะ"));
        map.addMarker(new MarkerOptions().position(latkrabang_hospital6).title("ศูนย์บริการสาธารณสุข 46 กันตารัติอุทิศ"));
        map.addMarker(new MarkerOptions().position(latkrabang_hospital7).title("เวสท์เทอรา คลินิก เดอะพาซิโอ ลาดกระบัง"));
        map.addMarker(new MarkerOptions().position(latkrabang_hospital8).title("รพ.จุฬารัตน์8"));
        map.addMarker(new MarkerOptions().position(latkrabang_hospital9).title("คลินิกเวชกรรมจุฬารัตน์ 8"));
        map.addMarker(new MarkerOptions().position(latkrabang_hospital10).title("โรงพยาบาล ลาดกระบังเมโมเรียล"));
        map.addMarker(new MarkerOptions().position(latkrabang_hospital11).title("โรงพยาบาล ลาดกระบัง"));
        map.addMarker(new MarkerOptions().position(latkrabang_hospital12).title("งานสุขภาพอนามัย"));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latkrabang_hospital7,12));
      //  map.addMarker(new MarkerOptions().position(latkrabang_hospital13).title("your locations")
             //   .snippet("and sniped").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        //map.moveCamera(CameraUpdateFactory.newLatLng(latkrabang_hospital1));
       // .snippet("and sniped").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))

    }
//    @Override
//    public void onLocationChanged(Location location) {
//         longtitude = location.getLongitude();
//         latitude = location.getLatitude();
//
//    }
//
//    @Override
//    public void onStatusChanged(String provider, int status, Bundle extras) {
//
//    }
//
//    @Override
//    public void onProviderEnabled(String provider) {
//
//    }
//
//    @Override
//    public void onProviderDisabled(String provider) {
//
  }

