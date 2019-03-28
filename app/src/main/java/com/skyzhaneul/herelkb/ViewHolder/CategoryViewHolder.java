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

    public CategoryViewHolder(View itemView) {
        super(itemView);
        t1 = (TextView) itemView.findViewById(R.id.name);
        i1 = (ImageView) itemView.findViewById(R.id.image);
        t2 = (TextView) itemView.findViewById(R.id.detail);
        i2 = (ImageView) itemView.findViewById(R.id.image2);
    }
}
