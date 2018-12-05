package com.thienthien97.thstore.Model.DangNhap_DangKy;

import com.thienthien97.thstore.ConnectDB.DownloadJSON;
import com.thienthien97.thstore.Model.ObjectClass.NhanVien;
import com.thienthien97.thstore.View.TrangChu.TrangChuActivity;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ModelDangKy {

    public Boolean DangKyThanhVien(NhanVien nhanVien){
        String duongdan = TrangChuActivity.SERVER_NAME;
        boolean kiemtra = false;

        List<HashMap<String, String>> attrs = new ArrayList<>();

        HashMap<String, String> hsHam = new HashMap<>();
        hsHam.put("ham","DangKyThanhVien" );

        HashMap<String, String> hsTenNV = new HashMap<>();
        hsTenNV.put("tennv", nhanVien.getTenNV());

        HashMap<String, String> hsTenDN = new HashMap<>();
        hsTenDN.put("tendangnhap", nhanVien.getTenDN());

        HashMap<String, String> hsMatKhau = new HashMap<>();
        hsMatKhau.put("matkhau", nhanVien.getMatKhau());

        HashMap<String, String> hsMaLoaiNV = new HashMap<>();
        hsMaLoaiNV.put("maloainv", String.valueOf(nhanVien.getMaLoaiNV()));

        HashMap<String, String> hsEmailDocQuyen = new HashMap<>();
        hsEmailDocQuyen.put("emaildocquyen", nhanVien.getEmailDocQuyen());

        attrs.add(hsHam);
        attrs.add(hsTenNV);
        attrs.add(hsTenDN);
        attrs.add(hsMatKhau);
        attrs.add(hsMaLoaiNV);
        attrs.add(hsEmailDocQuyen);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan, attrs);
        downloadJSON.execute();

        try{
            String dulieuJSON  = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dulieuJSON);
            String ketqua = jsonObject.getString("ketqua");
            if(ketqua.equals("true")){
                kiemtra = true;
            }else{
                kiemtra = false;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return kiemtra;
    }
}
