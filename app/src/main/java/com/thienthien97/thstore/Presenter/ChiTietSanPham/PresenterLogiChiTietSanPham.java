package com.thienthien97.thstore.Presenter.ChiTietSanPham;

import android.content.Context;

import com.thienthien97.thstore.Model.ChiTietSanPham.ModelChiTietSanPham;
import com.thienthien97.thstore.Model.DanhGia.ModelDanhGia;
import com.thienthien97.thstore.Model.GioHang.ModelGiohang;
import com.thienthien97.thstore.Model.ObjectClass.DanhGia;
import com.thienthien97.thstore.Model.ObjectClass.SanPham;
import com.thienthien97.thstore.View.ChiTietSanPham.ViewChiTietSanPham;

import java.util.List;

public class PresenterLogiChiTietSanPham implements iPresenterChiTietSanPham{

    ViewChiTietSanPham viewChiTietSanPham;
    ModelChiTietSanPham modelChiTietSanPham;
    ModelGiohang modelGiohang;
    ModelDanhGia modelDanhGia;

    public PresenterLogiChiTietSanPham(ViewChiTietSanPham viewChiTietSanPham){
        this.viewChiTietSanPham = viewChiTietSanPham;
        this.modelChiTietSanPham = new ModelChiTietSanPham();
        modelGiohang = new ModelGiohang();
        modelDanhGia = new ModelDanhGia();
    }

    public PresenterLogiChiTietSanPham(){
        modelGiohang = new ModelGiohang();
    }

    @Override
    public void LayChiTietSanPham(int masp) {
        SanPham sanPham = modelChiTietSanPham.LayChiTietSP("LaySanPhamVsChitietTheoMaSP","CHITIETSANPHAM",masp);

        if(sanPham.getMASP() > 0){
            String[] linkHinh = sanPham.getANHNHO().split(",");
            viewChiTietSanPham.HienThiSliderSanPham(linkHinh);
            viewChiTietSanPham.HienThiChiTietSanPham(sanPham);
        }
    }

    @Override
    public void LayDanhSachDanhGiaTheoSP(int masp, int limit) {
        List<DanhGia> danhGias = modelChiTietSanPham.LayDanhSachDanhGiaSP(masp,limit);

        if(danhGias.size()  > 0){
            viewChiTietSanPham.HienThiDanhGia(danhGias);
        }

    }

    @Override
    public void ThemGioHang(SanPham sanPham, Context context) {
        modelGiohang.MoKetNoiDB(context);
        boolean kt = modelGiohang.ThemSPGioHang(sanPham);
        if(kt){
            viewChiTietSanPham.ThemGioHangThanhCong();
        }else {
            viewChiTietSanPham.ThemGiohangThatbai();
        }
    }

    public int DemSanPhamTrongGioHang(Context context){
        modelGiohang.MoKetNoiDB(context);
        List<SanPham> sanPhamList = modelGiohang.LayDSSPGioHang();

        int dem = sanPhamList.size();
        return dem;
    }
}
