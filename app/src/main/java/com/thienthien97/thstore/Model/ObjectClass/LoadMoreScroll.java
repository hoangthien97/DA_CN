package com.thienthien97.thstore.Model.ObjectClass;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class LoadMoreScroll extends RecyclerView.OnScrollListener {

    int itemdau = 0;
    int tongitem = 0;
    int itemloadtruoc = 10;
    RecyclerView.LayoutManager layoutManager;
    iLoadMore ILoadMore;

    public LoadMoreScroll(RecyclerView.LayoutManager layoutManager, iLoadMore ILoadMore){
        this.layoutManager = layoutManager;
        this.ILoadMore = ILoadMore;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        tongitem = layoutManager.getItemCount();

        if(layoutManager instanceof LinearLayoutManager){
            itemdau = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
        }else if(layoutManager instanceof GridLayoutManager){
            itemdau  = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
        }

        if(tongitem <= (itemdau + itemloadtruoc)){
            ILoadMore.LoadMore(tongitem );
        }
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }
}
