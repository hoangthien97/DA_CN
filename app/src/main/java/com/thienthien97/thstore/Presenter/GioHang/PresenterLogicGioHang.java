package com.thienthien97.thstore.Presenter.GioHang;

import android.content.Context;

import com.thienthien97.thstore.Model.GioHang.ModelGiohang;
import com.thienthien97.thstore.Model.ObjectClass.SanPham;
import com.thienthien97.thstore.View.GioHang.ViewGioHang;

import java.util.List;

public class PresenterLogicGioHang implements iPresenterGioHang {

    ModelGiohang modelGiohang;
    ViewGioHang viewGioHang;

    public PresenterLogicGioHang(ViewGioHang viewGioHang, Context context){
        modelGiohang = new ModelGiohang();
        this.viewGioHang = viewGioHang;
        modelGiohang.MoKetNoiDB(context);
    }

    @Override
    public void LayDSSPTrongGioHang() {
        List<SanPham> sanPhamList = modelGiohang.LayDSSPGioHang();

        if(sanPhamList.size() > 0){
            viewGioHang.HienThiDSSPTrongGioHang(sanPhamList);
        }
    }

}
