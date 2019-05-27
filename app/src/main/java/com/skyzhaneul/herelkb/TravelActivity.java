package com.skyzhaneul.herelkb;


import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;

public class TravelActivity extends AppCompatActivity implements OnMapReadyCallback {
    public String data;
    GoogleMap map;
    private boolean mLocationPermissionGranted = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);
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
                Intent intent = new Intent(TravelActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
            case R.id.menuHome:
                Intent intent1 = new Intent(TravelActivity.this, MainActivity.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent1.putExtra("Email",data);
                startActivity(intent1);
                break;
            case android.R.id.home:
                finish();
                break;
            case R.id.menuProfile:
                Intent intent2 = new Intent(TravelActivity.this, ProfileActivity.class);
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
        LatLng latkrabang_travel1 = new LatLng(13.727760, 100.748382);
        LatLng latkrabang_travel2 = new LatLng(13.728260, 100.782868);
        LatLng latkrabang_travel3 = new LatLng(13.728251, 100.775544);
        LatLng latkrabang_travel4 = new LatLng(13.689971, 100.750116);
        LatLng latkrabang_travel5 = new LatLng(13.7273546,100.7482813);


        map.addMarker(new MarkerOptions().position(latkrabang_travel1).title("สถานีแอร์พอร์ต เรล ลิงค์ ลาดกระบัง"));
        map.addMarker(new MarkerOptions().position(latkrabang_travel2).title("สถานีหรถไฟหัวตะเข้"));
        map.addMarker(new MarkerOptions().position(latkrabang_travel3).title("ป้ายหยุดรถไฟพระจอมเกล้า"));
        map.addMarker(new MarkerOptions().position(latkrabang_travel4).title("ท่าอากาศยานสุวรรณภูมิ (BKK)"));
        map.addMarker(new MarkerOptions().position(latkrabang_travel5).title("สถานีรถไฟลาดกระบัง"));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latkrabang_travel5,12));

        //map.moveCamera(CameraUpdateFactory.newLatLng(latkrabang_hospital1));
    }
}
