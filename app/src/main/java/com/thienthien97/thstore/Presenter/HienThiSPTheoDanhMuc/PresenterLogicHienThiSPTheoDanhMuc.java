package com.thienthien97.thstore.Presenter.HienThiSPTheoDanhMuc;

import android.view.View;
import android.widget.ProgressBar;

import com.thienthien97.thstore.Model.HienThiSPTheoDanhMuc.ModelHienThiSPTheoDanhMuc;
import com.thienthien97.thstore.Model.ObjectClass.SanPham;
import com.thienthien97.thstore.View.HienThiSPTheoDanhMuc.ViewHienThiSPTheoDanhMuc;

import java.util.ArrayList;
import java.util.List;

public class PresenterLogicHienThiSPTheoDanhMuc implements iPrensenterHienThiSPTheoDanhMuc{

    ViewHienThiSPTheoDanhMuc viewHienThiSPTheoDanhMuc;
    ModelHienThiSPTheoDanhMuc modelHienThiSPTheoDanhMuc;

    public PresenterLogicHienThiSPTheoDanhMuc(ViewHienThiSPTheoDanhMuc viewHienThiSPTheoDanhMuc){
        this.viewHienThiSPTheoDanhMuc = viewHienThiSPTheoDanhMuc;
        modelHienThiSPTheoDanhMuc = new ModelHienThiSPTheoDanhMuc();
    }

    @Override
    public void LayDanhSachSPTheoMaLoai(int masp, boolean kt) {

        List<SanPham> sanPhamList = new ArrayList<>();

        if(kt){
            sanPhamList = modelHienThiSPTheoDanhMuc.LayDSSPTheoMaLoai(masp, "DANHSACHSANPHAM","LayDanhSachSanPhamTheoMaThuongHieu",0);
        }else {
            sanPhamList = modelHienThiSPTheoDanhMuc.LayDSSPTheoMaLoai(masp, "DANHSACHSANPHAM","LayDanhSachSanPhamTheoMaLoaiDanhMuc",0);
        }

        if(sanPhamList.size() > 0){
            viewHienThiSPTheoDanhMuc.HienThiDSSP(sanPhamList);
        }

    }

    public List<SanPham> LayDanhSachSPTheoMaLoaiLoadMore(int masp, boolean kt, int limit, ProgressBar progressBar){
        progressBar.setVisibility(View.VISIBLE);
        List<SanPham> sanPhamList = new ArrayList<>();

        if(kt){
            sanPhamList = modelHienThiSPTheoDanhMuc.LayDSSPTheoMaLoai(masp, "DANHSACHSANPHAM","LayDanhSachSanPhamTheoMaThuongHieu",limit);
        }else {
            sanPhamList = modelHienThiSPTheoDanhMuc.LayDSSPTheoMaLoai(masp, "DANHSACHSANPHAM","LayDanhSachSanPhamTheoMaLoaiDanhMuc",limit);
        }

        if(sanPhamList.size() > 0){
            progressBar.setVisibility(View.GONE);
        }
        return  sanPhamList;
    }

}
