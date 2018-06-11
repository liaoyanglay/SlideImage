package com.example.dizzylay.popupmenu;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter {

    private List<Drawable> drawableList = new ArrayList<>();
    private ItemOnClickListener listener;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, parent, false);
        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.imageView.setImageDrawable(drawableList.get(position));
    }

    @Override
    public int getItemCount() {
        return drawableList.size();
    }

    public void setDrawableList(List<Drawable> drawableList) {
        if (drawableList != null) {
            this.drawableList = drawableList;
        }
    }

    public void setListener(ItemOnClickListener listener) {
        this.listener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        ViewHolder(View itemView, ItemOnClickListener listener) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            if (listener != null) {
                imageView.setOnClickListener(v -> listener.onItemClick(v, getAdapterPosition()));
            }
        }
    }

    public interface ItemOnClickListener {
        void onItemClick(View v, int index);
    }
}
