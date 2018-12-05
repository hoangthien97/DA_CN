package com.thienthien97.thstore.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.thienthien97.thstore.Model.ObjectClass.DienTu;
import com.thienthien97.thstore.R;

import java.util.List;

public class DienTuAdapter extends RecyclerView.Adapter<DienTuAdapter.ViewHolderDienTu> {
    Context context;
    List<DienTu> dienTuList;

    public DienTuAdapter(Context context, List<DienTu> dienTuList){
        this.context = context;
        this.dienTuList = dienTuList;
    }

    public class ViewHolderDienTu extends RecyclerView.ViewHolder {
        ImageView imgHinhKhuyenMai;
        RecyclerView recyclerviewThuongHieuLon, reycyclerviewTopSanPham;
        TextView txtSPNoiBat, txtTopSPNoiBat;

        public ViewHolderDienTu(View itemView) {
            super(itemView);

            recyclerviewThuongHieuLon = itemView.findViewById(R.id.recyclerThuongHieuNoiBat);
            reycyclerviewTopSanPham = itemView.findViewById(R.id.recyclerTopDienThoaiMayTinhBang);
            imgHinhKhuyenMai = itemView.findViewById(R.id.imgKhuyenMaiDienTu);
            txtSPNoiBat = itemView.findViewById(R.id.txtTenSanPhamNoiBat);
            txtTopSPNoiBat = itemView.findViewById(R.id.txtTenTopSanPhamNoiBat);
        }
    }

    @NonNull
    @Override
    public ViewHolderDienTu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view  = inflater.inflate(R.layout.custom_layout_recyclerview_dientu, parent, false);

        ViewHolderDienTu viewHolderDienTu = new ViewHolderDienTu(view);
        return viewHolderDienTu;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDienTu holder, int position) {
        DienTu dienTu = dienTuList.get(position);

        holder.txtSPNoiBat.setText(dienTu.getTennoibat().toString());
        holder.txtTopSPNoiBat.setText(dienTu.getTentopnoibat().toString());

        //xử lý adapter hiển thị ds thương hiệu (RecyclerView thương hiệu lớn)
        ThuongHieuLonAdapter thuongHieuLonAdapter = new ThuongHieuLonAdapter(context, dienTu.getThuongHieus(), dienTu.isThuonghieu());

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context, 3, GridLayoutManager.HORIZONTAL,false);
        holder.recyclerviewThuongHieuLon.setLayoutManager(layoutManager);
        holder.recyclerviewThuongHieuLon.setAdapter(thuongHieuLonAdapter);

        thuongHieuLonAdapter.notifyDataSetChanged();

        //xử lý adapter hiểm ds top sản phẩm (RecyclerView TopSP)
        TopDienThoaiAdapter topDienThoaiAdapter = new TopDienThoaiAdapter(context, R.layout.custom_layout_topdtvamaytinhbang, dienTu.getSanPhams());

        RecyclerView.LayoutManager layoutManagerTOP = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);

        holder.reycyclerviewTopSanPham.setLayoutManager(layoutManagerTOP);
        holder.reycyclerviewTopSanPham.setAdapter(topDienThoaiAdapter);

        topDienThoaiAdapter.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dienTuList.size();
    }
}
