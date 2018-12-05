package com.thienthien97.thstore.View.GioHang;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.thienthien97.thstore.Adapter.GioHangAdapter;
import com.thienthien97.thstore.Model.ObjectClass.SanPham;
import com.thienthien97.thstore.Presenter.GioHang.PresenterLogicGioHang;
import com.thienthien97.thstore.R;
import com.thienthien97.thstore.View.ThanhToan.ThanhToanActivity;

import java.util.List;

public class GioHangActivity extends AppCompatActivity implements ViewGioHang{

    RecyclerView recyclerViewGioHang;
    PresenterLogicGioHang presenterLogicGioHang;
    Toolbar toolbarGH;
    Button btnMuaNgay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);

        toolbarGH = findViewById(R.id.toolbarGioHang);
        recyclerViewGioHang = findViewById(R.id.recyclerGioHang);
        btnMuaNgay = findViewById(R.id.giohang_btnMuaNgay);

        toolbarGH.setTitle("Giỏ hàng");
        setSupportActionBar(toolbarGH);

        presenterLogicGioHang = new PresenterLogicGioHang(this,this);
        presenterLogicGioHang.LayDSSPTrongGioHang();

        btnMuaNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentThanhToan = new Intent(GioHangActivity.this, ThanhToanActivity.class);
                startActivity(intentThanhToan);
            }
        });

    }

    @Override
    public void HienThiDSSPTrongGioHang(List<SanPham> sanPhamList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        GioHangAdapter gioHangAdapter = new GioHangAdapter(this, sanPhamList);

        recyclerViewGioHang.setLayoutManager(layoutManager);
        recyclerViewGioHang.setAdapter(gioHangAdapter);
    }

}
