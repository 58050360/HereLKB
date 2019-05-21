package com.skyzhaneul.herelkb;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.skyzhaneul.herelkb.Adapter.MyAdapter;
import com.skyzhaneul.herelkb.ViewHolder.CategoryViewHolder;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FoodActivity extends AppCompatActivity implements MyAdapter.OnItemClickListener {
    public  static final String EXTRA_URL = "imageUrl";
    public  static final String EXTRA_NAME = "locateName";
    public  static final String EXTRA_DETAIL = "locateDetail";
    public  static final String EXTRA_URL2 = "imageUrl2";
    public  static final String EXTRA_ADDRESS = "locateAddress";
    public static final String EXTRA_TIME = "locateTime";
    public static final String EXTRA_TEL = "locateTel";
    public static final String EXTRA_URL3 = "imageUrl3";
    public static final String EXTRA_URL4 = "imageUrl4";
    public static final String EXTRA_CATEGORY = "locateCategory";

    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    FirebaseRecyclerOptions<CategoryItem> options;
    FirebaseRecyclerAdapter<CategoryItem, CategoryViewHolder> adapter;
    ArrayList<CategoryItem> arrayList;
    String data;
    ImageButton button_cafe,button_restaurants;
    private boolean mLocationPermissionGranted = false ;
    TextView menufood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        arrayList = new ArrayList<>();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
        {   getSupportActionBar().setTitle("  ");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        data = getIntent().getExtras().getString("Email");
        recyclerView = (RecyclerView) findViewById(R.id.recycleview_food);
        button_cafe = (ImageButton) findViewById(R.id.button_cafe);
        button_restaurants = (ImageButton) findViewById(R.id.button_restaurants);
        menufood = (TextView) findViewById(R.id.txt_menu);

        //คลิกปุ่ม แล้วค้นหา cafe
        button_cafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                searchcafe();
                menufood.setText("Cafe List");


            }
        });
        //คลิกปุ่ม แล้วค้นหา restaurants
        button_restaurants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                searchrestaurants();
                menufood.setText("Restaurants List");

            }
        });
        recyclerView.setHasFixedSize(true);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("CategoryBackground");
        options = new FirebaseRecyclerOptions.Builder<CategoryItem>()
                .setQuery(databaseReference, CategoryItem.class).build();
        adapter = new FirebaseRecyclerAdapter<CategoryItem, CategoryViewHolder>(options) {
            @Override
            protected void onBindViewHolder(CategoryViewHolder holder, int position, CategoryItem model) {
                Picasso.get().load(model.getImageLink()).into(holder.i1, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
                Picasso.get().load(model.getImageLink()).into(holder.i2, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
                holder.t1.setText(model.getName());
                holder.t2.setText(model.getDetail());
                holder.t3.setText(model.getLocateAddress());
                holder.t4.setText(model.getLocateTime());
                holder.t5.setText(model.getLocateTel());
                holder.t6.setText(model.getCategory());
                Picasso.get().load(model.getImageLink()).into(holder.i3, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
                Picasso.get().load(model.getImageLink()).into(holder.i4, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

            }

            @Override
            public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int i) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_category_item, parent, false);


                return new CategoryViewHolder(view);
            }
        };

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter.startListening();
        recyclerView.setAdapter(adapter);

    }

    private void searchcafe() {
        Query query = databaseReference.orderByChild("category").equalTo("cafe");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    arrayList.clear();
                    for (DataSnapshot dss : dataSnapshot.getChildren()) {
                        final CategoryItem categoryItem = dss.getValue(CategoryItem.class);
                        arrayList.add(categoryItem);
                    }

                    MyAdapter myAdapter = new MyAdapter(getApplicationContext(),arrayList);
                    recyclerView.setAdapter(myAdapter);
                    myAdapter.notifyDataSetChanged();
                    recyclerView.setVisibility(View.VISIBLE);
                    myAdapter.setOnItemClickListener(FoodActivity.this);

                } else { recyclerView.setVisibility(View.GONE); }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void searchrestaurants() {
        Query query = databaseReference.orderByChild("category").equalTo("restaurants");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    arrayList.clear();
                    for (DataSnapshot dss : dataSnapshot.getChildren()) {
                        final CategoryItem categoryItem = dss.getValue(CategoryItem.class);
                        arrayList.add(categoryItem);
                    }

                    MyAdapter myAdapter = new MyAdapter(getApplicationContext(),arrayList);
                    recyclerView.setAdapter(myAdapter);
                    myAdapter.notifyDataSetChanged();
                    recyclerView.setVisibility(View.VISIBLE);
                    myAdapter.setOnItemClickListener(FoodActivity.this);

                } else { recyclerView.setVisibility(View.GONE); }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (adapter != null)
            adapter.startListening();

    }

    @Override
    protected void onStop() {
        if (adapter != null)
            adapter.stopListening();
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (adapter != null)
            adapter.startListening();
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
                Intent intent = new Intent(FoodActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
            case R.id.menuHome:
                Intent intent1 = new Intent(FoodActivity.this, MainActivity.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent1.putExtra("Email",data);
                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent1);
                break;
            case android.R.id.home:
                finish();
                break;
            case R.id.menuProfile:
                Intent intent2 = new Intent(FoodActivity.this, ProfileActivity.class);
                intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent2.putExtra("Email", data);
                intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent2);
                break;
        }

        return true;
    }

    @Override
    public void OnItemClick(int position) {
        Intent detailIntent = new Intent(this,DetailActivity.class);
        CategoryItem clickItem = arrayList.get(position);
        detailIntent.putExtra(EXTRA_URL,clickItem.getImageLink());
        detailIntent.putExtra(EXTRA_NAME,clickItem.getName());
        detailIntent.putExtra(EXTRA_DETAIL,clickItem.getDetail());
        detailIntent.putExtra(EXTRA_URL2,clickItem.getImageLink2());
        detailIntent.putExtra(EXTRA_ADDRESS,clickItem.getLocateAddress());
        detailIntent.putExtra(EXTRA_TIME,clickItem.getLocateTime());
        detailIntent.putExtra(EXTRA_TEL,clickItem.getLocateTel());
        detailIntent.putExtra(EXTRA_URL3,clickItem.getImageLink3());
        detailIntent.putExtra(EXTRA_URL4,clickItem.getImageLink4());
        detailIntent.putExtra(EXTRA_CATEGORY,clickItem.getCategory());

        detailIntent.putExtra("Email",data);

        startActivity(detailIntent);
    }
}
