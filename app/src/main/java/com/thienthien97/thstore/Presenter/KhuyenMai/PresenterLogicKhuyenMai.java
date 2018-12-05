package com.thienthien97.thstore.Presenter.KhuyenMai;

import com.thienthien97.thstore.Model.KhuyenMai.ModelKhuyenMai;
import com.thienthien97.thstore.Model.ObjectClass.KhuyenMai;
import com.thienthien97.thstore.View.TrangChu.ViewKhuyenMai;

import java.util.List;

public class PresenterLogicKhuyenMai implements iPresenterKhuyenMai {

    ViewKhuyenMai viewKhuyenMai;
    ModelKhuyenMai modelKhuyenMai;


    public PresenterLogicKhuyenMai(ViewKhuyenMai viewKhuyenMai){
        this.viewKhuyenMai = viewKhuyenMai;
        modelKhuyenMai = new ModelKhuyenMai();
    }

    @Override
    public void LayDSKhuyenMai() {
        List<KhuyenMai> khuyenMaiList = modelKhuyenMai.LayDanhSachSanPhamTheoMaLoai("LayDanhSachKhuyenMai","DANHSACHKHUYENMAI");

        if(khuyenMaiList.size() > 0){
            viewKhuyenMai.HienThiDSKhuyenMai(khuyenMaiList);
        }
    }
}
