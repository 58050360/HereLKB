package com.skyzhaneul.herelkb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static com.skyzhaneul.herelkb.MainActivity.EXTRA_DETAIL;
import static com.skyzhaneul.herelkb.MainActivity.EXTRA_NAME;
import static com.skyzhaneul.herelkb.MainActivity.EXTRA_URL;
import static com.skyzhaneul.herelkb.MainActivity.EXTRA_URL2;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra(EXTRA_URL);
        String locateName = intent.getStringExtra(EXTRA_NAME);
        String locateDetail = intent.getStringExtra(EXTRA_DETAIL);
        String imageUrl2 = intent.getStringExtra(EXTRA_URL2);
        ImageView imageView = findViewById(R.id.image_view_detail);
        TextView textView = findViewById(R.id.text_view_location);
        TextView textView1 = findViewById(R.id.text_view_locationdetail);
        ImageView imageView1 = findViewById(R.id.image_view_locate);

        Picasso.get().load(imageUrl).fit().centerInside().into(imageView);
        textView.setText(locateName);
        textView1.setText(locateDetail);
        Picasso.get().load(imageUrl2).fit().centerInside().into(imageView1);

    }
}
