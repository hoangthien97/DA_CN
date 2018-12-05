package com.thienthien97.thstore.View.TrangChu;

import com.thienthien97.thstore.Model.ObjectClass.DienTu;
import com.thienthien97.thstore.Model.ObjectClass.SanPham;
import com.thienthien97.thstore.Model.ObjectClass.ThuongHieu;

import java.util.List;

public interface ViewDienTu {
    void HienThiDanhSach(List<DienTu> dienTus);
    void HienThiLogoDienTu(List<ThuongHieu> thuongHieus);
    void HienThiDSSanPhamMoi(List<SanPham> sanPhams);
}
