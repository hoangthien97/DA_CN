package com.thienthien97.thstore.View.ChiTietSanPham;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.thienthien97.thstore.Adapter.DanhGiaAdapter;
import com.thienthien97.thstore.Adapter.SliderAdapter;
import com.thienthien97.thstore.Model.ObjectClass.ChiTietKhuyenMai;
import com.thienthien97.thstore.Model.ObjectClass.ChiTietSanPham;
import com.thienthien97.thstore.Model.ObjectClass.DanhGia;
import com.thienthien97.thstore.Model.ObjectClass.SanPham;
import com.thienthien97.thstore.Presenter.ChiTietSanPham.PresenterLogiChiTietSanPham;
import com.thienthien97.thstore.R;
import com.thienthien97.thstore.View.DanhGia.DanhSachDanhGiaActivity;
import com.thienthien97.thstore.View.DanhGia.ThemDanhGiaActivity;
import com.thienthien97.thstore.View.GioHang.GioHangActivity;
import com.thienthien97.thstore.View.ThanhToan.ThanhToanActivity;
import com.thienthien97.thstore.View.TrangChu.TrangChuActivity;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class ChiTietSanPhamActivity extends AppCompatActivity implements ViewChiTietSanPham, View.OnClickListener{

    ViewPager viewPager;
    PresenterLogiChiTietSanPham presenterLogiChiTietSanPham;
    TextView[] txtDots;
    LinearLayout linearLayoutDots;
    List<Fragment> fragmentList;
    TextView txtTenSP, txtGia, txtNCC, txtThongTinChiTiet, txtVietDanhGia, txtXemTatCaDanhGia, txtGioHang, txtGiamgia;
    ImageView imgXemChiTiet, imgThemGioHang;
    Button btnMuangay;
    Toolbar toolbar;
    LinearLayout lnThongSoKyThuat;
    boolean ktchitietsp = false;
    int masp;
    RecyclerView recyclerDanhgia;
    RatingBar rbDanhGia;
    SanPham sanphamGioHang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);

        Anhxa();

        setSupportActionBar(toolbar);

        masp = getIntent().getIntExtra("masp",0);
        presenterLogiChiTietSanPham = new PresenterLogiChiTietSanPham(this);
        presenterLogiChiTietSanPham.LayChiTietSanPham(masp);
        presenterLogiChiTietSanPham.LayDanhSachDanhGiaTheoSP(masp,0);

        txtVietDanhGia.setOnClickListener(this);
        txtXemTatCaDanhGia.setOnClickListener(this);
        imgThemGioHang.setOnClickListener(this);
        btnMuangay.setOnClickListener(this);

    }

    @Override
    public void HienThiChiTietSanPham(final SanPham sanPham) {
        masp = sanPham.getMASP();

        sanphamGioHang = sanPham;
        //sanphamGioHang.setSOLUONGTONKHO(sanPham.getSOLUONG());
        txtTenSP.setText(sanPham.getTENSP());

        int giatien = sanPham.getGIA();
        ChiTietKhuyenMai chiTietKhuyenMai = sanPham.getChiTietKhuyenMai();

        if(chiTietKhuyenMai !=null){
            int phamtramkm = chiTietKhuyenMai.getPHANTRAMKM();

            if(phamtramkm != 0){
                NumberFormat numberFormat = new DecimalFormat("###,###");
                String gia = numberFormat.format(giatien);

                txtGiamgia.setVisibility(View.VISIBLE);
                txtGiamgia.setPaintFlags(txtGiamgia.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                txtGiamgia.setText(gia + " VNĐ");

                giatien = giatien * phamtramkm/100;
            }

        }

        NumberFormat numberFormat = new DecimalFormat("###,###");
        String gia = numberFormat.format(giatien);
        txtGia.setText(gia + " VND");
        txtNCC.setText(sanPham.getTENNHANVIEN());
        txtThongTinChiTiet.setText(sanPham.getTHONGTIN().substring(0,110));

        if(sanPham.getTHONGTIN().length() < 110){
            imgXemChiTiet.setVisibility(View.GONE);
        }else{
            imgXemChiTiet.setVisibility(View.VISIBLE);

            imgXemChiTiet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //kiểm tra đóng mở xem chi tiết sản phẩm
                    ktchitietsp = !ktchitietsp;

                    if(ktchitietsp){
                        txtThongTinChiTiet.setText(sanPham.getTHONGTIN());
                        imgXemChiTiet.setImageResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                        lnThongSoKyThuat.setVisibility(View.VISIBLE);
                        HienThongSoKyThuat(sanPham);
                    }else{
                        txtThongTinChiTiet.setText(sanPham.getTHONGTIN().substring(0,110));
                        imgXemChiTiet.setImageResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                        lnThongSoKyThuat.setVisibility(View.GONE);
                    }
                }
            });
        }


    }

    private void HienThongSoKyThuat(SanPham sanPham){
        List<ChiTietSanPham> chiTietSanPhams = sanPham.getChiTietSanPhamList();
        lnThongSoKyThuat.removeAllViews();

        TextView txtThongSoKyThuat = new TextView(this);
        txtThongSoKyThuat.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        lnThongSoKyThuat.addView(txtThongSoKyThuat);

        for(int i=0; i<chiTietSanPhams.size(); i++){

            LinearLayout lnChiTiet = new LinearLayout(this);
            lnChiTiet.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lnChiTiet.setOrientation(LinearLayout.HORIZONTAL);


            TextView txtTenThongSo = new TextView(this);
            txtTenThongSo.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,1.0f));
            txtTenThongSo.setText(chiTietSanPhams.get(i).getTENCHITIET());

            TextView txtGiaTriThongSo = new TextView(this);
            txtGiaTriThongSo.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,1.0f));
            txtGiaTriThongSo.setText(chiTietSanPhams.get(i).getGIATRI());

            lnChiTiet.addView(txtTenThongSo);
            lnChiTiet.addView(txtGiaTriThongSo);

            lnThongSoKyThuat.addView(lnChiTiet);
        }

    }

    @Override
    public void HienThiSliderSanPham(String[] linkHinhSP) {

        fragmentList = new ArrayList<>();

        for(int i=0; i<linkHinhSP.length; i++){

            SliderChiTietSanPhamFragment sliderChiTietSanPhamFragment = new SliderChiTietSanPhamFragment();
            Bundle bundle = new Bundle();
            bundle.putString("linkHinhsp", TrangChuActivity.SERVER + linkHinhSP[i]);
            sliderChiTietSanPhamFragment.setArguments(bundle);

            fragmentList.add(sliderChiTietSanPhamFragment);

        }

        SliderAdapter sliderAdapter = new SliderAdapter(getSupportFragmentManager(),fragmentList);
        viewPager.setAdapter(sliderAdapter);
        sliderAdapter.notifyDataSetChanged();

        CountSlider(0);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                CountSlider(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void HienThiDanhGia(List<DanhGia> danhGiaList) {
        DanhGiaAdapter adapterDanhGia = new DanhGiaAdapter(this, danhGiaList,3);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerDanhgia.setLayoutManager(layoutManager);
        recyclerDanhgia.setAdapter(adapterDanhGia);

        adapterDanhGia.notifyDataSetChanged();
    }

    @Override
    public void ThemGioHangThanhCong() {
        Toast.makeText(this, "Sản phẩm đã được thêm vào giỏ hàng.", Toast.LENGTH_SHORT).show();
        txtGioHang.setText(String.valueOf(presenterLogiChiTietSanPham.DemSanPhamTrongGioHang(this)));
    }

    @Override
    public void ThemGiohangThatbai() {
        Toast.makeText(this, "Sản phẩm đã có trong giỏ  hàng !", Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_trangchu,menu);


        MenuItem itGioHang = menu.findItem(R.id.item_GioHang);
        View giaoDienCustomGioHang = MenuItemCompat.getActionView(itGioHang);
        txtGioHang =  giaoDienCustomGioHang.findViewById(R.id.txtSLSPGioHang);
        txtGioHang.setText(String.valueOf(presenterLogiChiTietSanPham.DemSanPhamTrongGioHang(getApplicationContext())));

        giaoDienCustomGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentGioHang = new Intent(ChiTietSanPhamActivity.this, GioHangActivity.class);
                startActivity(intentGioHang);
            }
        });
        return true;
    }

    //dem fragment hinh san pham
    private void CountSlider(int vitri){
        txtDots = new TextView[fragmentList.size()];

        linearLayoutDots.removeAllViews();

        for (int i=0; i<fragmentList.size(); i++){

            txtDots[i] = new TextView(this);
            txtDots[i].setText(Html.fromHtml("&#8226"));
            txtDots[i].setTextSize(40);
            txtDots[i].setTextColor(getIdColor(R.color.colorSliderActive));

            linearLayoutDots.addView(txtDots[i]);
        }

        txtDots[vitri].setTextColor(getIdColor(R.color.bgToolbar));
    }

    private int getIdColor(int idcolor){
        int color = 0;

        if(Build.VERSION.SDK_INT > 21){
            color = ContextCompat.getColor(this, idcolor);
        }else{
            color = getResources().getColor(idcolor);
        }

        return color;
    }

    private void Anhxa() {
        viewPager = findViewById(R.id.viewpagerSlider);
        linearLayoutDots = findViewById(R.id.layoutDots);
        txtTenSP = findViewById(R.id.txtTenSanPham);
        txtGia = findViewById(R.id.txtGiaTien);
        txtNCC = findViewById(R.id.txtTenNCC);
        txtThongTinChiTiet = findViewById(R.id.txtThongTinChiTiet);
        imgXemChiTiet = findViewById(R.id.imgXemThemChiTiet);
        toolbar = findViewById(R.id.toolbarCTSP);
        lnThongSoKyThuat = findViewById(R.id.lnThongSoKyThuat);
        txtVietDanhGia = findViewById(R.id.txtVietDanhGia);
        recyclerDanhgia = findViewById(R.id.recyclerDanhGiaChiTiet);
        txtXemTatCaDanhGia = findViewById(R.id.txtXemTatCaNhanXet);
        rbDanhGia = findViewById(R.id.rbDanhGiaChiTiet);
        imgThemGioHang = findViewById(R.id.imgThemVaoGioHang);
        btnMuangay = findViewById(R.id.btnMuaNgay);
        txtGiamgia = findViewById(R.id.txtGiamGia);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.txtVietDanhGia:
                Intent intentDanhGia = new Intent(ChiTietSanPhamActivity.this, ThemDanhGiaActivity.class);
                intentDanhGia.putExtra("masp",masp);
                startActivity(intentDanhGia);
                break;

            case R.id.txtXemTatCaNhanXet:
                Intent intentDSDanhGia = new Intent(ChiTietSanPhamActivity.this, DanhSachDanhGiaActivity.class);
                intentDSDanhGia.putExtra("masp",masp);
                startActivity(intentDSDanhGia);
                break;

            case R.id.imgThemVaoGioHang:
                Fragment fragment = fragmentList.get(0);
                View v = fragment.getView();
                ImageView imageView = v.findViewById(R.id.imgSlider);
                Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100, byteArrayOutputStream);
                byte[] hinhspgiohang = byteArrayOutputStream.toByteArray();

                sanphamGioHang.setHinhgiohang(hinhspgiohang);
                sanphamGioHang.setSOLUONG(1);
                presenterLogiChiTietSanPham.ThemGioHang(sanphamGioHang, this);
                break;

            case R.id.btnMuaNgay:
                Fragment fragment1 = fragmentList.get(0);
                View v1 = fragment1.getView();
                ImageView imageView1 = v1.findViewById(R.id.imgSlider);
                Bitmap bitmap1 = ((BitmapDrawable) imageView1.getDrawable()).getBitmap();

                ByteArrayOutputStream byteArrayOutputStream1 = new ByteArrayOutputStream();
                bitmap1.compress(Bitmap.CompressFormat.PNG,100, byteArrayOutputStream1);
                byte[] hinhspgiohang1 = byteArrayOutputStream1.toByteArray();

                sanphamGioHang.setHinhgiohang(hinhspgiohang1);
                sanphamGioHang.setSOLUONG(1);
                presenterLogiChiTietSanPham.ThemGioHang(sanphamGioHang, this);

                Intent intentThanhtoan = new Intent(ChiTietSanPhamActivity.this, ThanhToanActivity.class);
                startActivity(intentThanhtoan);
                break;
        }
    }

}
