package com.thienthien97.thstore.Model.GioHang;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.thienthien97.thstore.Model.ObjectClass.SanPham;

import java.util.ArrayList;
import java.util.List;

public class ModelGiohang {

    SQLiteDatabase sqLiteDatabase;

    public void MoKetNoiDB(Context context){
        DBSanPham dbSanPham = new DBSanPham(context);
        sqLiteDatabase = dbSanPham.getWritableDatabase();
    }

    public boolean ThemSPGioHang(SanPham sanPham){

        ContentValues contentValues = new ContentValues();
        contentValues.put(DBSanPham.TB_GIOHANG_MASP, sanPham.getMASP());
        contentValues.put(DBSanPham.TB_GIOHANG_TENSP, sanPham.getTENSP());
        contentValues.put(DBSanPham.TB_GIOHANG_GIATIEN, sanPham.getGIA());
        contentValues.put(DBSanPham.TB_GIOHANG_HINHANH, sanPham.getHinhgiohang());
        contentValues.put(DBSanPham.TB_GIOHANG_SOLUONG, sanPham.getSOLUONG());
        contentValues.put(DBSanPham.TB_GIOHANG_SOLUONGTONKHO, sanPham.getSOLUONGTONKHO());

        long id = sqLiteDatabase.insert(DBSanPham.TB_GIOHANG,null,contentValues);
        if(id > 0){
            return true;
        }else {
            return false;
        }
    }

    public boolean XoaSPGioHang(int masp){

        int kt = sqLiteDatabase.delete(DBSanPham.TB_GIOHANG, DBSanPham.TB_GIOHANG_MASP + "=" + masp, null);
        if(kt > 0){
            return true;
        }else {
            return false;
        }
    }

    public boolean CapNhatSLPSGioHang(int masp, int sl){

        ContentValues contentValues = new ContentValues();
        contentValues.put(DBSanPham.TB_GIOHANG_SOLUONG,sl);

        int id = sqLiteDatabase.update(DBSanPham.TB_GIOHANG,contentValues,DBSanPham.TB_GIOHANG_MASP + " = " + masp,null);
        if(id > 0){
            return true;
        }else{
            return false;
        }

    }

    public List<SanPham> LayDSSPGioHang(){
        List<SanPham> sanPhamList = new ArrayList<>();

        String query = " SELECT * FROM " + DBSanPham.TB_GIOHANG;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int masp = cursor.getInt(cursor.getColumnIndex(DBSanPham.TB_GIOHANG_MASP));
            String tensp = cursor.getString(cursor.getColumnIndex(DBSanPham.TB_GIOHANG_TENSP));
            int giatien = cursor.getInt(cursor.getColumnIndex(DBSanPham.TB_GIOHANG_GIATIEN));
            byte[] hinhanh = cursor.getBlob(cursor.getColumnIndex(DBSanPham.TB_GIOHANG_HINHANH));
            int sl = cursor.getInt(cursor.getColumnIndex(DBSanPham.TB_GIOHANG_SOLUONG));
            int sltonkho = cursor.getInt(cursor.getColumnIndex(DBSanPham.TB_GIOHANG_SOLUONGTONKHO));

            SanPham sanPham = new SanPham();
            sanPham.setMASP(masp);
            sanPham.setTENSP(tensp);
            sanPham.setGIA(giatien);
            sanPham.setHinhgiohang(hinhanh);
            sanPham.setSOLUONG(sl);
            sanPham.setSOLUONGTONKHO(sltonkho);

            sanPhamList.add(sanPham);
            cursor.moveToNext();
        }

        return sanPhamList;
    }
}
