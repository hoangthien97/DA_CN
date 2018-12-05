package com.thienthien97.thstore.Presenter.DanhGia;

import android.view.View;
import android.widget.ProgressBar;

import com.thienthien97.thstore.Model.DanhGia.ModelDanhGia;
import com.thienthien97.thstore.Model.ObjectClass.DanhGia;
import com.thienthien97.thstore.View.DanhGia.ViewDanhGia;

import java.util.List;

public class PresenterLogicDanhGia implements iPresenterDanhGia {

    ViewDanhGia viewDanhGia;
    ModelDanhGia modelDanhGia;

    public PresenterLogicDanhGia(ViewDanhGia viewDanhGia){
        this.viewDanhGia = viewDanhGia;
        modelDanhGia = new ModelDanhGia();
    }

    @Override
    public void ThemDanhGia(DanhGia danhGia) {
        boolean kiemtra = modelDanhGia.ThemDanhGia(danhGia);

        if(kiemtra){
            viewDanhGia.DanhGiaThanhCong();
        }else{
            viewDanhGia.DanhGiaThatBai();
        }
    }

    @Override
    public void LayDanhSachDanhGiaTheoSP(int masp, int limit, ProgressBar progressBar) {
        //progressBar.setVisibility(View.VISIBLE);
        List<DanhGia> danhGiaList = modelDanhGia.LayDanhSachDanhGiaSP(masp,limit);

        if(danhGiaList.size() > 0){
            progressBar.setVisibility(View.INVISIBLE);
            viewDanhGia.HienThiDSDanhGiaSP(danhGiaList);
        }
    }

}
