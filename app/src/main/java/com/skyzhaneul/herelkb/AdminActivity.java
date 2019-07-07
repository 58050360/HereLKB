package com.skyzhaneul.herelkb;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
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
import com.skyzhaneul.herelkb.Adapter.MyAdapter_pr;
import com.skyzhaneul.herelkb.ViewHolder.CategoryViewHolder;
import com.skyzhaneul.herelkb.ViewHolder.PrListViewHolder;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdminActivity extends AppCompatActivity implements MyAdapter_pr.OnItemClickListener {
    public  static final String EXTRA_NAMEPR = "pr_name";
    public  static final String EXTRA_TELPR = "pr_tel";
    public  static final String EXTRA_DETAILPR = "locateDetail";
    public  static final String EXTRA_ADDRESSPR = "pr_address";
    public static final String EXTRA_TIMEPR = "pr_time";
    public static final String EXTRA_CATEGORYPR = "pr_category";
    public static final String EXTRA_LATITUDEPR = "pr_latitude";
    public static final String EXTRA_LONGTITUDEPR = "pr_longtitude";
    public static final String EXTRA_DATEPR = "pr_date";
    public static final String EXTRA_STATUSPR = "pr_status";
    public static final String EXTRA_USERPR = "pr_user";

    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    FirebaseRecyclerOptions<PrList> options;
    FirebaseRecyclerAdapter<PrList, PrListViewHolder> adapter;
    ArrayList<PrList> arrayList;
    Button button_prlist,button_addlist,button_history;
    String data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        arrayList = new ArrayList<>();
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recycleview);
        button_prlist = (Button) findViewById(R.id.button_prlist);
        button_addlist = (Button) findViewById(R.id.button_approvelist);
        button_history = (Button)findViewById(R.id.button_history);

        button_prlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchPr();
            }
        });

        button_addlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchAdd();
            }
        });

        button_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchHistory();
            }
        });

        recyclerView.setHasFixedSize(true);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("PrList");
        options = new FirebaseRecyclerOptions.Builder<PrList>()
                .setQuery(databaseReference, PrList.class).build();
        adapter = new FirebaseRecyclerAdapter<PrList, PrListViewHolder>(options) {
            @Override
            protected void onBindViewHolder(PrListViewHolder holder, int position, PrList model) {


                holder.t1.setText(model.getPr_Name());
                holder.t2.setText(model.getPr_date());
                holder.t3.setText(model.getPr_Status());
                holder.t4.setText(model.getPr_Category());
                holder.t5.setText(model.getPr_Open());
                holder.t6.setText(model.getPr_Address());
                holder.t7.setText(model.getPr_Tel());
                holder.t8.setText(model.getPr_Detail());
                holder.t9.setText(model.getPr_Latitude());
                holder.t10.setText(model.getPr_Longtitude());
                holder.t11.setText(model.getPr_user());

            }

            @Override
            public PrListViewHolder onCreateViewHolder(ViewGroup parent, int i) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_pr_list, parent, false);


                return new PrListViewHolder(view);
            }
        };

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter.startListening();
        recyclerView.setAdapter(adapter);


    }

      private void searchPr() {
        Query query = databaseReference.orderByChild("pr_Status").equalTo("New Request");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    arrayList.clear();
                    for (DataSnapshot dss : dataSnapshot.getChildren()) {
                        final PrList prList = dss.getValue(PrList.class);
                        arrayList.add(prList);
                    }

                    MyAdapter_pr myAdapter_pr = new MyAdapter_pr(getApplicationContext(),arrayList);
                    recyclerView.setAdapter(myAdapter_pr);
                    myAdapter_pr.notifyDataSetChanged();
                    recyclerView.setVisibility(View.VISIBLE);
                    myAdapter_pr.setOnItemClickListener(AdminActivity.this);

                } else { recyclerView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void searchAdd() {
        Query query = databaseReference.orderByChild("pr_Status").equalTo("Approved");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    arrayList.clear();
                    for (DataSnapshot dss : dataSnapshot.getChildren()) {
                        final PrList prList = dss.getValue(PrList.class);
                        arrayList.add(prList);
                    }

                    MyAdapter_pr myAdapter_pr = new MyAdapter_pr(getApplicationContext(),arrayList);
                    recyclerView.setAdapter(myAdapter_pr);
                    myAdapter_pr.notifyDataSetChanged();
                    recyclerView.setVisibility(View.VISIBLE);
                    myAdapter_pr.setOnItemClickListener(AdminActivity.this);

                } else { recyclerView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void searchHistory() {
        Query query = databaseReference.orderByChild("pr_Status").equalTo("Added");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    arrayList.clear();
                    for (DataSnapshot dss : dataSnapshot.getChildren()) {
                        final PrList prList = dss.getValue(PrList.class);
                        arrayList.add(prList);
                    }

                    MyAdapter_pr myAdapter_pr = new MyAdapter_pr(getApplicationContext(),arrayList);
                    recyclerView.setAdapter(myAdapter_pr);
                    myAdapter_pr.notifyDataSetChanged();
                    recyclerView.setVisibility(View.VISIBLE);
                    myAdapter_pr.setOnItemClickListener(AdminActivity.this);

                } else { recyclerView.setVisibility(View.GONE);
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
                Intent intent = new Intent(AdminActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
            case R.id.menuHome:
                Intent intent1 = new Intent(AdminActivity.this, AdminActivity.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent1);
                break;
            case R.id.menuProfile:
//                Intent intent2 = new Intent(AdminActivity.this, AdminActivity.class);
//                intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                startActivity(intent2);
                break;
        }

        return true;
    }

    @Override
    public void OnItemClick(int position) {
        Intent detailIntent = new Intent(this,AdEditDeleteActivity.class);
        PrList clickItem = arrayList.get(position);
        detailIntent.putExtra(EXTRA_NAMEPR,clickItem.getPr_Name());
        detailIntent.putExtra(EXTRA_STATUSPR,clickItem.getPr_Status());
        detailIntent.putExtra(EXTRA_DETAILPR,clickItem.getPr_Detail());
        detailIntent.putExtra(EXTRA_DATEPR,clickItem.getPr_date());
        detailIntent.putExtra(EXTRA_ADDRESSPR,clickItem.getPr_Address());
        detailIntent.putExtra(EXTRA_TIMEPR,clickItem.getPr_Open());
        detailIntent.putExtra(EXTRA_TELPR,clickItem.getPr_Tel());
        detailIntent.putExtra(EXTRA_LATITUDEPR,clickItem.getPr_Latitude());
        detailIntent.putExtra(EXTRA_LONGTITUDEPR,clickItem.getPr_Longtitude());
        detailIntent.putExtra(EXTRA_CATEGORYPR,clickItem.getPr_Category());
        detailIntent.putExtra(EXTRA_USERPR,clickItem.getPr_user());

        startActivity(detailIntent);
    }
}
