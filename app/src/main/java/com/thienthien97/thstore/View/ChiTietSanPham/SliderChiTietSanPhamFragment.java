package com.thienthien97.thstore.View.ChiTietSanPham;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.thienthien97.thstore.R;

public class SliderChiTietSanPhamFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_slider_chitietsanpham, container, false);
        Bundle bundle = getArguments();
        String linkHinhsp = bundle.getString("linkHinhsp");

        ImageView imageView = view.findViewById(R.id.imgSlider);

        Picasso.with(getContext()).load(linkHinhsp).placeholder(R.drawable.noimage).into(imageView);

        return view;
    }
}
