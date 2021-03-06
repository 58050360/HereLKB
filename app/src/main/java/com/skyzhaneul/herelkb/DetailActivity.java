package com.skyzhaneul.herelkb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import static com.skyzhaneul.herelkb.MainActivity.EXTRA_ADDRESS;
import static com.skyzhaneul.herelkb.MainActivity.EXTRA_CATEGORY;
import static com.skyzhaneul.herelkb.MainActivity.EXTRA_DETAIL;
import static com.skyzhaneul.herelkb.MainActivity.EXTRA_LATITUDE;
import static com.skyzhaneul.herelkb.MainActivity.EXTRA_LONG;
import static com.skyzhaneul.herelkb.MainActivity.EXTRA_NAME;
import static com.skyzhaneul.herelkb.MainActivity.EXTRA_TEL;
import static com.skyzhaneul.herelkb.MainActivity.EXTRA_TIME;
import static com.skyzhaneul.herelkb.MainActivity.EXTRA_URL;
import static com.skyzhaneul.herelkb.MainActivity.EXTRA_URL2;
import static com.skyzhaneul.herelkb.MainActivity.EXTRA_URL3;
import static com.skyzhaneul.herelkb.MainActivity.EXTRA_URL4;

public class DetailActivity extends AppCompatActivity implements OnMapReadyCallback {
    public String data,name;
    GoogleMap map;
    public Double latitude,longtitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
        {   getSupportActionBar().setTitle("  ");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        data = getIntent().getExtras().getString("Email");
        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra(EXTRA_URL);
        String locateName = intent.getStringExtra(EXTRA_NAME);
        String locateDetail = intent.getStringExtra(EXTRA_DETAIL);
        String imageUrl2 = intent.getStringExtra(EXTRA_URL2);
        String locateAddress = intent.getStringExtra(EXTRA_ADDRESS);
        String locateTime = intent.getStringExtra(EXTRA_TIME);
        String locateTel = intent.getStringExtra(EXTRA_TEL);
        String imageUrl3 = intent.getStringExtra(EXTRA_URL3);
        String imageUrl4 = intent.getStringExtra(EXTRA_URL4);
        String locateCategory = intent.getStringExtra(EXTRA_CATEGORY);
        String locateLatitude = intent.getStringExtra(EXTRA_LATITUDE);
      String locateLong = intent.getStringExtra(EXTRA_LONG);
        ImageView imageView = findViewById(R.id.image_view_detail);
        TextView textView = findViewById(R.id.text_view_location);
        TextView textView1 = findViewById(R.id.text_view_locationdetail);
        ImageView imageView1 = findViewById(R.id.image_view_locate);
        TextView  textView2 = findViewById(R.id.text_view_locationAddress);
        TextView textView3 = findViewById(R.id.text_view_locationTime);
        TextView textView4 = findViewById(R.id.text_view_locationTel);
        ImageView imageView2 = findViewById(R.id.image_view_locate2);
        ImageView imageView3 = findViewById(R.id.image_view_locate3);
        TextView textView5 = findViewById(R.id.text_view_locationCategory);
        TextView textView6 = findViewById(R.id.text_view_locationLatitude);
        TextView textView7 = findViewById(R.id.text_view_locationLong);
        Picasso.get().load(imageUrl).fit().centerInside().into(imageView);
        textView.setText(locateName);
        textView1.setText(locateDetail);
        Picasso.get().load(imageUrl2).fit().centerInside().into(imageView1);
        textView2.setText(locateAddress);
        textView3.setText(locateTime);
        textView4.setText(locateTel);
        Picasso.get().load(imageUrl3).fit().centerInside().into(imageView2);
        Picasso.get().load(imageUrl4).fit().centerInside().into(imageView3);
        textView5.setText(locateCategory);
        //textView6.setText("Latitude :"+locateLatitude);
        //textView7.setText("Longtitude :"+locateLong);
        latitude = Double.valueOf(locateLatitude);
        longtitude = Double.valueOf(locateLong);
        textView6.setText("latitude :"+latitude);
        textView7.setText("longtitude :"+longtitude);
        name = locateName;
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

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
                Intent intent = new Intent(DetailActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
            case R.id.menuHome:
                Intent intent1 = new Intent(DetailActivity.this, MainActivity.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent1.putExtra("Email",data);
                startActivity(intent1);
                break;
            case android.R.id.home:
                finish();
                break;
            case R.id.menuProfile:
                Intent intent2 = new Intent(DetailActivity.this, ProfileActivity.class);
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
        LatLng location = new LatLng(latitude,longtitude);
        map.addMarker(new MarkerOptions().position(location).title(name));
        //map.moveCamera(CameraUpdateFactory.newLatLng(location));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(location,18));
        //map.animateCamera(CameraUpdateFactory.zoomIn());
    }
}