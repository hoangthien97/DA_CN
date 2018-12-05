package com.thienthien97.thstore.View.TrangChu.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;
import com.thienthien97.thstore.Adapter.DSKhuyenMaiAdapter;
import com.thienthien97.thstore.Model.ObjectClass.KhuyenMai;
import com.thienthien97.thstore.Presenter.KhuyenMai.PresenterLogicKhuyenMai;
import com.thienthien97.thstore.R;
import com.thienthien97.thstore.View.TrangChu.ViewKhuyenMai;

import java.util.List;

public class CTKMFragment extends Fragment implements ViewKhuyenMai{

    View view;
    LinearLayout lnHinhKM;
    RecyclerView recyclerViewDSKM;
    PresenterLogicKhuyenMai presenterLogicKhuyenMai;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_ctkm,container,false);

        lnHinhKM = view.findViewById(R.id.lnHinhKM);
        recyclerViewDSKM = view.findViewById(R.id.recyclerDSKhuyenMai);

        presenterLogicKhuyenMai = new PresenterLogicKhuyenMai(this);
        presenterLogicKhuyenMai.LayDSKhuyenMai();

        return view;
    }

    @Override
    public void HienThiDSKhuyenMai(List<KhuyenMai> khuyenMais) {

        int demhinh = khuyenMais.size();
        if(demhinh > 5){
            demhinh = 5;
        }else {
            demhinh = khuyenMais.size();
        }

        lnHinhKM.removeAllViews();
        for (int i = 0 ;i<demhinh ;i++){

            ImageView imageView = new ImageView(getContext());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,450);
            layoutParams.setMargins(0,10,0,10);
            imageView.setLayoutParams(layoutParams);

            Picasso.with(getContext()).load(khuyenMais.get(i).getHINHKHUYENMAI()).resize(900,450)
                    .placeholder(R.drawable.noimage)
                    .into(imageView);

            lnHinhKM.addView(imageView);
        }

        DSKhuyenMaiAdapter adapterDanhSachKhuyenMai = new DSKhuyenMaiAdapter(getContext(),khuyenMais);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerViewDSKM.setLayoutManager(layoutManager);
        recyclerViewDSKM.setAdapter(adapterDanhSachKhuyenMai);
        adapterDanhSachKhuyenMai.notifyDataSetChanged();
    }
}
