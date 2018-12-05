package com.thienthien97.thstore.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.thienthien97.thstore.Model.ObjectClass.ThuongHieu;
import com.thienthien97.thstore.R;

import java.util.List;

public class ThuongHieuDienTuAdapter extends RecyclerView.Adapter<ThuongHieuDienTuAdapter.ViewHolderThuongHieuDT> {

    Context context;
    List<ThuongHieu> thuongHieus;

    public ThuongHieuDienTuAdapter(Context context, List<ThuongHieu> thuongHieus){
        this.context = context;
        this.thuongHieus = thuongHieus;
    }

    @NonNull
    @Override
    public ThuongHieuDienTuAdapter.ViewHolderThuongHieuDT onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_layout_recycler_thuonghieudientu, parent, false);

        ViewHolderThuongHieuDT viewHolderDienTu = new ViewHolderThuongHieuDT(view);

        return viewHolderDienTu;
    }

    @Override
    public void onBindViewHolder(@NonNull ThuongHieuDienTuAdapter.ViewHolderThuongHieuDT holder, int position) {
        ThuongHieu thuongHieu = thuongHieus.get(position);

        Picasso.with(context).load(thuongHieu.getHINHTHUONGHIEU()).resize(180,90).centerInside().into(holder.imgLogoDienTu);
    }

    @Override
    public int getItemCount() {
        return thuongHieus.size();
    }

    public class ViewHolderThuongHieuDT extends RecyclerView.ViewHolder {
        ImageView imgLogoDienTu;

        public ViewHolderThuongHieuDT(View itemView) {
            super(itemView);

            imgLogoDienTu = itemView.findViewById(R.id.imgLogoThuongHieuDienTu);
        }
    }
}
