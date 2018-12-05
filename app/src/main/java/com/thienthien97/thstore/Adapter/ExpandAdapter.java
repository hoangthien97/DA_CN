package com.thienthien97.thstore.Adapter;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.thienthien97.thstore.Model.ObjectClass.LoaiSanPham;
import com.thienthien97.thstore.Model.TrangChu.XuLyMenu.XuLyJSONMenu;
import com.thienthien97.thstore.R;
import com.thienthien97.thstore.View.HienThiSPTheoDanhMuc.HienThiSPTheoDanhMucActivity;

import java.util.List;

public class ExpandAdapter extends BaseExpandableListAdapter {

    Context context;
    List<LoaiSanPham> loaiSanPhams;
    ViewHolderMenu viewHolderMenu;

    public ExpandAdapter(Context context, List<LoaiSanPham> loaiSanPhams){
        this.context = context;
        this.loaiSanPhams = loaiSanPhams;

        XuLyJSONMenu xuLyJSONMenu = new XuLyJSONMenu();

        int count = loaiSanPhams.size();
        for(int i=0; i<count; i++){
            int maloaisp = loaiSanPhams.get(i).getMALOAISP();
            loaiSanPhams.get(i).setListCon(xuLyJSONMenu.LayLoaiSanPhamTheoMaLoai(maloaisp));
        }
    }

    @Override
    public int getGroupCount() {
        return loaiSanPhams.size();
    }

    @Override
    public int getChildrenCount(int capCha) {
        if(loaiSanPhams.get(capCha).getListCon().size() != 0) {
            return 1;
        }else{
            return 0;
        }

    }

    @Override
    public Object getGroup(int capCha) {
        return loaiSanPhams.get(capCha);
    }

    @Override
    public Object getChild(int capCha, int capCon) {
        return loaiSanPhams.get(capCha).getListCon().get(capCon);
    }

    @Override
    public long getGroupId(int capCha) {
        return loaiSanPhams.get(capCha).getMALOAISP();
    }

    @Override
    public long getChildId(int capCha, int capCon) {
        return loaiSanPhams.get(capCha).getListCon().get(capCon).getMALOAISP();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    public class ViewHolderMenu{
        TextView txtTenLoaiSP;
        ImageView imgMenu;
    }

    @Override
    public View getGroupView(final int capCha, boolean isExpanded, View view, ViewGroup viewGroup) {

        View viewCapCha = view;

        if(view == null){

            viewHolderMenu = new ViewHolderMenu();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            viewCapCha = layoutInflater.inflate(R.layout.custom_menu_cap_cha, viewGroup, false);

            viewHolderMenu.txtTenLoaiSP = viewCapCha.findViewById(R.id.txtTenLoaiSP);
            viewHolderMenu.imgMenu = viewCapCha.findViewById(R.id.imgMenuPlus);

            viewCapCha.setTag(viewHolderMenu);
        }else{
            viewHolderMenu = (ViewHolderMenu) viewCapCha.getTag();
        }

        viewHolderMenu.txtTenLoaiSP.setText(loaiSanPhams.get(capCha).getTENLOAISP());

        int countcapcon = loaiSanPhams.get(capCha).getListCon().size();
        if(countcapcon > 0){
            viewHolderMenu.imgMenu.setVisibility(View.VISIBLE);
        }else{
            viewHolderMenu.imgMenu.setVisibility(View.INVISIBLE);
        }

        if(isExpanded){
            viewHolderMenu.imgMenu.setImageResource(R.drawable.ic_remove_black_24dp);
            viewCapCha.setBackgroundResource(R.color.colorGray);
        }else{
            viewHolderMenu.imgMenu.setImageResource(R.drawable.ic_add_black_24dp);
            viewCapCha.setBackgroundColor(00000);
        }

        //Item Menu click
        viewCapCha.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                HienThiSPTheoDanhMucActivity hienThiSPTheoDanhMucActivity = new HienThiSPTheoDanhMucActivity();

                Bundle bundle = new Bundle();
                bundle.putInt("MALOAI", loaiSanPhams.get(capCha).getMALOAISP());
                bundle.putBoolean("KIEMTRA",false);
                bundle.putString("TENLOAI", loaiSanPhams.get(capCha).getTENLOAISP());
                hienThiSPTheoDanhMucActivity.setArguments(bundle);

                fragmentTransaction.addToBackStack("TrangChu");
                fragmentTransaction.replace(R.id.themFragment, hienThiSPTheoDanhMucActivity);
                fragmentTransaction.commit();

                return false;
            }
        });

        return viewCapCha;
    }

    @Override
    public View getChildView(int capCha, int capCon, boolean isExpanded, View view, ViewGroup viewGroup) {
//        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View viewCapCon = layoutInflater.inflate(R.layout.custom_menu_cap_con, viewGroup, false);
//
//        ExpandableListView expandableListView = viewCapCon.findViewById(R.id.epMenuCon);

        SecondExpandable secondExpandable = new SecondExpandable(context);
        ExpandAdapter secondAdapter = new ExpandAdapter(context, loaiSanPhams.get(capCha).getListCon());
        secondExpandable.setAdapter(secondAdapter);

        secondExpandable.setGroupIndicator(null);
        notifyDataSetChanged();

        return secondExpandable;
    }

    public class SecondExpandable extends ExpandableListView{

        public SecondExpandable(Context context) {
            super(context);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Display display = windowManager.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);

            int width = size.x;
            int height = size.y;

//            widthMeasureSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.AT_MOST);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }

}


