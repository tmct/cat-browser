package com.example.com.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.atomic.AtomicInteger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class MainFragment extends Fragment {

    private static final int NUM_CATS = 10;
    private static final String CAT_IMAGE_URL = "http://thecatapi.com/api/images/get";
    private Unbinder unbinder;

    @BindView(R.id.catGridView)
    GridView catGrid;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_main, container, false);
        unbinder = ButterKnife.bind(this, view);
        downloadAndShowCatImages();
        return view;
    }

    private void downloadAndShowCatImages() {
        final Bitmap[] bitmaps = new Bitmap[NUM_CATS];
        final AtomicInteger bitmapsToDownload = new AtomicInteger(NUM_CATS);
        for (int i = 0; i < NUM_CATS; i++) {
            final int j = i;
            new AsyncTask<String, Void, Bitmap>() {
                protected Bitmap doInBackground(String... urls) {
                    String url = urls[0];
                    Bitmap bitmap = null;
                    try {
                        final InputStream in = new URL(url).openStream();
                        bitmap = BitmapFactory.decodeStream(in);
                    } catch (Exception e) {
                        Log.e("Error", e.getMessage());
                        e.printStackTrace();
                    }
                    return bitmap;
                }

                protected void onPostExecute(Bitmap result) {
                    bitmaps[j] = result;
                    if (bitmapsToDownload.decrementAndGet() == 0) {
                        showCatImages(bitmaps);
                    }
                }
            }.execute(CAT_IMAGE_URL);
        }
    }

    private void showCatImages(Bitmap[] bitmaps) {
        catGrid.setAdapter(new BitmapArrayAdapter(getContext(), bitmaps));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
