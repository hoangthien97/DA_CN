package com.thienthien97.thstore.Presenter.TimKiem;

import com.thienthien97.thstore.Model.ObjectClass.SanPham;
import com.thienthien97.thstore.Model.TimKiem.ModelTimKiem;
import com.thienthien97.thstore.View.TimKiem.ViewTimKiem;

import java.util.List;

public class PresenterLogicTimKiem implements iPresenterTimKiem {

    ViewTimKiem viewTimKiem;
    ModelTimKiem modelTimKiem;

    public PresenterLogicTimKiem(ViewTimKiem viewTimKiem){
        this.viewTimKiem = viewTimKiem;
        modelTimKiem = new ModelTimKiem();
    }

    @Override
    public void TimKiemSPTheoTen(String tensp, int limit) {
        List<SanPham> sanPhamList = modelTimKiem.TimKiemSPTheoTen(tensp,"DANHSACHSANPHAM","TimKiemSanPhamTheoTenSP",limit);

        if(sanPhamList.size() > 0){
            viewTimKiem.TimKiemThanhCong(sanPhamList);
        }else{
            viewTimKiem.TimKiemThatBai();
        }
    }
}
