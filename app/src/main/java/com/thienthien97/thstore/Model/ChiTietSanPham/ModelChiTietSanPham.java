package com.thienthien97.thstore.Model.ChiTietSanPham;

import com.thienthien97.thstore.ConnectDB.DownloadJSON;
import com.thienthien97.thstore.Model.ObjectClass.ChiTietKhuyenMai;
import com.thienthien97.thstore.Model.ObjectClass.ChiTietSanPham;
import com.thienthien97.thstore.Model.ObjectClass.DanhGia;
import com.thienthien97.thstore.Model.ObjectClass.SanPham;
import com.thienthien97.thstore.View.TrangChu.TrangChuActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ModelChiTietSanPham {

    public List<DanhGia> LayDanhSachDanhGiaSP(int masp, int limit){
        List<DanhGia> danhGias = new ArrayList<>();

        List<HashMap<String,String>> attrs = new ArrayList<>();
        String dataJSON = "";

        String duongdan = TrangChuActivity.SERVER_NAME;

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham", "LayDanhSachDanhGiaTheoMaSP");

        HashMap<String,String> hsMaLoai = new HashMap<>();
        hsMaLoai.put("masp", String.valueOf(masp));

        HashMap<String,String> hsLimit = new HashMap<>();
        hsLimit.put("limit", String.valueOf(limit));

        attrs.add(hsHam);
        attrs.add(hsMaLoai);
        attrs.add(hsLimit);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        //end phương thức post
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArrayTOPSP = jsonObject.getJSONArray("DANHSACHDANHGIA");

            int dem = jsonArrayTOPSP.length();
            for(int i=0; i<dem; i++){
                DanhGia danhGia = new DanhGia();
                JSONObject object = jsonArrayTOPSP.getJSONObject(i);

                danhGia.setTENTHIETBI(object.getString("TENTHIETBI"));
                danhGia.setTIEUDE(object.getString("TIEUDE"));
                danhGia.setNOIDUNG(object.getString("NOIDUNG"));
                danhGia.setSOSAO(object.getInt("SOSAO"));
                danhGia.setMASP(object.getInt("MASP"));
                danhGia.setMADG(object.getString("MADG"));
                danhGia.setNGAYDANHGIA(object.getString("NGAYDANHGIA"));

                danhGias.add(danhGia);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return danhGias;
    }

    public SanPham LayChiTietSP(String tenham, String tenmang, int masp){
        SanPham sanPham = new SanPham();
        List<ChiTietSanPham> chiTietSanPhams = new ArrayList<>();

        List<HashMap<String,String>> attrs = new ArrayList<>();
        String dataJSON = "";

        String duongdan = TrangChuActivity.SERVER_NAME;

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham", tenham);

        HashMap<String,String> hsMasp = new HashMap<>();
        hsMasp.put("masp", String.valueOf(masp));

        attrs.add(hsHam);
        attrs.add(hsMasp);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        //end phương thức post
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();
//            Log.d("kiemtra",dataJSON.toString());
            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArrayDanhSachSanPham = jsonObject.getJSONArray(tenmang);

            int count = jsonArrayDanhSachSanPham.length();
            for (int i = 0; i<count; i++){

                JSONObject object = jsonArrayDanhSachSanPham.getJSONObject(i);
                ChiTietKhuyenMai chiTietKhuyenMai = new ChiTietKhuyenMai();
                chiTietKhuyenMai.setPHANTRAMKM(object.getInt("PHANTRAMKM"));

                sanPham.setChiTietKhuyenMai(chiTietKhuyenMai);
                sanPham.setMASP(object.getInt("MASP"));
                sanPham.setTENSP(object.getString("TENSP"));
                sanPham.setGIA(object.getInt("GIATIEN"));
                sanPham.setANHNHO(object.getString("ANHNHO"));
                sanPham.setSOLUONG(object.getInt("SOLUONG"));
                sanPham.setTHONGTIN(object.getString("THONGTIN"));
                sanPham.setMALOAISP(object.getInt("MALOAISP"));
                sanPham.setMATHUONGHIEU(object.getInt("MATHUONGHIEU"));
                sanPham.setMANV(object.getInt("MANV"));
                sanPham.setTENNHANVIEN(object.getString("TENNV"));
                sanPham.setLUOTMUA(object.getInt("LUOTMUA"));

                JSONArray jsonArrayThongSoKyThuat = object.getJSONArray("THONGSOKYTHUAT");
                for (int j=0; j<jsonArrayThongSoKyThuat.length(); j++){
                    JSONObject jsonObject1 = jsonArrayThongSoKyThuat.getJSONObject(j);

                    for(int k=0; k<jsonObject1.names().length(); k++){
                        String tenchitiet = jsonObject1.names().getString(k);

                        ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
                        chiTietSanPham.setTENCHITIET(tenchitiet);
                        chiTietSanPham.setGIATRI(jsonObject1.getString(tenchitiet));

                        chiTietSanPhams.add(chiTietSanPham);
                    }
                }

                sanPham.setChiTietSanPhamList(chiTietSanPhams);

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sanPham;
    }
}
