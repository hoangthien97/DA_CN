package com.thienthien97.thstore.Presenter.ThanhToan;

import android.content.Context;

import com.thienthien97.thstore.Model.GioHang.ModelGiohang;
import com.thienthien97.thstore.Model.ObjectClass.HoaDon;
import com.thienthien97.thstore.Model.ObjectClass.SanPham;
import com.thienthien97.thstore.Model.ThanhToan.ModelThanhToan;
import com.thienthien97.thstore.View.ThanhToan.ViewThanhToan;

import java.util.List;

public class PresenterLogicThanhToan implements iPrensenterLogicThanhToan {

    ViewThanhToan viewThanhToan;
    ModelThanhToan modelThanhToan;
    ModelGiohang modelGiohang;
    List<SanPham> sanPhamList;

    public PresenterLogicThanhToan(ViewThanhToan viewThanhToan, Context context){
        this.viewThanhToan = viewThanhToan;
        modelThanhToan = new ModelThanhToan();
        modelGiohang = new ModelGiohang();
        modelGiohang.MoKetNoiDB(context);
    }

    @Override
    public void ThemHoaDon(HoaDon hoaDon) {
        boolean kt = modelThanhToan.ThemHoaDon(hoaDon);

        if(kt){
            viewThanhToan.DatHangThanhCong();

            int dem = sanPhamList.size();
            for(int i = 0; i<dem ;i++){
                modelGiohang.XoaSPGioHang(sanPhamList.get(i).getMASP());
            }

        }else {
            viewThanhToan.DatHangThatBai();
        }
    }

    @Override
    public void LayDSSPTrongGioHang() {
        sanPhamList = modelGiohang.LayDSSPGioHang();
        viewThanhToan.LayDSSPTrongGioHang(sanPhamList);
    }
}
