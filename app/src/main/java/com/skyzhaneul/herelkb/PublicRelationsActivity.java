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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;


public class PublicRelationsActivity extends AppCompatActivity implements LocationListener {

    private static final int CHOSE_IMAGE = 101;
    String user;
    private boolean mLocationPermissionGranted = false;
    EditText editTextName, editTextAddress, editTextOpen, editTextTel, editTextDetail, editTextLatitude, editTextLongtitude;
    Spinner spinner;
    Button button_add, button_upload, button_addlalong;
    DatabaseReference database_Pr;
    ImageView imagepick;
    Uri uriImage;
    ProgressBar progressBar;
    public String imagename;
    FirebaseAuth mAuth;
    TextView textView, textView2;
    private LocationManager locationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pr);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("  ");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        user = getIntent().getExtras().getString("Email");

        database_Pr = FirebaseDatabase.getInstance().getReference("PrList");
        mAuth = FirebaseAuth.getInstance();
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextAddress = (EditText) findViewById(R.id.editTextAddress);
        editTextOpen = (EditText) findViewById(R.id.editTextOpen);
        editTextTel = (EditText) findViewById(R.id.editTextTel);
        editTextDetail = (EditText) findViewById(R.id.editTextDetail);
        editTextLatitude = (EditText) findViewById(R.id.editTextLatitude);
        editTextLongtitude = (EditText) findViewById(R.id.editTextLongtitude);
        spinner = (Spinner) findViewById(R.id.spinnerCategory);
        button_add = (Button) findViewById(R.id.button_add);
        button_upload = (Button) findViewById(R.id.button_upload);
        button_addlalong = (Button) findViewById(R.id.button_addlalong);
        imagepick = (ImageView) findViewById(R.id.image_logo);
        textView = (TextView) findViewById(R.id.txt_pr_image);
        textView2 = (TextView) findViewById(R.id.txt_pr_image2);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

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

        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPRlist();

            }
        });
        imagepick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showImageChoser();
            }
        });
        button_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { if (uriImage != null) {
                upLoadImageToFirebaseStorage(); } else{ Toast.makeText(PublicRelationsActivity.this,"No file selected",Toast.LENGTH_LONG).show();}
            }
        });
        button_addlalong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLocationChanged(location);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CHOSE_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            uriImage = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uriImage);
                imagepick.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
private String getFileExtension(Uri uri){
    ContentResolver cR = getContentResolver();
    MimeTypeMap mime = MimeTypeMap.getSingleton();
    return mime.getExtensionFromMimeType(cR.getType(uri));

}

    private void upLoadImageToFirebaseStorage() {
        String name = editTextName.getText().toString().trim();
        Date ts = Calendar.getInstance().getTime();
        String ts1 =  ts.toString();
        imagename = name+ts1 ;
        StorageReference imageRef =
                FirebaseStorage.getInstance().getReference("image/" + imagename  + "."+ getFileExtension(uriImage));

        if (uriImage != null) {
            progressBar.setVisibility(View.VISIBLE);
            imageRef.putFile(uriImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Task<Uri> result =  taskSnapshot.getMetadata().getReference().getDownloadUrl();
                    result.addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                         String  downloadLink = uri.toString();
                          textView.setText(Html.fromHtml(downloadLink+" Success"));
                          textView.setMovementMethod(LinkMovementMethod.getInstance());

                        }


                    });

                    Toast.makeText(PublicRelationsActivity.this,"Upload Succussful " + imagename ,Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(PublicRelationsActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }


    private void showImageChoser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Location Image"), CHOSE_IMAGE);

    }


    private void addPRlist() {
        String name = editTextName.getText().toString().trim();
        String category = spinner.getSelectedItem().toString();
        String address = editTextAddress.getText().toString().trim();
        String open = editTextOpen.getText().toString().trim();
        String tel = editTextTel.getText().toString().trim();
        String detail = editTextDetail.getText().toString().trim();
        String latitude = editTextLatitude.getText().toString().trim();
        String longtitude = editTextLongtitude.getText().toString().trim();
        String status = "New Request";
        Date ts = Calendar.getInstance().getTime();
        String ts1 =  ts.toString();
        String imagelogo = imagename+"logo.jpg";
        String image1 = imagename+"1.jpg";
        String image2 = imagename+"2.jpg";
        String image3 = imagename+"3.jpg";


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
        PrList prList = new PrList(id, name, category, address, open, tel, detail, latitude, longtitude, status,imagelogo,user,image1,image2,image3);
        database_Pr.child(ts1).setValue(prList);

        Toast.makeText(this, "PR list added", Toast.LENGTH_LONG).show();
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
                Intent intent = new Intent(PublicRelationsActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
            case R.id.menuHome:
                Intent intent1 = new Intent(PublicRelationsActivity.this, MainActivity.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent1.putExtra("Email", user);
                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent1);
                break;
            case android.R.id.home:
                finish();
                break;
        }

        return true;
    }


    @Override
    public void onLocationChanged(Location location) {
        double longtitude = location.getLongitude();
        double latitude = location.getLatitude();
       textView.setText("longtitude" + longtitude + "latitude" + latitude);
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

