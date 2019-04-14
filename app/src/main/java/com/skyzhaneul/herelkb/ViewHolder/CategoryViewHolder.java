package com.skyzhaneul.herelkb.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.skyzhaneul.herelkb.R;

public class CategoryViewHolder extends RecyclerView.ViewHolder {
    public TextView t1;
    public ImageView i1;
    public TextView t2;
    public ImageView i2;
    public TextView t3;
    public  TextView t4;
    public TextView t5;
    public ImageView i3;
    public ImageView i4;
    public TextView t6;

    public CategoryViewHolder(View itemView) {
        super(itemView);
        t1 = (TextView) itemView.findViewById(R.id.name);
        i1 = (ImageView) itemView.findViewById(R.id.image);
        t2 = (TextView) itemView.findViewById(R.id.detail);
        i2 = (ImageView) itemView.findViewById(R.id.image2);
        t3 = (TextView) itemView.findViewById(R.id.locateaddress);
        t4 = (TextView) itemView.findViewById(R.id.locatetime);
        t5 = (TextView) itemView.findViewById(R.id.locatetel);
        i3 = (ImageView) itemView.findViewById(R.id.image3);
        i4 = (ImageView) itemView.findViewById(R.id.image4);
        t6 = (TextView) itemView.findViewById(R.id.locatecategory);
    }
}
