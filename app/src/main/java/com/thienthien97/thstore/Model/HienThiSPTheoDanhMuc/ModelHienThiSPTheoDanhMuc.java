package com.thienthien97.thstore.Model.HienThiSPTheoDanhMuc;

import com.thienthien97.thstore.ConnectDB.DownloadJSON;
import com.thienthien97.thstore.Model.ObjectClass.SanPham;
import com.thienthien97.thstore.View.TrangChu.TrangChuActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ModelHienThiSPTheoDanhMuc {

    public List<SanPham> LayDSSPTheoMaLoai(int masp, String tenmang, String tenham, int limit){
        List<SanPham> sanPhamList = new ArrayList<>();

        List<HashMap<String,String>> attrs = new ArrayList<>();
        String dataJSON = "";

        String duongdan = TrangChuActivity.SERVER_NAME;

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham", tenham);

        HashMap<String,String> hsMaLoai = new HashMap<>();
        hsMaLoai.put("maloaisp", String.valueOf(masp));

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
            JSONArray jsonArrayTOPSP = jsonObject.getJSONArray(tenmang);

            int dem = jsonArrayTOPSP.length();
            for(int i=0; i<dem; i++){
                SanPham sanPham = new SanPham();
                JSONObject object = jsonArrayTOPSP.getJSONObject(i);

                sanPham.setMASP(object.getInt("MASP"));
                sanPham.setTENSP(object.getString("TENSP"));
                sanPham.setGIA(object.getInt("GIATIEN"));
                sanPham.setANHLON(object.getString("HINHSANPHAM"));
                sanPham.setANHNHO(object.getString("HINHSANPHAMNHO"));

                sanPhamList.add(sanPham);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return sanPhamList;
    }

}
