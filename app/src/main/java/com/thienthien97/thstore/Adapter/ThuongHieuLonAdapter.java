package com.thienthien97.thstore.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.thienthien97.thstore.Model.ObjectClass.ThuongHieu;
import com.thienthien97.thstore.R;
import com.thienthien97.thstore.View.HienThiSPTheoDanhMuc.HienThiSPTheoDanhMucActivity;

import java.util.List;

public class ThuongHieuLonAdapter extends RecyclerView.Adapter<ThuongHieuLonAdapter.ViewHolderThuongHieu>{

    Context context;
    List<ThuongHieu> thuongHieuList;
    boolean kt;

    public ThuongHieuLonAdapter(Context context, List<ThuongHieu> thuongHieuList, boolean kt){
        this.context = context;
        this.thuongHieuList = thuongHieuList;
        this.kt = kt;
    }

    public class ViewHolderThuongHieu extends RecyclerView.ViewHolder {
        TextView txtTieuDeThuongHieu;
        ImageView imgHinhThuongHieu;
        ProgressBar progressBar;
        LinearLayout linearLayout;

        public ViewHolderThuongHieu(View itemView) {
            super(itemView);

            txtTieuDeThuongHieu = itemView.findViewById(R.id.txtThuongHieuLonDienTu);
            imgHinhThuongHieu = itemView.findViewById(R.id.imgThuongHieuLonDienTu);
            progressBar = itemView.findViewById(R.id.progress_bar_download);
            linearLayout = itemView.findViewById(R.id.linearThuonghieulon);

        }
    }

    @NonNull
    @Override
    public ThuongHieuLonAdapter.ViewHolderThuongHieu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_layout_recyclerview_thuonghieulon, parent,false);

        ViewHolderThuongHieu viewHolder = new ViewHolderThuongHieu(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ThuongHieuLonAdapter.ViewHolderThuongHieu holder, int position) {
        final ThuongHieu thuongHieu = thuongHieuList.get(position);
        holder.txtTieuDeThuongHieu.setText(thuongHieu.getTENTHUONGHIEU());

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                HienThiSPTheoDanhMucActivity hienThiSPTheoDanhMucActivity = new HienThiSPTheoDanhMucActivity();

                Bundle bundle = new Bundle();
                bundle.putInt("MALOAI", thuongHieu.getMATHUONGHIEU());
                bundle.putBoolean("KIEMTRA",kt);
                bundle.putString("TENLOAI", thuongHieu.getTENTHUONGHIEU());
                hienThiSPTheoDanhMucActivity.setArguments(bundle);

                fragmentTransaction.addToBackStack("TrangChu");
                fragmentTransaction.replace(R.id.themFragment, hienThiSPTheoDanhMucActivity);
                fragmentTransaction.commit();
            }
        });

        Picasso.with(context).load(thuongHieu.getHINHTHUONGHIEU()).resize(150,150).into(holder.imgHinhThuongHieu, new Callback() {
            @Override
            public void onSuccess() {
                holder.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError() {

            }
        });
    }

    @Override
    public int getItemCount() {
        return thuongHieuList.size();
    }

}
