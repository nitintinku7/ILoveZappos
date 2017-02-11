package com.nitin.ilovezappos;

import android.databinding.BindingAdapter;
import android.graphics.Paint;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class CustomBindingAdapter {
    @BindingAdapter({"bind:thumbnailImageUrl"})
    public static void loadImage(ImageView imageView, String url) {
        Picasso.with(imageView.getContext()).load(url).resize(200, 200).into(imageView);
    }

    @BindingAdapter({"bind:percentOff"})
    public static void percentage(TextView textView, String url) {
        textView.setText(url + " off");
    }

    @BindingAdapter({"bind:originalPrice"})
    public static void strike(TextView textView, String url) {
        textView.setPaintFlags(textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        textView.setText(url);
    }

}