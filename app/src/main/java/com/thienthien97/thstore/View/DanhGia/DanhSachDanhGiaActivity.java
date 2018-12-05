package com.thienthien97.thstore.View.DanhGia;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.thienthien97.thstore.Adapter.DanhGiaAdapter;
import com.thienthien97.thstore.Model.ObjectClass.DanhGia;
import com.thienthien97.thstore.Model.ObjectClass.LoadMoreScroll;
import com.thienthien97.thstore.Model.ObjectClass.iLoadMore;
import com.thienthien97.thstore.Presenter.DanhGia.PresenterLogicDanhGia;
import com.thienthien97.thstore.R;

import java.util.ArrayList;
import java.util.List;

public class DanhSachDanhGiaActivity extends AppCompatActivity implements ViewDanhGia, iLoadMore {

    RecyclerView recyclerViewDSDanhGia;
    ProgressBar progressBar;
    int masp = 0;
    PresenterLogicDanhGia presenterLogicDanhGia;
    List<DanhGia> dsDanhGia;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_danh_gia);

        recyclerViewDSDanhGia = findViewById(R.id.recyclerDanhSachDanhGia);
        progressBar = findViewById(R.id.dsdg_progress_bar);

        masp = getIntent().getIntExtra("masp",0);

        dsDanhGia = new ArrayList<>();
        presenterLogicDanhGia = new PresenterLogicDanhGia(this);
        presenterLogicDanhGia.LayDanhSachDanhGiaTheoSP(masp,0,progressBar);

    }

    @Override
    public void DanhGiaThanhCong() {

    }


    @Override
    public void DanhGiaThatBai() {

    }

    @Override
    public void HienThiDSDanhGiaSP(List<DanhGia> danhGiaList) {
        dsDanhGia.addAll(danhGiaList);

        DanhGiaAdapter adapterDanhGia = new DanhGiaAdapter(this, dsDanhGia, 0);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewDSDanhGia.setLayoutManager(layoutManager);
        recyclerViewDSDanhGia.addOnScrollListener(new LoadMoreScroll(layoutManager, this));
        recyclerViewDSDanhGia.setAdapter(adapterDanhGia);

        adapterDanhGia.notifyDataSetChanged();

        progressBar.setVisibility(View.INVISIBLE);

    }

    @Override
    public void LoadMore(int tongitem) {
        presenterLogicDanhGia.LayDanhSachDanhGiaTheoSP(masp, tongitem, progressBar);
    }
}
