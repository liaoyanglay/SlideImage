package com.example.dizzylay.popupmenu;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class FlipperAdapter extends BaseAdapter {

    private List<Drawable> drawableList = new ArrayList<>();

    public FlipperAdapter(List<Drawable> drawableList) {
        this.drawableList = drawableList;
    }

    @Override
    public int getCount() {
        return drawableList.size();
    }

    @Override
    public Object getItem(int position) {
        return drawableList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(parent.getContext());
        imageView.setImageDrawable(drawableList.get(position));
        return imageView;
    }

    public void setDrawableList(List<Drawable> drawableList) {
        if (drawableList != null) {
            this.drawableList = drawableList;
        }
    }

}
