package com.thienthien97.thstore.Presenter.DangKy;

import com.thienthien97.thstore.Model.DangNhap_DangKy.ModelDangKy;
import com.thienthien97.thstore.Model.ObjectClass.NhanVien;
import com.thienthien97.thstore.View.DangNhap_DangKy.ViewDangKy;

public class PresenterLogicDangKy implements iPresenterDangKy {

    ViewDangKy viewDangKy;
    ModelDangKy modelDangKy;

    public PresenterLogicDangKy(ViewDangKy viewDangKy){
        this.viewDangKy = viewDangKy;
        modelDangKy = new ModelDangKy();
    }

    @Override
    public void ThucHienDangKy(NhanVien nhanvien) {
        Boolean kiemtra = modelDangKy.DangKyThanhVien(nhanvien);
        if(kiemtra){
            viewDangKy.DangKyThanhCong();
        }else{
            viewDangKy.DangKyThatBai();
        }
    }
}
