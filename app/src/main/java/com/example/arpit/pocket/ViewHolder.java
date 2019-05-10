package com.example.arpit.pocket;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ViewHolder extends RecyclerView.ViewHolder {

    View mView;

    public ViewHolder(View itemView) {
        super(itemView);

        mView = itemView;
    }

    public void setDetails(Context ctx, String image, String name, String purchase_date, String return_date, String description){
        ImageView img = (ImageView) mView.findViewById(R.id.riv_image);
        TextView tv_name = (TextView) mView.findViewById(R.id.text_title);
        TextView tv_purchase = (TextView) mView.findViewById(R.id.text_purchase);

        tv_name.setText(name);
        tv_purchase.setText(purchase_date);
        Picasso.with(ctx).load(image).into(img);

    }

}
