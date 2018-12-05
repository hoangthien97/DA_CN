package com.thienthien97.thstore.Presenter.ChiTietSanPham;

import android.content.Context;

import com.thienthien97.thstore.Model.ObjectClass.SanPham;

public interface iPresenterChiTietSanPham {
    void LayChiTietSanPham(int masp);
    void LayDanhSachDanhGiaTheoSP(int masp, int limit);
    void ThemGioHang(SanPham sanPham, Context context);
}
