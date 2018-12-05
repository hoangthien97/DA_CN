package com.thienthien97.thstore.View.TrangChu.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.thienthien97.thstore.Adapter.DienTuAdapter;
import com.thienthien97.thstore.Adapter.ThuongHieuDienTuAdapter;
import com.thienthien97.thstore.Adapter.TopDienThoaiAdapter;
import com.thienthien97.thstore.Model.ObjectClass.DienTu;
import com.thienthien97.thstore.Model.ObjectClass.SanPham;
import com.thienthien97.thstore.Model.ObjectClass.ThuongHieu;
import com.thienthien97.thstore.Presenter.TrangChu_DienTu.PresenterLogicDienTu;
import com.thienthien97.thstore.R;
import com.thienthien97.thstore.View.TrangChu.ViewDienTu;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DienTuFragment extends Fragment implements ViewDienTu{

    RecyclerView recyclerView, recyclerViewThuongHieuDienTu, recyclerViewHangMoi;
    List<DienTu> dienTuList;
    PresenterLogicDienTu presenterLogicDienTu;
    ImageView imgSP1, imgSP2, imgSP3;
    TextView txtSP1, txtSP2, txtSP3;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dien_tu, container, false);

        recyclerView = view.findViewById(R.id.recyclerDienTu);
        recyclerViewThuongHieuDienTu = view.findViewById(R.id.recyclerTopCacThuongHieuLon);
        recyclerViewHangMoi = view.findViewById(R.id.recyclerHangMoiVe);
        imgSP1 = view.findViewById(R.id.imgTV1);
        imgSP2 = view.findViewById(R.id.imgTV2);
        imgSP3 = view.findViewById(R.id.imgTV3);
        txtSP1  = view.findViewById(R.id.txtSP1);
        txtSP2  = view.findViewById(R.id.txtSP2);
        txtSP3  = view.findViewById(R.id.txtSP3);

        presenterLogicDienTu = new PresenterLogicDienTu(this);
        dienTuList = new ArrayList<>();

        presenterLogicDienTu.LayDanhSachDienTu();
        presenterLogicDienTu.LayLogoThuongHieuDienTu();
        presenterLogicDienTu.LayDanhSachSanPhamMoi();

        return view;
    }

    @Override
    public void HienThiDanhSach(List<DienTu> dienTus) {

        dienTuList = dienTus;

        DienTuAdapter dienTuAdapter = new DienTuAdapter(getContext(), dienTuList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(dienTuAdapter);

        dienTuAdapter.notifyDataSetChanged();
    }

    @Override
    public void HienThiLogoDienTu(List<ThuongHieu> thuongHieus) {

        ThuongHieuDienTuAdapter thuongHieuDienTuAdapter = new ThuongHieuDienTuAdapter(getContext(), thuongHieus);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),2, GridLayoutManager.HORIZONTAL,false);
        recyclerViewThuongHieuDienTu.setLayoutManager(layoutManager);
        recyclerViewThuongHieuDienTu.setAdapter(thuongHieuDienTuAdapter);

        thuongHieuDienTuAdapter.notifyDataSetChanged();
    }

    @Override
    public void HienThiDSSanPhamMoi(List<SanPham> sanPhams) {
        TopDienThoaiAdapter topDienThoaiAdapter = new TopDienThoaiAdapter(getContext(), R.layout.custom_layout_topdtvamaytinhbang, sanPhams);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewHangMoi.setLayoutManager(layoutManager);
        recyclerViewHangMoi.setAdapter(topDienThoaiAdapter);

        topDienThoaiAdapter.notifyDataSetChanged();

        Random random = new Random();
        int vt = random.nextInt(sanPhams.size());
        Picasso.with(getContext()).load(sanPhams.get(vt).getANHLON()).fit().centerInside().into(imgSP1);
        txtSP1.setText(sanPhams.get(vt).getTENSP());

        int vt1 = random.nextInt(sanPhams.size());
        Picasso.with(getContext()).load(sanPhams.get(vt1).getANHLON()).fit().centerInside().into(imgSP2);
        txtSP2.setText(sanPhams.get(vt1).getTENSP());

        int vt2 = random.nextInt(sanPhams.size());
        Picasso.with(getContext()).load(sanPhams.get(vt2).getANHLON()).fit().centerInside().into(imgSP3);
        txtSP3.setText(sanPhams.get(vt2).getTENSP());
    }
}
