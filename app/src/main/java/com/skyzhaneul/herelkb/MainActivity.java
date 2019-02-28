package com.skyzhaneul.herelkb;

import android.app.DownloadManager;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
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
import android.widget.GridLayout;
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

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    FirebaseRecyclerOptions<CategoryItem> options;
    FirebaseRecyclerAdapter<CategoryItem, CategoryViewHolder> adapter;
    EditText editText;
    ArrayList<CategoryItem> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        arrayList = new ArrayList<>();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recycleview);
        editText = (EditText) findViewById(R.id.editText);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty()) {
                    search(s.toString());
                } else {
                    search("");
                }
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
                holder.t1.setText(model.getName());
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

    private void search(String s) {
        Query query = databaseReference.orderByChild("name")
                .startAt(s).endAt(s + "\uf8ff");
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


                }
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
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
            case R.id.menuHome:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }

        return true;
    }
}
