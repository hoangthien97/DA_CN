package com.thienthien97.thstore.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thienthien97.thstore.Model.ObjectClass.KhuyenMai;
import com.thienthien97.thstore.R;

import java.util.List;

public class DSKhuyenMaiAdapter extends RecyclerView.Adapter<DSKhuyenMaiAdapter.ViewHolderKM> {

    Context context;
    List<KhuyenMai> khuyenMaiList;

    public DSKhuyenMaiAdapter(Context context, List<KhuyenMai> khuyenMaiList){
        this.context = context;
        this.khuyenMaiList = khuyenMaiList;
    }

    public class ViewHolderKM extends RecyclerView.ViewHolder {

        RecyclerView recyclerViewItemKM;
        TextView txtTieudeKM;

        public ViewHolderKM(View itemView) {
            super(itemView);

            recyclerViewItemKM = itemView.findViewById(R.id.recyclerItemKhuyenMai);
            txtTieudeKM = itemView.findViewById(R.id.txtTieuDeKM);
        }
    }

    @NonNull
    @Override
    public ViewHolderKM onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_layout_recycler_itemkhuyenmai, parent, false);

        ViewHolderKM viewHolderKM = new ViewHolderKM(view);
        return viewHolderKM;
    }

    @Override
    public void onBindViewHolder(@NonNull DSKhuyenMaiAdapter.ViewHolderKM holder, int position) {
        KhuyenMai khuyenMai = khuyenMaiList.get(position);

        holder.txtTieudeKM.setText(khuyenMai.getTENLOAISP());
        TopDienThoaiAdapter topDienThoaiAdapter = new TopDienThoaiAdapter(context, R.layout.custom_layout_topdtvamaytinhbang, khuyenMai.getDSSPKhuyenMai());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        holder.recyclerViewItemKM.setLayoutManager(layoutManager);
        holder.recyclerViewItemKM.setAdapter(topDienThoaiAdapter);
    }

    @Override
    public int getItemCount() {
        return khuyenMaiList.size();
    }

}
