package com.skyzhaneul.herelkb.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.skyzhaneul.herelkb.CategoryItem;
import com.skyzhaneul.herelkb.R;
import com.skyzhaneul.herelkb.ViewHolder.CategoryViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyAdapterViewHolder> {
    public Context c;
    public ArrayList<CategoryItem> arrayList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void OnItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){

        mListener = listener;
    }

    public MyAdapter(Context c, ArrayList<CategoryItem> arrayList)
    {
        this.c = c;
        this.arrayList = arrayList;
    }

    @Override
    public int getItemCount()
    {
        return arrayList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public MyAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_category_item,parent,false);
        return new MyAdapterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyAdapterViewHolder holder, int position)
    {
      CategoryItem categoryItem = arrayList.get(position);
      holder.t1.setText(categoryItem.getName());
        Picasso.get().load(categoryItem.getImageLink()).into(holder.i1);
        holder.t2.setText(categoryItem.getDetail());
        Picasso.get().load(categoryItem.getImageLink2()).into(holder.i2);
        holder.t3.setText(categoryItem.getLocateAddress());
        holder.t4.setText(categoryItem.getLocateTime());

    }

    public class MyAdapterViewHolder extends RecyclerView.ViewHolder {
        public TextView t1;
        public ImageView i1;
        public  TextView t2;
        public ImageView i2;
        public TextView t3;
        public TextView t4;

        public MyAdapterViewHolder(View itemView) {
            super(itemView);
            t1 = (TextView) itemView.findViewById(R.id.name);
            i1 = (ImageView) itemView.findViewById(R.id.image);
            t2 = (TextView) itemView.findViewById(R.id.detail);
            i2 = (ImageView) itemView.findViewById(R.id.image2);
            t3 = (TextView) itemView.findViewById(R.id.locateaddress);
            t4 = (TextView) itemView.findViewById(R.id.locatetime);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener != null){

                        int position = getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            mListener.OnItemClick(position);
                        }
                    }
                }
            });
        }
    }

}
