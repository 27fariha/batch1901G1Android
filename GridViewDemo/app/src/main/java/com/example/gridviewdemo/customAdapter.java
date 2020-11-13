package com.example.gridviewdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class customAdapter extends BaseAdapter {
    Context context;
    int[] img_index;
    LayoutInflater inflater;

    public customAdapter(Context context, int[] img_index) {
        this.context = context;
        this.img_index = img_index;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return img_index.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override           //position
    public View getView(int i, View view, ViewGroup viewGroup) {
        view=inflater.inflate(R.layout.grid_layout,null);
        ImageView img=(ImageView)view.findViewById(R.id.imageView);
        img.setImageResource(img_index[i]);
        return view;
    }
}
