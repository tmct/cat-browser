package com.example.com.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

class BitmapArrayAdapter extends ArrayAdapter<Bitmap> {
    BitmapArrayAdapter(Context context, Bitmap[] bitmaps) {
        super(context, 0, bitmaps);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_image, parent, false);
        }
        final Bitmap bitmap = getItem(position);
        final ImageView imageView = (ImageView) convertView;
        imageView.setImageBitmap(bitmap);
        return convertView;
    }
}
