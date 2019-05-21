package com.skyzhaneul.herelkb.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.skyzhaneul.herelkb.R;

public class PrListViewHolder extends RecyclerView.ViewHolder{

    public TextView t1;
    public TextView t2;
    public TextView t3;
    public TextView t4;
    public TextView t5;
    public TextView t6;
    public TextView t7;
    public TextView t8;
    public TextView t9;
    public TextView t10;
    public TextView t11;


    public PrListViewHolder(View itemView) {
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
    }
}
