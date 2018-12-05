package com.thienthien97.thstore.View.ThanhToan;

import com.thienthien97.thstore.Model.ObjectClass.SanPham;

import java.util.List;

public interface ViewThanhToan {
    void DatHangThanhCong();
    void DatHangThatBai();
    void LayDSSPTrongGioHang(List<SanPham> sanPhamList);
}
