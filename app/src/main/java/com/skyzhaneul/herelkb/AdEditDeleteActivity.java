package com.skyzhaneul.herelkb;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import static com.skyzhaneul.herelkb.ProfileActivity.EXTRA_ADDRESSPR;
import static com.skyzhaneul.herelkb.ProfileActivity.EXTRA_CATEGORYPR;
import static com.skyzhaneul.herelkb.ProfileActivity.EXTRA_DATEPR;
import static com.skyzhaneul.herelkb.ProfileActivity.EXTRA_DETAILPR;
import static com.skyzhaneul.herelkb.ProfileActivity.EXTRA_LATITUDEPR;
import static com.skyzhaneul.herelkb.ProfileActivity.EXTRA_LONGTITUDEPR;
import static com.skyzhaneul.herelkb.ProfileActivity.EXTRA_NAMEPR;
import static com.skyzhaneul.herelkb.ProfileActivity.EXTRA_STATUSPR;
import static com.skyzhaneul.herelkb.ProfileActivity.EXTRA_TELPR;
import static com.skyzhaneul.herelkb.ProfileActivity.EXTRA_TIMEPR;


public class AdEditDeleteActivity extends AppCompatActivity implements LocationListener {


    String user;
    private boolean mLocationPermissionGranted = false;
    EditText editTextName, editTextAddress, editTextOpen, editTextTel, editTextDetail, editTextLatitude, editTextLongtitude;
    Spinner spinner;
    Button button_approve,button_delete,button_addlalong,button_add;
    DatabaseReference database_Pr,database_Cate;
    ProgressBar progressBar;
    FirebaseAuth mAuth;
    TextView textView;
    private LocationManager locationManager;
    public String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_edit_delete);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("  ");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        user = getIntent().getExtras().getString("Email");

        mAuth = FirebaseAuth.getInstance();
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextAddress = (EditText) findViewById(R.id.editTextAddress);
        editTextOpen = (EditText) findViewById(R.id.editTextOpen);
        editTextTel = (EditText) findViewById(R.id.editTextTel);
        editTextDetail = (EditText) findViewById(R.id.editTextDetail);
        editTextLatitude = (EditText) findViewById(R.id.editTextLatitude);
        editTextLongtitude = (EditText) findViewById(R.id.editTextLongtitude);
        textView = (TextView)findViewById(R.id.txt_pr_status);
        spinner = (Spinner) findViewById(R.id.spinnerCategory);
        button_approve = (Button) findViewById(R.id.button_approve);
        button_delete = (Button) findViewById(R.id.button_delete);
        button_add = (Button) findViewById(R.id.button_add);
        button_addlalong = (Button) findViewById(R.id.button_addlalong);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        Intent intent = getIntent();
        String pr_name = intent.getStringExtra(EXTRA_NAMEPR);
        String pr_date = intent.getStringExtra(EXTRA_DATEPR);
        String pr_detail = intent.getStringExtra(EXTRA_DETAILPR);
        String pr_address = intent.getStringExtra(EXTRA_ADDRESSPR);
        String pr_time = intent.getStringExtra(EXTRA_TIMEPR);
        String pr_tel = intent.getStringExtra(EXTRA_TELPR);
        String pr_latitude = intent.getStringExtra(EXTRA_LATITUDEPR);
        String pr_longtitude = intent.getStringExtra(EXTRA_LONGTITUDEPR);
        String pr_category = intent.getStringExtra(EXTRA_CATEGORYPR);
        String pr_status = intent.getStringExtra(EXTRA_STATUSPR);

        editTextName.setText(pr_name);
        editTextAddress.setText(pr_address);
        editTextDetail.setText(pr_detail);
        editTextLatitude.setText(pr_latitude);
        editTextLongtitude.setText(pr_longtitude);
        editTextOpen.setText(pr_time);
        editTextTel.setText(pr_tel);
        textView.setText(pr_status);



        key = pr_date+" "+pr_status;
        database_Pr = FirebaseDatabase.getInstance().getReference("PrList");
        database_Cate = FirebaseDatabase.getInstance().getReference("CategoryBackground");


        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        final Location location = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
        button_approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                approvePRlist();
                finish();


            }
        });

        button_addlalong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLocationChanged(location);
            }
        });
        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletePRlist();
                finish();
            }
        });

        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPRlist();
                finish();
            }
        });
    }

    private void addPRlist(){
        String status = textView.getText().toString().trim();
        if (status.equals("Added")) {
            Toast.makeText(this, "PR list Already Added", Toast.LENGTH_LONG).show();
            return;
        } else if ( status.equals("Approved")) {
        String name = editTextName.getText().toString().trim();
        String category = spinner.getSelectedItem().toString();
        String address = editTextAddress.getText().toString().trim();
        String open = editTextOpen.getText().toString().trim();
        String tel = editTextTel.getText().toString().trim();
        String detail = editTextDetail.getText().toString().trim();
        String latitude = editTextLatitude.getText().toString().trim();
        String longtitude = editTextLongtitude.getText().toString().trim();
        status = "Added";
        Date ts = Calendar.getInstance().getTime();
        String ts1 =  ts.toString();
        String imagelink = "https://firebasestorage.googleapis.com/v0/b/herelkb.appspot.com/o/485x325.png?alt=media&token=5b5e6e52-7a84-40b4-bd55-43f149a3079a";
        String imagelink2 = "https://firebasestorage.googleapis.com/v0/b/herelkb.appspot.com/o/485x325.png?alt=media&token=5b5e6e52-7a84-40b4-bd55-43f149a3079a";
        String imagelink3 = "https://firebasestorage.googleapis.com/v0/b/herelkb.appspot.com/o/485x325.png?alt=media&token=5b5e6e52-7a84-40b4-bd55-43f149a3079a";
        String imagelink4 = "https://firebasestorage.googleapis.com/v0/b/herelkb.appspot.com/o/485x325.png?alt=media&token=5b5e6e52-7a84-40b4-bd55-43f149a3079a";

        if (name.isEmpty()) {
            editTextName.setError("Name is required");
            editTextName.requestFocus();
            return;
        }
        if (address.isEmpty()) {
            editTextAddress.setError("Address is required");
            editTextAddress.requestFocus();
            return;
        }
        if (open.isEmpty()) {
            editTextOpen.setError("OpenTime is required");
            editTextOpen.requestFocus();
            return;
        }
        if (tel.isEmpty()) {
            editTextTel.setError("Telephone Number is required");
            editTextTel.requestFocus();
            return;
        }
        if (detail.isEmpty()) {
            editTextDetail.setError("Location Detail is required");
            editTextDetail.requestFocus();
            return;
        }
        if (latitude.isEmpty()) {
            editTextLatitude.setError("Latitude is required");
            editTextLatitude.requestFocus();
            return;
        }
        if (longtitude.isEmpty()) {
            editTextLongtitude.setError("Longtitude is required");
            editTextLongtitude.requestFocus();
            return;
        }
        String id = database_Pr.push().getKey();
        String id_cate = database_Cate.push().getKey();
        CategoryItem categoryItem = new CategoryItem(name,imagelink,detail,imagelink2, address, open, tel,imagelink3,imagelink4, category, latitude, longtitude);
        String sent = ts1+" "+status+" "+name;
        database_Cate.child(sent).setValue(categoryItem);
        PrList prList = new PrList(name, category, address, open, tel, detail, latitude, longtitude, status,user,ts1);
        database_Pr.child(sent).setValue(prList);
        DatabaseReference remove_past_request = FirebaseDatabase.getInstance().getReference("PrList").child(key);
        remove_past_request.removeValue();
        Toast.makeText(this, "Success Added", Toast.LENGTH_LONG).show();}

        else
            Toast.makeText(this, "This public relation Status is Not Approved", Toast.LENGTH_LONG).show();
            return; }




    private void approvePRlist() {
        String status = textView.getText().toString().trim();
        if (status.equals("Added")) {
            Toast.makeText(this, "PR list Already Added", Toast.LENGTH_LONG).show();
            return;
        } else if (status.equals("Approved")) {
            Toast.makeText(this, "PR list Already Approved", Toast.LENGTH_LONG).show();
            return;
        } else {
        String name = editTextName.getText().toString().trim();
        String category = spinner.getSelectedItem().toString();
        String address = editTextAddress.getText().toString().trim();
        String open = editTextOpen.getText().toString().trim();
        String tel = editTextTel.getText().toString().trim();
        String detail = editTextDetail.getText().toString().trim();
        String latitude = editTextLatitude.getText().toString().trim();
        String longtitude = editTextLongtitude.getText().toString().trim();
        status = "Approved";
        Date ts = Calendar.getInstance().getTime();
        String ts1 = ts.toString();


        if (name.isEmpty()) {
            editTextName.setError("Name is required");
            editTextName.requestFocus();
            return;
        }
        if (address.isEmpty()) {
            editTextAddress.setError("Address is required");
            editTextAddress.requestFocus();
            return;
        }
        if (open.isEmpty()) {
            editTextOpen.setError("OpenTime is required");
            editTextOpen.requestFocus();
            return;
        }
        if (tel.isEmpty()) {
            editTextTel.setError("Telephone Number is required");
            editTextTel.requestFocus();
            return;
        }
        if (detail.isEmpty()) {
            editTextDetail.setError("Location Detail is required");
            editTextDetail.requestFocus();
            return;
        }
        if (latitude.isEmpty()) {
            editTextLatitude.setError("Latitude is required");
            editTextLatitude.requestFocus();
            return;
        }
        if (longtitude.isEmpty()) {
            editTextLongtitude.setError("Longtitude is required");
            editTextLongtitude.requestFocus();
            return;
        }
        String id = database_Pr.push().getKey();
        PrList prList = new PrList(name, category, address, open, tel, detail, latitude, longtitude, status, user, ts1);
        String sent = ts1 + " " + status;
        database_Pr.child(sent).setValue(prList);
        DatabaseReference remove_past_request = FirebaseDatabase.getInstance().getReference("PrList").child(key);
        remove_past_request.removeValue();
        Toast.makeText(this, "PR list Approving", Toast.LENGTH_LONG).show();

    } }

    private void deletePRlist() {
        String name = editTextName.getText().toString().trim();
        String category = spinner.getSelectedItem().toString();
        String address = editTextAddress.getText().toString().trim();
        String open =  editTextOpen.getText().toString().trim();
        String tel = editTextTel.getText().toString().trim();
        String detail = editTextDetail.getText().toString().trim();
        String latitude = editTextLatitude.getText().toString().trim();
        String longtitude = editTextLongtitude.getText().toString().trim();
        String status = "Deleting";
        Date ts = Calendar.getInstance().getTime();
        String ts1 =  ts.toString();



        if (name.isEmpty()) {
            editTextName.setError("Name is required");
            editTextName.requestFocus();
            return;
        }
        if (address.isEmpty()) {
            editTextAddress.setError("Address is required");
            editTextAddress.requestFocus();
            return;
        }
        if (open.isEmpty()) {
            editTextOpen.setError("OpenTime is required");
            editTextOpen.requestFocus();
            return;
        }
        if (tel.isEmpty()) {
            editTextTel.setError("Telephone Number is required");
            editTextTel.requestFocus();
            return;
        }
        if (detail.isEmpty()) {
            editTextDetail.setError("Location Detail is required");
            editTextDetail.requestFocus();
            return;
        }
        if (latitude.isEmpty()) {
            editTextLatitude.setError("Latitude is required");
            editTextLatitude.requestFocus();
            return;
        }
        if (longtitude.isEmpty()) {
            editTextLongtitude.setError("Longtitude is required");
            editTextLongtitude.requestFocus();
            return;
        }
        String id = database_Pr.push().getKey();
        PrList prList = new PrList(name, category, address, open, tel, detail, latitude, longtitude, status,user,ts1);
        String sent = ts1+" "+status;
        //database_Pr.child(sent).setValue(prList);
        DatabaseReference remove_past_request = FirebaseDatabase.getInstance().getReference("PrList").child(key);
        remove_past_request.removeValue();
        Toast.makeText(this, "PR list Deleting", Toast.LENGTH_LONG).show();
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
                Intent intent = new Intent(AdEditDeleteActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
            case R.id.menuHome:
                Intent intent1 = new Intent(AdEditDeleteActivity.this, AdminActivity.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent1.putExtra("Email", user);
                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent1);
                break;
            case android.R.id.home:
                finish();
                break;
            case R.id.menuProfile:
//                Intent intent2 = new Intent(AdEditDeleteActivity.this, ProfileActivity.class);
//                intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                intent2.putExtra("Email", user);
//                intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                startActivity(intent2);
                break;

        }

        return true;
    }


    @Override
    public void onLocationChanged(Location location) {
        double longtitude = location.getLongitude();
        double latitude = location.getLatitude();
        editTextLongtitude.setText("" + longtitude);
        editTextLatitude.setText(""+latitude);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