//    public class SecondAdapter extends BaseExpandableListAdapter{
//
//        List<LoaiSanPham> listCon;
//        public SecondAdapter(List<LoaiSanPham> listCon){
//            this.listCon = listCon;
//
//            XuLyJSONMenu xuLyJSONMenu = new XuLyJSONMenu();
//
//            int count = listCon.size();
//            for(int i=0; i<count; i++){
//                int maloaisp = listCon.get(i).getMALOAISP();
//                listCon.get(i).setListCon(xuLyJSONMenu.LayLoaiSanPhamTheoMaLoai(maloaisp));
//            }
//        }
//
//        @Override
//        public int getGroupCount() {
//            return listCon.size();
//        }
//
//        @Override
//        public int getChildrenCount(int capCha) {
//            return listCon.get(capCha).getListCon().size();
//
//        }
//
//        @Override
//        public Object getGroup(int capCha) {
//            return listCon.get(capCha);
//        }
//
//        @Override
//        public Object getChild(int capCha, int capCon) {
//            return listCon.get(capCha).getListCon().get(capCon);
//        }
//
//        @Override
//        public long getGroupId(int capCha) {
//            return listCon.get(capCha).getMALOAISP();
//        }
//
//        @Override
//        public long getChildId(int capCha, int capCon) {
//            return listCon.get(capCha).getListCon().get(capCon).getMALOAISP();
//        }
//
//        @Override
//        public boolean hasStableIds() {
//            return false;
//        }
//
//        @Override
//        public View getGroupView(int capCha, boolean isExpanded, View view, ViewGroup viewGroup) {
//            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            View viewCapCha = layoutInflater.inflate(R.layout.custom_menu_cap_cha, viewGroup, false);
//            TextView txtTenLoaiSP = viewCapCha.findViewById(R.id.txtTenLoaiSP);
//            txtTenLoaiSP.setText(listCon.get(capCha).getTENLOAISP());
//
//            return viewCapCha;
//        }
//
//        @Override
//        public View getChildView(int capCha, int capCon, boolean isExpanded, View view, ViewGroup viewGroup) {
//            TextView textView = new TextView(context);
//            textView.setText(listCon.get(capCha).getListCon().get(capCon).getTENLOAISP());
//            textView.setPadding(15,5,5,5);
//            textView.setLayoutParams(new ListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
//            return textView;
//        }
//
//
//        @Override
//        public boolean isChildSelectable(int i, int i1) {
//            return false;
//        }
//    }
