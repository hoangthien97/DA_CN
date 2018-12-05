package com.thienthien97.thstore.Model.ThongTinDonHang;

import com.thienthien97.thstore.ConnectDB.DownloadJSON;
import com.thienthien97.thstore.Model.ObjectClass.ThongTinHoaDon;
import com.thienthien97.thstore.View.TrangChu.TrangChuActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ModelThongTinDonHang {

    public ThongTinHoaDon LayThongTinHoaDon(int mahd){
        ThongTinHoaDon thongTinHoaDon = new ThongTinHoaDon();

        List<HashMap<String,String>> attrs = new ArrayList<>();
        String dataJSON = "";

        String duongdan = TrangChuActivity.SERVER_NAME;

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham", "LayThongTinChiTietDonHangTheoMaHoaDon");

        HashMap<String,String> hsMaHD = new HashMap<>();
        hsMaHD.put("mahd", String.valueOf(mahd));


        attrs.add(hsHam);
        attrs.add(hsMaHD);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        //end phương thức post
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArrayTOPSP = jsonObject.getJSONArray("THONGTINHOADON");

            int dem = jsonArrayTOPSP.length();
            for(int i=0; i<dem; i++){

                JSONObject object = jsonArrayTOPSP.getJSONObject(i);

                thongTinHoaDon.setTENSP(object.getString("TENSP"));
                thongTinHoaDon.setGIA(object.getInt("GIA"));
                thongTinHoaDon.setSOLUONG(object.getInt("SOLUONG"));
                thongTinHoaDon.setTEBNGUOINHAN(object.getString("TENNGUOINHAN"));
                thongTinHoaDon.setSODT(object.getString("SODT"));
                thongTinHoaDon.setDIACHI(object.getString("DIACHI"));
                thongTinHoaDon.setNGAYMUA(object.getString("NGAYMUA"));
                thongTinHoaDon.setNGAYGIAO(object.getString("NGAYGIAO"));

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return thongTinHoaDon;
    }
}
