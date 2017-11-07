package com.smim.smimandroid.widget;

import android.graphics.Rect;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by kakoapps on 2017-06-07.
 */

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {

    private int space_left, space_right, space_bottom;

    public SpacesItemDecoration(int space) {
        this.space_left = space;
        this.space_right = space;
        this.space_bottom = space;
    }

    public SpacesItemDecoration(int space_left, int space_right, int space_bottom) {
        this.space_left = space_left;
        this.space_right = space_right;
        this.space_bottom = space_bottom;
    }



    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {
        outRect.left = space_left;
        outRect.right = space_right;
        outRect.bottom = space_bottom;
        outRect.top = 0;

        // Add top margin only for the first item to avoid double space between items
//        if (parent.getChildLayoutPosition(view) == 0) {
//            outRect.top = space;
//        } else {
//            outRect.top = 0;
//        }
    }

}
