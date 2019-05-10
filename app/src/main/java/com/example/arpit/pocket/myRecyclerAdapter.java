package com.example.arpit.pocket;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class myRecyclerAdapter extends RecyclerView.Adapter<myRecyclerAdapter.MyViewHolder> {

    Context context;
    ArrayList<Upload> up;

    public myRecyclerAdapter(Context c, ArrayList<Upload> u ){
        context  = c;
        up = u;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv_name.setText(up.get(position).getName());
        holder.tv_purchase.setText(up.get(position).getPurchaseDate());
        Picasso.with(context).load(up.get(position).getImageUrl()).into(holder.img);
        holder.tv_return.setText(up.get(position).getReturnDate());
        holder.mCurrentPosition = position;

    }

    @Override
    public int getItemCount() {
        return up.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView tv_name, tv_purchase, tv_return, tv_spinnerItem , tv_details ;
        int mCurrentPosition;

        public MyViewHolder(View itemView) {
            super(itemView);
             img = (ImageView) itemView.findViewById(R.id.riv_image);
             tv_name = ((TextView) itemView.findViewById(R.id.text_title));
             tv_purchase = ((TextView) itemView.findViewById(R.id.text_purchase));
             tv_return =  ((TextView) itemView.findViewById(R.id.text_return));
             tv_spinnerItem = (TextView) itemView.findViewById(R.id.spinner_item);
             tv_details = (TextView) itemView.findViewById(R.id.et_description);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DetailsActivity.class);
                    intent.putExtra("name",up.get(mCurrentPosition).getName());
                    intent.putExtra("url",up.get(mCurrentPosition).getImageUrl());
                    intent.putExtra("purchase",up.get(mCurrentPosition).getPurchaseDate());
                    intent.putExtra("return",up.get(mCurrentPosition).getReturnDate());
                    intent.putExtra("spinneritem",up.get(mCurrentPosition).getSpinnerItem());
                    intent.putExtra("descript",up.get(mCurrentPosition).getDescription());
                    context.startActivity(intent);

                }
            });
        }
    }
}
