package com.thienthien97.thstore.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.thienthien97.thstore.View.TrangChu.Fragment.CTKMFragment;
import com.thienthien97.thstore.View.TrangChu.Fragment.DienTuFragment;
import com.thienthien97.thstore.View.TrangChu.Fragment.LamDepFragment;
import com.thienthien97.thstore.View.TrangChu.Fragment.MeVaBeFragment;
import com.thienthien97.thstore.View.TrangChu.Fragment.NhaCuaVaDoiSongFragment;
import com.thienthien97.thstore.View.TrangChu.Fragment.NoiBatFragment;
import com.thienthien97.thstore.View.TrangChu.Fragment.TheThaoVaDuLichFragment;
import com.thienthien97.thstore.View.TrangChu.Fragment.ThoiTrangFragment;
import com.thienthien97.thstore.View.TrangChu.Fragment.ThuongHieuFragment;

import java.util.ArrayList;
import java.util.List;

public class ViewpagerAdapter extends FragmentPagerAdapter {

    List<Fragment> fragmentList = new ArrayList<Fragment>();
    List<String> titleFragment = new ArrayList<String>();

    public ViewpagerAdapter(FragmentManager fm) {
        super(fm);

        fragmentList.add(new NoiBatFragment());
        fragmentList.add(new CTKMFragment());
        fragmentList.add(new DienTuFragment());
        fragmentList.add(new NhaCuaVaDoiSongFragment());
        fragmentList.add(new MeVaBeFragment());
        fragmentList.add(new LamDepFragment());
        fragmentList.add(new ThoiTrangFragment());
        fragmentList.add(new TheThaoVaDuLichFragment());
        fragmentList.add(new ThuongHieuFragment());

        titleFragment.add("Nổi bật");
        titleFragment.add("Chương trình khuyến mãi");
        titleFragment.add("Điện tử");
        titleFragment.add("Nhà cửa & đời sống");
        titleFragment.add("Mẹ & bé");
        titleFragment.add("Làm đẹp");
        titleFragment.add("Thời trang");
        titleFragment.add("Thể thao & du lịch");
        titleFragment.add("Thương hiệu");

    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titleFragment.get(position);
    }
}
