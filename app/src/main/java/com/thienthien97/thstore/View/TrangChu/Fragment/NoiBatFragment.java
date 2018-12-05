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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.squareup.picasso.Picasso;
import com.thienthien97.thstore.Adapter.DSKhuyenMaiAdapter;
import com.thienthien97.thstore.Model.ObjectClass.KhuyenMai;
import com.thienthien97.thstore.Presenter.KhuyenMai.PresenterLogicKhuyenMai;
import com.thienthien97.thstore.R;
import com.thienthien97.thstore.View.TrangChu.ViewKhuyenMai;

import java.util.ArrayList;
import java.util.List;

public class NoiBatFragment extends Fragment implements ViewKhuyenMai{
    View view;
    RecyclerView recyclerView;
    ViewFlipper viewFlipper;
    PresenterLogicKhuyenMai presenterLogicKhuyenMai;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_noi_bat,container,false);

        viewFlipper = view.findViewById(R.id.viewflipper);
        recyclerView = view.findViewById(R.id.recyclerNoiBat);

        ActionViewflipper();

        presenterLogicKhuyenMai = new PresenterLogicKhuyenMai(this);
        presenterLogicKhuyenMai.LayDSKhuyenMai();

        return view;
    }


    private void ActionViewflipper(){
        ArrayList<String> ads = new ArrayList<>();
        ads.add("https://luvhnq.dm.files.1drv.com/y4monqKt67v6n_NkN-pbUVkPHHxnxHD0Vv-jN7cQnVPZ1ADUVMpwh8NdQ5FNb9hGR_tMWg7ur_QAYom_43xpbwdMhRJV4JZOF5aimRgiR6r3zKYlrKRydC8hM7L-5VLdy_0RTDOj6YxiHUv4xQseUBz51D5w39VD5Wu5cVSExQE86Autz82W5GcXalJI5B0tenrTKBeh3XyTKBSi6df2wICFA?width=1200&height=418&cropmode=none");
        ads.add("https://luvgnq.dm.files.1drv.com/y4mYnT6sgK_ZTL8QQTj9MbHmAbbjKVMD6YNgxuQVMcubMdMID_xPQA3xoRZtKINbL7DHEN9QSelQbAHrfJ4TZing3loEn_hVo9_mcqnnOGTV-15T1NHji5OJpzC4RbzZ499jW_aHGBSs6Er69vcMjOyyJ-u5Y3bDdY-TPkGou9BkV-0-jIb0wEBhuhYc4dbWBy9CkVgJ-4MnBj7bj9VvkgYWA?width=1242&height=373&cropmode=none");
        ads.add("https://luvenq.dm.files.1drv.com/y4mbRaUCNj8wJZRfrcxG_LKIDK4q9DOvzbJ7CUnFM4zS6I_iVgsVuIKGv4A4nUTy9oJes40Rn9PedmPjm-LvnPEi1ig8DNpZtjmfqzrbSJ1hACteKOVRgo2xaH13smnE3soYRhYW-Wqw4P-e7vjx3HxlGUdQbDVJtbMfNB0LjAuXQpORWhrofd0i-kDR0CUjfaTckbcvldZ0fXubwYmVM1RYg?width=565&height=146&cropmode=none");
        ads.add("https://lpupnq.dm.files.1drv.com/y4mSipW4CygxericKlXc8gkcPF74tZBXmFCFJCZk7tRVe-ZWVcmLzs9GLkxQ4rPyCOfJeX2d8JtrQglztTwfyq5t1L0eAVwx19y3u7qKfIeI13xp32-A-BDPEjleqcU5h9zq5VNDtxPBizKrzoByF9KYSDojEdKe4zUdf3ZOF1ThSqZUakJuoixJQ9dXgcQoQ4H5AXs2KX_Y9P-ESXxpxvV2A?width=600&height=204&cropmode=none");

        for(int i=0; i<ads.size(); i++){
            ImageView imageView = new ImageView(getContext());
            Picasso.with(getContext()).load(ads.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        //Set time cho quang cao chay
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
        Animation anim_slide_in = AnimationUtils.loadAnimation(getContext(),R.anim.slide_r);
        Animation anim_slide_out = AnimationUtils.loadAnimation(getContext(),R.anim.slide_out_r);
        viewFlipper.setInAnimation(anim_slide_in);
        viewFlipper.setOutAnimation(anim_slide_out);
    }

    @Override
    public void HienThiDSKhuyenMai(List<KhuyenMai> khuyenMais) {
        DSKhuyenMaiAdapter adapterDanhSachKhuyenMai = new DSKhuyenMaiAdapter(getContext(),khuyenMais);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterDanhSachKhuyenMai);
        adapterDanhSachKhuyenMai.notifyDataSetChanged();
    }
}
