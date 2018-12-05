package com.thienthien97.thstore.Presenter.TrangChu_DienTu;

import com.thienthien97.thstore.Model.ObjectClass.DienTu;
import com.thienthien97.thstore.Model.ObjectClass.SanPham;
import com.thienthien97.thstore.Model.ObjectClass.ThuongHieu;
import com.thienthien97.thstore.Model.TrangChu_DienTu.ModelDienTu;
import com.thienthien97.thstore.View.TrangChu.ViewDienTu;

import java.util.ArrayList;
import java.util.List;

public class PresenterLogicDienTu implements iPresenterDienTu {

    ViewDienTu viewDienTu;
    ModelDienTu modelDienTu;

    public PresenterLogicDienTu(ViewDienTu viewDienTu){
        this.viewDienTu = viewDienTu;
        modelDienTu = new ModelDienTu();
    }

    @Override
    public void LayDanhSachDienTu() {
        List<DienTu> dienTus = new ArrayList<>();

        List<ThuongHieu> thuongHieuList = modelDienTu.LayDanhSachThuongHieuLon("LayDanhSachCacThuongHieuLon","DANHSACHTHUONGHIEU");
        List<SanPham> sanPhamList = modelDienTu.LayDanhSachSPTOP("LayDanhSachTopDienThoaiVaMayTinhBang","TOPDIENTHOAI&MAYTINHBANG");

        DienTu dienTu = new DienTu();
        dienTu.setThuongHieus(thuongHieuList);
        dienTu.setSanPhams(sanPhamList);
        dienTu.setTennoibat("Thương hiệu nổi bật");
        dienTu.setTentopnoibat("Top điện thoại và máy tính bảng");
        dienTu.setThuonghieu(true);
        dienTus.add(dienTu);

        List<SanPham> phukienList = modelDienTu.LayDanhSachSPTOP("LayDanhSachTopPhuKien","TOPPHUKIEN");
        List<ThuongHieu> topphukienList = modelDienTu.LayDanhSachThuongHieuLon("LayDanhSachPhuKien","DANHSACHPHUKIEN");

        DienTu dienTu1 = new DienTu();
        dienTu1.setThuongHieus(topphukienList);
        dienTu1.setSanPhams(phukienList);
        dienTu1.setTennoibat("Phụ kiện");
        dienTu1.setTentopnoibat("Top các phụ kiện");
        dienTu1.setThuonghieu(false);
        dienTus.add(dienTu1);

        List<SanPham> tienichList = modelDienTu.LayDanhSachSPTOP("LayTopTienIch","TOPTIENICH");
        List<ThuongHieu> toptienichList = modelDienTu.LayDanhSachThuongHieuLon("LayDanhSachTienIch","DANHSACHTIENICH");

        DienTu dienTu2 = new DienTu();
        dienTu2.setThuongHieus(toptienichList);
        dienTu2.setSanPhams(tienichList);
        dienTu2.setTennoibat("Tiện ích");
        dienTu2.setTentopnoibat("Top các tiện ích");
        dienTu2.setThuonghieu(false);
        dienTus.add(dienTu2);

        if(thuongHieuList.size() > 0 && sanPhamList.size() > 0){
            viewDienTu.HienThiDanhSach(dienTus);
        }
    }

    @Override
    public void LayLogoThuongHieuDienTu(){
        List<ThuongHieu> thuongHieuList = modelDienTu.LayDanhSachThuongHieuLon("LayLogoThuongHieuLon","DANHSACHTHUONGHIEU");

        if(thuongHieuList.size() > 0){
            viewDienTu.HienThiLogoDienTu(thuongHieuList);
        }
    }

    @Override
    public void LayDanhSachSanPhamMoi() {
        List<SanPham> sanPhamList = modelDienTu.LayDanhSachSPTOP("LayDanhSachSanPhamMoi","DANHSACHSANPHAMMOIVE");

        if(sanPhamList.size() > 0){
           viewDienTu.HienThiDSSanPhamMoi(sanPhamList);
        }
    }
}
