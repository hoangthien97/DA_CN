package com.thienthien97.thstore.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.thienthien97.thstore.Model.ObjectClass.DanhGia;
import com.thienthien97.thstore.R;

import java.util.List;

public class DanhGiaAdapter extends RecyclerView.Adapter<DanhGiaAdapter.ViewHolderDanhGia> {

    List<DanhGia> danhGiaList;
    int limit;
    Context context;

    public DanhGiaAdapter(Context context, List<DanhGia> danhGiaList, int limit){
        this.danhGiaList = danhGiaList;
        this.limit = limit;
        this.context = context;
    }

    @NonNull
    @Override
    public DanhGiaAdapter.ViewHolderDanhGia onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_layout_recycler_danhgia, parent, false);

        ViewHolderDanhGia viewHolderDanhGia = new ViewHolderDanhGia(view);

        return viewHolderDanhGia;
    }

    @Override
    public void onBindViewHolder(@NonNull DanhGiaAdapter.ViewHolderDanhGia holder, int position) {
        DanhGia danhGia = danhGiaList.get(position);

        holder.txtTieude.setText(danhGia.getTIEUDE());
        holder.txtNoidung.setText(danhGia.getNOIDUNG());
        holder.txtTennguoidang.setText("Được đánh giá bởi " + danhGia.getTENTHIETBI() + " / Ngày: " + danhGia.getNGAYDANHGIA());
        holder.rbDanhgia.setRating(danhGia.getSOSAO());
    }

    @Override
    public int getItemCount() {
        int dem = 0;

        if (danhGiaList.size() < limit) {
            dem = danhGiaList.size();
        } else {
            if (limit != 0) {
                dem = limit;
            }else{
                dem = danhGiaList.size();
            }
        }

        return  dem;
    }

    public class ViewHolderDanhGia extends RecyclerView.ViewHolder {

        TextView txtTieude, txtNoidung, txtTennguoidang;
        RatingBar rbDanhgia;

        public ViewHolderDanhGia(View itemView) {
            super(itemView);

            txtTieude = itemView.findViewById(R.id.txtTieuDeDanhGia);
            txtNoidung = itemView.findViewById(R.id.txtNoiDungDanhGia);
            txtTennguoidang = itemView.findViewById(R.id.txtTennguoidang);
            rbDanhgia = itemView.findViewById(R.id.rbDanhGia);

        }
    }
}
