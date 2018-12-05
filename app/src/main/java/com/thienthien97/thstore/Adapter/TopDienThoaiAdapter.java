package com.thienthien97.thstore.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.thienthien97.thstore.Model.ObjectClass.ChiTietKhuyenMai;
import com.thienthien97.thstore.Model.ObjectClass.SanPham;
import com.thienthien97.thstore.R;
import com.thienthien97.thstore.View.ChiTietSanPham.ChiTietSanPhamActivity;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class TopDienThoaiAdapter extends RecyclerView.Adapter<TopDienThoaiAdapter.ViewHolderTopDT> {

    Context context;
    List<SanPham> sanPhamList;
    int layout;

    public TopDienThoaiAdapter(Context context, int layout, List<SanPham> sanPhamList){
        this.context = context;
        this.sanPhamList = sanPhamList;
        this.layout = layout;
    }

    @NonNull
    @Override
    public ViewHolderTopDT onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(layout, parent,  false);

        ViewHolderTopDT viewHolderTopDT = new ViewHolderTopDT(view);

        return viewHolderTopDT;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderTopDT holder, int position) {

        SanPham sanPham = sanPhamList.get(position);
        Picasso.with(context).load(sanPham.getANHLON()).resize(140, 140).centerInside()
                .placeholder(R.drawable.noimage)
                .into(holder.imgHinhSP);

        holder.txtTenSP.setText(sanPham.getTENSP());

        ChiTietKhuyenMai chiTietKhuyenMai = sanPham.getChiTietKhuyenMai();
        int giatien = sanPham.getGIA();

        if(chiTietKhuyenMai !=null){
            int phamtramkm = chiTietKhuyenMai.getPHANTRAMKM();

            NumberFormat numberFormat = new DecimalFormat("###,###");
            String gia = numberFormat.format(giatien);

            holder.txtGiamGia.setVisibility(View.VISIBLE);
            holder.txtGiamGia.setPaintFlags(holder.txtGiamGia.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.txtGiamGia.setText(gia + " VNĐ");

            giatien = giatien * phamtramkm/100;

        }

        NumberFormat numberFormat = new DecimalFormat("###,###");
        String gia = numberFormat.format(giatien);
        holder.txtGiaTien.setText(gia + " VND");
        holder.cardView.setTag(sanPham.getMASP());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentCTSP = new Intent(context, ChiTietSanPhamActivity.class);
                intentCTSP.putExtra("masp", (int)view.getTag());
                context.startActivity(intentCTSP);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sanPhamList.size();
    }

    public class ViewHolderTopDT extends RecyclerView.ViewHolder {
        ImageView imgHinhSP;
        TextView txtTenSP, txtGiaTien, txtGiamGia;
        CardView cardView;

        public ViewHolderTopDT(View itemView) {
            super(itemView);

            imgHinhSP = itemView.findViewById(R.id.imgTopDienThoaiDienTu);
            txtTenSP = itemView.findViewById(R.id.txtTopDienThoaiDienTu);
            txtGiaTien = itemView.findViewById(R.id.txtGiaDienTu);
            txtGiamGia = itemView.findViewById(R.id.txtGiamGiaDienTu);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
