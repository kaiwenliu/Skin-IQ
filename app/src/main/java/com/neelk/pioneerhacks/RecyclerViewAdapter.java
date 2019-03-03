package com.neelk.pioneerhacks;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/*
 * Copyright © 2019 Neel Kandlikar. All rights reserved.
 */


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    ArrayList<Disease> categories;
    private Context context;

    private OnItemClicked onClick;


    public interface OnItemClicked {
        void onItemClick(int position);
    }


    public RecyclerViewAdapter (Context context, ArrayList<Disease> categories) {
        this.categories = categories;
        this.context = context;
    }


    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(v);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder categoryViewHolder, final int i) {
        // categoryViewHolder.categoryIcon.setImageResource(categories.get(i).resId);
        //  Glide.with(context).load(categories.get(i).resId).into(categoryViewHolder.categoryIcon);
        Picasso.get().load(categories.get(i).getResId()).fit().into(categoryViewHolder.categoryIcon);
        categoryViewHolder.nameTextView.setText(categories.get(i).getName());
        categoryViewHolder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClick.onItemClick(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView categoryTextView;
        de.hdodenhof.circleimageview.CircleImageView categoryIcon;
        TextView nameTextView;
        View view;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.disease_cardView);
            categoryIcon = itemView.findViewById(R.id.cardViewImage);
            categoryTextView = itemView.findViewById(R.id.cardViewText);
            nameTextView = itemView.findViewById(R.id.nameTextView);


        }


    }

    public void setOnClick(OnItemClicked onClick) {
        this.onClick = onClick;
    }

}