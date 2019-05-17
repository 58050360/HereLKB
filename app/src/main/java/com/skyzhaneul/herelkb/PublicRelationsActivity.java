package com.skyzhaneul.herelkb;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;


public class PublicRelationsActivity extends AppCompatActivity {

    private static final int CHOSE_IMAGE = 101;
    String user;
    private boolean mLocationPermissionGranted = false;
    EditText editTextName, editTextAddress, editTextOpen, editTextTel, editTextDetail, editTextLatitude, editTextLongtitude;
    Spinner spinner;
    Button button_add;
    DatabaseReference database_Pr;
    ImageView image_logo, image_1, image_2, image_3;
    Uri uriImage;
    ProgressBar progressBar;
    public String imagename;
    FirebaseAuth mAuth;


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
        image_logo = (ImageView) findViewById(R.id.image_logo);
        image_1 = (ImageView) findViewById(R.id.image_1);
        image_2 = (ImageView) findViewById(R.id.image_2);
        image_3 = (ImageView) findViewById(R.id.image_3);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upLoadImageToFirebaseStorage();
                addPRlist();

            }
        });
        image_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showImageChoser();
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
                image_logo.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void upLoadImageToFirebaseStorage() {
        String name = editTextName.getText().toString().trim();
        imagename = name+"1" ;
        StorageReference imageRef =
                FirebaseStorage.getInstance().getReference("image/" + imagename  + ".jpg");

        if (uriImage != null) {
            progressBar.setVisibility(View.VISIBLE);
            imageRef.putFile(uriImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    progressBar.setVisibility(View.GONE);

                    String imagelogoUrl = taskSnapshot.getMetadata().getReference().getDownloadUrl().toString();
                    imagename = imagelogoUrl;
                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressBar.setVisibility(View.GONE);
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


}

