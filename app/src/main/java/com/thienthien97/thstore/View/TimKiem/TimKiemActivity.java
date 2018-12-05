package com.thienthien97.thstore.View.TimKiem;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.thienthien97.thstore.Adapter.TopDienThoaiAdapter;
import com.thienthien97.thstore.Model.ObjectClass.LoadMoreScroll;
import com.thienthien97.thstore.Model.ObjectClass.SanPham;
import com.thienthien97.thstore.Model.ObjectClass.iLoadMore;
import com.thienthien97.thstore.Presenter.TimKiem.PresenterLogicTimKiem;
import com.thienthien97.thstore.R;

import java.util.List;

public class TimKiemActivity extends AppCompatActivity implements ViewTimKiem, iLoadMore, SearchView.OnQueryTextListener{

    Toolbar toolbarTimkiem;
    RecyclerView recyclerViewTimkiem;
    PresenterLogicTimKiem presenterLogicTimKiem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim_kiem);

        toolbarTimkiem = findViewById(R.id.toolbarTimKiem);
        recyclerViewTimkiem  = findViewById(R.id.recyclerTimKiem);

        setSupportActionBar(toolbarTimkiem);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        presenterLogicTimKiem = new PresenterLogicTimKiem(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_timkiem, menu);

        MenuItem itSearch = menu.findItem(R.id.item_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(itSearch);
        searchView.setIconified(false);
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public void TimKiemThanhCong(List<SanPham> sanPhamList) {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        TopDienThoaiAdapter topDienThoaiAdapter = new TopDienThoaiAdapter(this, R.layout.custom_layout_list_topdtvamaytinhbang,sanPhamList);

        recyclerViewTimkiem.setLayoutManager(layoutManager);
        recyclerViewTimkiem.setAdapter(topDienThoaiAdapter);
        recyclerViewTimkiem.addOnScrollListener(new LoadMoreScroll(layoutManager,this));
        topDienThoaiAdapter.notifyDataSetChanged();
    }

    @Override
    public void TimKiemThatBai() {
        Toast.makeText(this, "Không tìm thấy sản phẩm !", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void LoadMore(int tongtien) {

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        presenterLogicTimKiem.TimKiemSPTheoTen(query,0);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
