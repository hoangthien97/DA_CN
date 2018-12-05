package com.thienthien97.thstore.View.HienThiSPTheoDanhMuc;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.thienthien97.thstore.Adapter.TopDienThoaiAdapter;
import com.thienthien97.thstore.Model.ObjectClass.LoadMoreScroll;
import com.thienthien97.thstore.Model.ObjectClass.SanPham;
import com.thienthien97.thstore.Model.ObjectClass.iLoadMore;
import com.thienthien97.thstore.Presenter.ChiTietSanPham.PresenterLogiChiTietSanPham;
import com.thienthien97.thstore.Presenter.HienThiSPTheoDanhMuc.PresenterLogicHienThiSPTheoDanhMuc;
import com.thienthien97.thstore.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HienThiSPTheoDanhMucActivity extends Fragment implements ViewHienThiSPTheoDanhMuc, iLoadMore {

    RecyclerView recyclerView;
    Button btnDoiKieuXem, btnSapXep;
    boolean dangGrid = true;
    RecyclerView.LayoutManager layoutManager;
    PresenterLogicHienThiSPTheoDanhMuc spTheoDanhMuc;
    int masp;
    TopDienThoaiAdapter topDienThoaiAdapter;
    boolean kt, onPause = false;
    Toolbar toolbar;
    List<SanPham> sanPhamList1 = new ArrayList<>();
    ProgressBar progressBar;
    TextView txtGioHang;
    //Spinner spinner;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_hien_thi_sptheo_danh_muc, container, false);

        setHasOptionsMenu(false);

        recyclerView = view.findViewById(R.id.recyclerHienThiSPTheoDanhMuc);
        btnDoiKieuXem = view.findViewById(R.id.btnDoiKieuXem);
        btnSapXep = view.findViewById(R.id.btnSapxep);
        toolbar = view.findViewById(R.id.toolbarHienThiSPDM);
        progressBar = view.findViewById(R.id.spdm_progress_bar);
        //spinner = view.findViewById(R.id.spinner);

        Bundle bundle = getArguments();
        masp = bundle.getInt("MALOAI",0);
        String tensp = bundle.getString("TENLOAI");
        final boolean kt = bundle.getBoolean("KIEMTRA",false);

        spTheoDanhMuc = new PresenterLogicHienThiSPTheoDanhMuc(this);
        spTheoDanhMuc.LayDanhSachSPTheoMaLoai(masp,kt);

        toolbar.setTitle(tensp);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack("TrangChu", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });



        btnDoiKieuXem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dangGrid  = !dangGrid;
                spTheoDanhMuc.LayDanhSachSPTheoMaLoai(masp,kt);
            }
        });

        btnSapXep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SapXepTheoGia();
                topDienThoaiAdapter.notifyDataSetChanged();
            }
        });

        return view;
    }

    private void SapXepTheoGia(){
        Collections.sort(sanPhamList1, new Comparator<SanPham>() {
            @Override
            public int compare(SanPham sp1, SanPham sp2) {
                if(sp1.getGIA() > sp2.getGIA()){
                    return 1;
                }else if(sp1.getGIA() < sp2.getGIA()){
                    return -1;
                }else{
                    return 0;
                }
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_trangchu,menu);

        MenuItem itGioHang = menu.findItem(R.id.item_GioHang);
        View giaoDienCustomGioHang = MenuItemCompat.getActionView(itGioHang);
        txtGioHang = giaoDienCustomGioHang.findViewById(R.id.txtSLSPGioHang);
        PresenterLogiChiTietSanPham presenterLogiChiTietSanPham = new PresenterLogiChiTietSanPham();
        txtGioHang.setText(String.valueOf(presenterLogiChiTietSanPham.DemSanPhamTrongGioHang(getContext())));

    }


    @Override
    public void HienThiDSSP(List<SanPham> sanPhamList) {
        sanPhamList1 = sanPhamList;

        if(dangGrid){
            layoutManager = new GridLayoutManager(getContext(),2);
            topDienThoaiAdapter = new TopDienThoaiAdapter(getContext(), R.layout.custom_layout_topdtvamaytinhbang,sanPhamList1);
        }else {
            layoutManager = new LinearLayoutManager(getContext());
            topDienThoaiAdapter = new TopDienThoaiAdapter(getContext(), R.layout.custom_layout_list_topdtvamaytinhbang,sanPhamList1);
        }

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(topDienThoaiAdapter);
        recyclerView.addOnScrollListener(new LoadMoreScroll(layoutManager, this));
        topDienThoaiAdapter.notifyDataSetChanged();
    }

    @Override
    public void LoadMore(int tongitem) {
        List<SanPham> listsanPhamsLoadMore = spTheoDanhMuc.LayDanhSachSPTheoMaLoaiLoadMore(masp,kt,tongitem,progressBar);
        sanPhamList1.addAll(listsanPhamsLoadMore);

        topDienThoaiAdapter.notifyDataSetChanged();
    }

}

//    private void SapXep() {
//
//        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
//                R.array.planets_array, android.R.layout.simple_spinner_item);
//
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        spinner.setAdapter(adapter);
//
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                if(i == 0){
//                    SapXepTheoTen();
//                    topDienThoaiAdapter.notifyDataSetChanged();
//                }else{
//                    SapXepTheoGia();
//                    topDienThoaiAdapter.notifyDataSetChanged();
//                }
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//                topDienThoaiAdapter.notifyDataSetChanged();
//            }
//        });
//    }
//
//    private void SapXepTheoTen() {
//        Collections.sort(sanPhamList1, new Comparator<SanPham>() {
//            @Override
//            public int compare(SanPham sp1, SanPham sp2) {
//                return sp1.getTENSP().compareTo(sp2.getTENSP());
//            }
//        });
//    }
//