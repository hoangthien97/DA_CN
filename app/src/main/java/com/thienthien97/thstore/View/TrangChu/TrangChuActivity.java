package com.thienthien97.thstore.View.TrangChu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.thienthien97.thstore.Adapter.ExpandAdapter;
import com.thienthien97.thstore.Adapter.ViewpagerAdapter;
import com.thienthien97.thstore.Model.DangNhap_DangKy.ModelDangNhap;
import com.thienthien97.thstore.Model.ObjectClass.LoaiSanPham;
import com.thienthien97.thstore.Presenter.ChiTietSanPham.PresenterLogiChiTietSanPham;
import com.thienthien97.thstore.Presenter.TrangChu.XuLyMenu.PresenterLogicXulyMenu;
import com.thienthien97.thstore.R;
import com.thienthien97.thstore.View.DangNhap_DangKy.DangNhapActivity;
import com.thienthien97.thstore.View.GioHang.GioHangActivity;
import com.thienthien97.thstore.View.ThongTinDonHang.ThongTinDonHangActivity;
import com.thienthien97.thstore.View.TimKiem.TimKiemActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class TrangChuActivity extends AppCompatActivity implements ViewXuLyMenu, GoogleApiClient.OnConnectionFailedListener, AppBarLayout.OnOffsetChangedListener {

    public static final String SERVER_NAME = "http://noobstore.000webhostapp.com/thshop/loaisanpham.php";
    public static final String SERVER = "http://noobstore.000webhostapp.com/thshop";

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewpagerAdapter adapter;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    ExpandableListView expandableListView;
    PresenterLogicXulyMenu logicXulyMenu;
    String tennguoidung = "";
    AccessToken accessToken;
    Menu menu;
    ModelDangNhap modelDangNhap;
    MenuItem menuItDangnhap, menuItDangxuat;
    GoogleApiClient googleApiClient;
    GoogleSignInResult googleSignInResult;
    CollapsingToolbarLayout collapsingToolbarLayout;
    AppBarLayout appBarLayout;
    TextView txtGioHang;
    Button btnSearch;
    boolean onPause = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_trang_chu);

        toolbar = findViewById(R.id.trangchu_toolbar);
        tabLayout = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.viewpager);
        drawerLayout = findViewById(R.id.drawerLayout);
        expandableListView = findViewById(R.id.epMenu);
        appBarLayout = findViewById(R.id.appbar);
        collapsingToolbarLayout = findViewById(R.id.collapsToolbar);
        btnSearch = findViewById(R.id.search_btn);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerToggle.syncState();

        adapter = new ViewpagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        logicXulyMenu = new PresenterLogicXulyMenu(this);
        modelDangNhap = new ModelDangNhap();

        logicXulyMenu.LayDSMenu();

        googleApiClient = modelDangNhap.LayGgAPIClient(this, this);

        appBarLayout.addOnOffsetChangedListener(this);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentTimkiem = new Intent(TrangChuActivity.this, TimKiemActivity.class);
                startActivity(intentTimkiem);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_trangchu,menu);
        this.menu = menu;

        MenuItem itGioHang = menu.findItem(R.id.item_GioHang);
        View giaoDienCustomGioHang = MenuItemCompat.getActionView(itGioHang);
        txtGioHang = giaoDienCustomGioHang.findViewById(R.id.txtSLSPGioHang);
        PresenterLogiChiTietSanPham presenterLogiChiTietSanPham = new PresenterLogiChiTietSanPham();
        txtGioHang.setText(String.valueOf(presenterLogiChiTietSanPham.DemSanPhamTrongGioHang(getApplicationContext())));

        giaoDienCustomGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentGioHang = new Intent(TrangChuActivity.this, GioHangActivity.class);
                startActivity(intentGioHang);
            }
        });

        menuItDangnhap = menu.findItem(R.id.item_DangNhap);
        menuItDangxuat = menu.findItem(R.id.item_Dangxuat);

        accessToken = logicXulyMenu.LayTokenFB();
        googleSignInResult = modelDangNhap.LayThongTinDangNhapGG(googleApiClient);

        //kiểm tra token fabcebook để lấy tên đăng nhập facebook
        if(accessToken != null) {
            GraphRequest graphRequest = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
                @Override
                public void onCompleted(JSONObject object, GraphResponse response) {
                    try {
                        tennguoidung = object.getString("name");

                        menuItDangnhap.setTitle(tennguoidung);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

            Bundle parameter = new Bundle();
            parameter.putString("fields", "name");

            graphRequest.setParameters(parameter);
            graphRequest.executeAsync();
        }

        //kiểm tra account google để lấy tên đăng nhập google
        if(googleSignInResult != null){
            menuItDangnhap.setTitle(googleSignInResult.getSignInAccount().getDisplayName());
        }

        String tennv = modelDangNhap.LayCacheDangNhap(this);
        if(!tennv.equals("")){
            menuItDangnhap.setTitle(tennv);
        }

        //kiểm tra đăng xuất tài khoản
        if(accessToken != null || googleSignInResult != null || !tennv.equals("") ){
            menuItDangxuat.setVisible(true);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item)){
            return true;
        }

        int id = item.getItemId();
        switch (id){
            case R.id.item_DangNhap:
                if(accessToken == null && googleSignInResult == null && modelDangNhap.LayCacheDangNhap(this).equals("")) {
                    Intent intentDangNhap = new Intent(this, DangNhapActivity.class);
                    startActivity(intentDangNhap);
                }break;

            case R.id.item_Dangxuat:
                if(accessToken != null){
                    LoginManager.getInstance().logOut();
                    this.menu.clear();
                    this.onCreateOptionsMenu(menu);
                }

                if(googleSignInResult != null){
                    Auth.GoogleSignInApi.signOut(googleApiClient);
                    this.menu.clear();
                    this.onCreateOptionsMenu(menu);
                }

                if(!modelDangNhap.LayCacheDangNhap(this).equals("")){
                    modelDangNhap.CapNhatCache(this, "");
                    this.menu.clear();
                    this.onCreateOptionsMenu(menu);
                }
                break;

            case R.id.item_search:
                Intent intentTimkiem = new Intent(TrangChuActivity.this, TimKiemActivity.class);
                startActivity(intentTimkiem);
                break;

            case R.id.item_DonHang:
                Intent intentDonHang = new Intent(TrangChuActivity.this, ThongTinDonHangActivity.class);
                startActivity(intentDonHang);
                break;
        }

        return true;
    }

    @Override
    public void HienThiDSMenu(List<LoaiSanPham> loaiSanPhamList) {
        ExpandAdapter expandAdapter = new ExpandAdapter(this, loaiSanPhamList);
        expandableListView.setAdapter(expandAdapter);
        expandAdapter.notifyDataSetChanged();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

        if(collapsingToolbarLayout.getHeight() + verticalOffset <=  1.5 * ViewCompat.getMinimumHeight(collapsingToolbarLayout)){
            LinearLayout linearLayout = appBarLayout.findViewById(R.id.lnSearch);
            linearLayout.animate().alpha(0).setDuration(200);

            MenuItem itSearch = menu.findItem(R.id.item_search);
            itSearch.setVisible(true);

        }else{
            LinearLayout linearLayout = appBarLayout.findViewById(R.id.lnSearch);
            linearLayout.animate().alpha(1).setDuration(200);
            try{
                MenuItem itSearch = menu.findItem(R.id.item_search);
                itSearch.setVisible(false);
            }catch (Exception e){

            }

        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(onPause){
            PresenterLogiChiTietSanPham presenterLogiChiTietSanPham = new PresenterLogiChiTietSanPham();
            txtGioHang.setText(String.valueOf(presenterLogiChiTietSanPham.DemSanPhamTrongGioHang(getApplicationContext())));
        }

    }

    @Override
    protected void onPause() {
        super.onPause();

        onPause = true;
    }
}
