package com.thienthien97.thstore.View.DanhGia;

import com.thienthien97.thstore.Model.ObjectClass.DanhGia;

import java.util.List;

public interface ViewDanhGia {
    void DanhGiaThanhCong();
    void DanhGiaThatBai();
    void HienThiDSDanhGiaSP(List<DanhGia> danhGiaList);
}
