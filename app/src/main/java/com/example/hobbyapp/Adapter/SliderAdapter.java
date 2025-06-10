package com.example.hobbyapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.hobbyapp.R;
import com.example.hobbyapp.Domain.SliderModel;

import java.util.ArrayList;

public class SliderAdapter extends PagerAdapter {

    private final Context context;
    private final ArrayList<SliderModel> sliderModalArrayList;

    public SliderAdapter(Context context, ArrayList<SliderModel> sliderModalArrayList) {
        this.context = context;
        this.sliderModalArrayList = sliderModalArrayList;
    }

    @Override
    public int getCount() {
        return sliderModalArrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.slider_layout, container, false);

        ImageView imageView = view.findViewById(R.id.idIV);
        TextView titleTV = view.findViewById(R.id.idTVtitle);
        TextView headingTV = view.findViewById(R.id.idTVheading);

        SliderModel model = sliderModalArrayList.get(position);

        titleTV.setText(model.getTitle());
        headingTV.setText(model.getHeading());
        imageView.setImageResource(model.getImageResId()); // 👈 локальное изображение

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
