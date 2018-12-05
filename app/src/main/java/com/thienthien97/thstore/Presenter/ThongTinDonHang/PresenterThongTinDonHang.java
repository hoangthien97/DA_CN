package com.thienthien97.thstore.Presenter.ThongTinDonHang;

import com.thienthien97.thstore.Model.ObjectClass.ThongTinHoaDon;
import com.thienthien97.thstore.Model.ThongTinDonHang.ModelThongTinDonHang;
import com.thienthien97.thstore.View.ThongTinDonHang.ViewThongTinDonHang;

public class PresenterThongTinDonHang implements iPresenterThongTinDonHang {

    ViewThongTinDonHang viewThongTinHoaDon;
    ModelThongTinDonHang modelThongTinHoaDon;

    public PresenterThongTinDonHang(ViewThongTinDonHang viewThongTinHoaDon){
        this.viewThongTinHoaDon = viewThongTinHoaDon;
        modelThongTinHoaDon = new ModelThongTinDonHang();
    }

    @Override
    public ThongTinHoaDon LayThongTinHoaDonTheoMaHD(int mahd) {
        ThongTinHoaDon thongTinHoaDon = modelThongTinHoaDon.LayThongTinHoaDon(mahd);

        if(thongTinHoaDon.getMAHD() > 0){
            viewThongTinHoaDon.TimThayThongTinHoaDon();
        }

        return thongTinHoaDon;
    }
}
