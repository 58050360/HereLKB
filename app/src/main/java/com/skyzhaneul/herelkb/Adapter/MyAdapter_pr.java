package com.skyzhaneul.herelkb.Adapter;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.skyzhaneul.herelkb.CategoryItem;
import com.skyzhaneul.herelkb.PrList;
import com.skyzhaneul.herelkb.R;
import com.skyzhaneul.herelkb.ViewHolder.CategoryViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapter_pr extends RecyclerView.Adapter<MyAdapter_pr.MyAdapter_prViewHolder> {
    public Context c;
    public ArrayList<PrList> arrayList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void OnItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){

        mListener = listener;
    }

    public MyAdapter_pr(Context c, ArrayList<PrList> arrayList)
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
    public MyAdapter_prViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_pr_list,parent,false);
        return new MyAdapter_prViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyAdapter_prViewHolder holder, int position)
    {
        PrList prList = arrayList.get(position);
        holder.t1.setText(prList.getPr_Name());
        holder.t2.setText(prList.getPr_date());
        holder.t3.setText(prList.getPr_Status());
        holder.t4.setText(prList.getPr_Category());
        holder.t5.setText(prList.getPr_Open());
        holder.t6.setText(prList.getPr_Address());
        holder.t7.setText(prList.getPr_Tel());
        holder.t8.setText(prList.getPr_Detail());
        holder.t9.setText(prList.getPr_Latitude());
        holder.t10.setText(prList.getPr_Longtitude());
        holder.t11.setText(prList.getPr_user());

    }

    public class MyAdapter_prViewHolder extends RecyclerView.ViewHolder {
        public TextView t1,t3,t4,t5,t6,t7,t8,t9,t10,t11;
        public  TextView t2;


        public MyAdapter_prViewHolder(View itemView) {
            super(itemView);
            t1 = (TextView) itemView.findViewById(R.id.name);
            t2 = (TextView) itemView.findViewById(R.id.date);
            t3 = (TextView) itemView.findViewById(R.id.status);
            t4 = (TextView) itemView.findViewById(R.id.category);
            t5 = (TextView) itemView.findViewById(R.id.address);
            t6 = (TextView) itemView.findViewById(R.id.open);
            t7 = (TextView) itemView.findViewById(R.id.tel);
            t8 = (TextView) itemView.findViewById(R.id.detail);
            t9 = (TextView) itemView.findViewById(R.id.latitude);
            t10 = (TextView) itemView.findViewById(R.id.longtitude);
            t11 = (TextView) itemView.findViewById(R.id.user);
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
