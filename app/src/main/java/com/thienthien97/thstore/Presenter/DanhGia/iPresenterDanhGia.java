package com.thienthien97.thstore.Presenter.DanhGia;

import android.widget.ProgressBar;

import com.thienthien97.thstore.Model.ObjectClass.DanhGia;

public interface iPresenterDanhGia {
    void ThemDanhGia(DanhGia danhGia);
    void LayDanhSachDanhGiaTheoSP(int masp, int limit, ProgressBar progressBar);
}
