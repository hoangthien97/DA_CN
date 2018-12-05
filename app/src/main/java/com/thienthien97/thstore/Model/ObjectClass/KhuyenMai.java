package com.thienthien97.thstore.Model.ObjectClass;

import java.util.List;

public class KhuyenMai {
    int MAKM, MALOAISP;
    String TENKM;
    String NGAYBATDAU;
    String NGAYKETTHUC;
    String HINHKHUYENMAI;
    String TENLOAISP;
    List<SanPham> DSSPKhuyenMai;


    public String getTENLOAISP() {
        return TENLOAISP;
    }

    public void setTENLOAISP(String TENLOAISP) {
        this.TENLOAISP = TENLOAISP;
    }

    public int getMAKM() {
        return MAKM;
    }

    public void setMAKM(int MAKM) {
        this.MAKM = MAKM;
    }

    public int getMALOAISP() {
        return MALOAISP;
    }

    public void setMALOAISP(int MALOAISP) {
        this.MALOAISP = MALOAISP;
    }

    public String getTENKM() {
        return TENKM;
    }

    public void setTENKM(String TENKM) {
        this.TENKM = TENKM;
    }

    public String getNGAYBATDAU() {
        return NGAYBATDAU;
    }

    public void setNGAYBATDAU(String NGAYBATDAU) {
        this.NGAYBATDAU = NGAYBATDAU;
    }

    public String getNGAYKETTHUC() {
        return NGAYKETTHUC;
    }

    public void setNGAYKETTHUC(String NGAYKETTHUC) {
        this.NGAYKETTHUC = NGAYKETTHUC;
    }

    public String getHINHKHUYENMAI() {
        return HINHKHUYENMAI;
    }

    public void setHINHKHUYENMAI(String HINHKHUYENMAI) {
        this.HINHKHUYENMAI = HINHKHUYENMAI;
    }

    public List<SanPham> getDSSPKhuyenMai() {
        return DSSPKhuyenMai;
    }

    public void setDSSPKhuyenMai(List<SanPham> DSSPKhuyenMai) {
        this.DSSPKhuyenMai = DSSPKhuyenMai;
    }
}
