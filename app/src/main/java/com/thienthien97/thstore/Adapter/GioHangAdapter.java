package com.thienthien97.thstore.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.thienthien97.thstore.Model.GioHang.ModelGiohang;
import com.thienthien97.thstore.Model.ObjectClass.SanPham;
import com.thienthien97.thstore.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.ViewHolderGioHang> {

    Context context;
    List<SanPham> sanPhamList;
    ModelGiohang modelGiohang;

   public GioHangAdapter(Context context, List<SanPham> sanPhamList){
       this.context = context;
       this.sanPhamList = sanPhamList;
       modelGiohang = new ModelGiohang();
       modelGiohang.MoKetNoiDB(context);
   }

    @NonNull
    @Override
    public GioHangAdapter.ViewHolderGioHang onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       View view = inflater.inflate(R.layout.custom_layout_giohang, parent, false);

       ViewHolderGioHang viewHolderGioHang = new ViewHolderGioHang(view);

       return viewHolderGioHang;
    }

    @Override
    public void onBindViewHolder(@NonNull final GioHangAdapter.ViewHolderGioHang holder, final int position) {
        final SanPham sanPham = sanPhamList.get(position);

        holder.txtTenSPGioHang.setText(sanPham.getTENSP());

        NumberFormat numberFormat = new DecimalFormat("###,###");
        String gia = numberFormat.format(sanPham.getGIA()).toString();
        holder.txtGiaSPGioHang.setText(gia + " VND");

        byte[] hinhsp = sanPham.getHinhgiohang();
        Bitmap bitmapHinhGioHang = BitmapFactory.decodeByteArray(hinhsp,0,hinhsp.length);
        holder.imghinhGioHang.setImageBitmap(bitmapHinhGioHang);

        holder.imgXoaSPGioHang.setTag(sanPham.getMASP());

        holder.imgXoaSPGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xóa khỏi giỏ hàng");
                builder.setMessage("Bạn có muốn xóa sản phẩm này không ?");
                builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ModelGiohang modelGiohang = new ModelGiohang();
                        modelGiohang.MoKetNoiDB(context);
                        modelGiohang.XoaSPGioHang((int)view.getTag());
                        sanPhamList.remove(position);
                        notifyDataSetChanged();
                    }
                });

                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        notifyDataSetChanged();
                    }
                });

                builder.show();

            }
        });

        holder.txtSLSP.setText(String.valueOf(sanPham.getSOLUONG()));

        holder.img_btnTang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int slmoi = Integer.parseInt(holder.txtSLSP.getText().toString()) +1;

//                int slht = sanPhamList.get(position).getSOLUONG();
//                int giaht = sanPhamList.get(position).getGIA();
//                sanPhamList.get(position).setSOLUONG(slmoi);
//                int giamoi = (giaht * slmoi) / slht;
//                sanPhamList.get(position).setGIA(giamoi);
//
//                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
//                String gia = decimalFormat.format(giamoi);
//                holder.txtTongTien.setText(gia + " VND");
//
//                Log.d("giamoi", gia + " vnd");

                if(slmoi > 9 ){
                    holder.img_btnTang.setVisibility(View.INVISIBLE);
                    holder.img_btnGiam.setVisibility(View.VISIBLE);
                    holder.txtSLSP.setText(String.valueOf(slmoi));
                }else {
                    holder.img_btnTang.setVisibility(View.VISIBLE);
                    holder.img_btnGiam.setVisibility(View.VISIBLE);
                    holder.txtSLSP.setText(String.valueOf(slmoi));
                }

                modelGiohang.CapNhatSLPSGioHang(sanPham.getMASP(), slmoi);

            }
        });

        holder.img_btnGiam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int slmoi = Integer.parseInt(holder.txtSLSP.getText().toString()) -1;

                if(slmoi < 2 ){
                    holder.img_btnTang.setVisibility(View.VISIBLE);
                    holder.img_btnGiam.setVisibility(View.INVISIBLE);
                    holder.txtSLSP.setText(String.valueOf(slmoi));
                }else {
                    holder.img_btnTang.setVisibility(View.VISIBLE);
                    holder.img_btnGiam.setVisibility(View.VISIBLE);
                    holder.txtSLSP.setText(String.valueOf(slmoi));
                }

                modelGiohang.CapNhatSLPSGioHang(sanPham.getMASP(), slmoi);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sanPhamList.size();
    }

    public class ViewHolderGioHang extends RecyclerView.ViewHolder {

        TextView txtTenSPGioHang, txtGiaSPGioHang, txtSLSP;
        TextView txtTongTien;
        ImageView imghinhGioHang, imgXoaSPGioHang;
        ImageButton img_btnTang, img_btnGiam;

        public ViewHolderGioHang(View itemView) {
            super(itemView);

            txtGiaSPGioHang = itemView.findViewById(R.id.txtGiaGioHang);
            txtTenSPGioHang = itemView.findViewById(R.id.txtTenSPGioHang);
            imghinhGioHang = itemView.findViewById(R.id.imgHinhGioHang);
            imgXoaSPGioHang = itemView.findViewById(R.id.imgXoaGioHang);
            img_btnTang = itemView.findViewById(R.id.img_buttonTang);
            img_btnGiam = itemView.findViewById(R.id.img_buttonGiam);
            txtSLSP = itemView.findViewById(R.id.txtSoLuongSP);
            txtTongTien = itemView.findViewById(R.id.txtTongTien);

        }
    }

}
