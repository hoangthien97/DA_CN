package com.thienthien97.thstore.View.ChiTietSanPham;

import com.thienthien97.thstore.Model.ObjectClass.DanhGia;
import com.thienthien97.thstore.Model.ObjectClass.SanPham;

import java.util.List;

public interface ViewChiTietSanPham {
    void HienThiChiTietSanPham(SanPham sanPham);
    void HienThiSliderSanPham(String[] linkHinhSP);
    void HienThiDanhGia(List<DanhGia> danhGiaList);
    void ThemGioHangThanhCong();
    void ThemGiohangThatbai();
}
