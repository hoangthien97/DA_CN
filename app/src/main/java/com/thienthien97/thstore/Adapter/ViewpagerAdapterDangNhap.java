package com.thienthien97.thstore.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.thienthien97.thstore.View.DangNhap_DangKy.Fragment.DangKyFragment;
import com.thienthien97.thstore.View.DangNhap_DangKy.Fragment.DangNhapFragment;

public class ViewpagerAdapterDangNhap extends FragmentPagerAdapter {

    public ViewpagerAdapterDangNhap(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                DangNhapFragment dangNhapFragment = new DangNhapFragment();
                return  dangNhapFragment;
            case 1:
                DangKyFragment dangKyFragment = new DangKyFragment();
                return  dangKyFragment;

                default: return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Đăng nhập";
            case 1:
                return "Đăng ký";

                default: return null;
        }
    }
}
