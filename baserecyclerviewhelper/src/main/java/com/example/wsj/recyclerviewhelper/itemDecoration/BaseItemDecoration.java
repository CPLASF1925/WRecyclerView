package com.example.wsj.recyclerviewhelper.itemDecoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by wangshijia on 2017/6/28 下午3:37.
 * Copyright (c) 2017. alpha, Inc. All rights reserved.
 */

public abstract class BaseItemDecoration extends RecyclerView.ItemDecoration {

    private Paint mPaint;

    private Context context;

    public BaseItemDecoration(Context context) {
        this.context = context;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            int viewLayoutPosition = ((RecyclerView.LayoutParams) child.getLayoutParams()).getViewLayoutPosition();
            int itemCount = parent.getAdapter().getItemCount();
            Divide divide = getDivider(viewLayoutPosition,itemCount);

            if (divide.getLeftSideLine().isHave()) {
                int lineWidthPx = Dp2Px.convert(parent.getContext(), divide.getLeftSideLine().getWidthDp());
                int startPaddingPx = Dp2Px.convert(parent.getContext(), divide.getLeftSideLine().getStartPaddingDp());
                int endPaddingPx = Dp2Px.convert(parent.getContext(), divide.getLeftSideLine().getEndPaddingDp());
                drawChildLeftVertical(child, c, parent, divide.getLeftSideLine().getColor(), lineWidthPx, startPaddingPx, endPaddingPx);
            }
            if (divide.getTopSideLine().isHave()) {
                int lineWidthPx = Dp2Px.convert(parent.getContext(), divide.getTopSideLine().getWidthDp());
                int startPaddingPx = Dp2Px.convert(parent.getContext(), divide.getTopSideLine().getStartPaddingDp());
                int endPaddingPx = Dp2Px.convert(parent.getContext(), divide.getTopSideLine().getEndPaddingDp());
                drawChildTopHorizontal(child, c, parent, divide.getTopSideLine().getColor(), lineWidthPx, startPaddingPx, endPaddingPx);
            }
            if (divide.getRightSideLine().isHave()) {
                int lineWidthPx = Dp2Px.convert(parent.getContext(), divide.getRightSideLine().getWidthDp());
                int startPaddingPx = Dp2Px.convert(parent.getContext(), divide.getRightSideLine().getStartPaddingDp());
                int endPaddingPx = Dp2Px.convert(parent.getContext(), divide.getRightSideLine().getEndPaddingDp());
                drawChildRightVertical(child, c, parent, divide.getRightSideLine().getColor(), lineWidthPx, startPaddingPx, endPaddingPx);
            }
            if (divide.getBottomSideLine().isHave()) {
                int lineWidthPx = Dp2Px.convert(parent.getContext(), divide.getBottomSideLine().getWidthDp());
                int startPaddingPx = Dp2Px.convert(parent.getContext(), divide.getBottomSideLine().getStartPaddingDp());
                int endPaddingPx = Dp2Px.convert(parent.getContext(), divide.getBottomSideLine().getEndPaddingDp());
                drawChildBottomHorizontal(child, c, parent, divide.getBottomSideLine().getColor(), lineWidthPx, startPaddingPx, endPaddingPx);
            }
        }
    }

    private void drawChildBottomHorizontal(View child, Canvas c, RecyclerView parent, int color, int lineWidthPx, int startPaddingPx, int endPaddingPx) {
        int leftPadding = 0;
        int rightPadding = 0;

        if (startPaddingPx <= 0) {
            //padding<0当作==0处理
            //上下左右默认分割线的两头都出头一个分割线的宽度，避免十字交叉的时候，交叉点是空白
            leftPadding = -lineWidthPx;
        } else {
            leftPadding = startPaddingPx;
        }

        if (endPaddingPx <= 0) {
            rightPadding = lineWidthPx;
        } else {
            rightPadding = -endPaddingPx;
        }

        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                .getLayoutParams();
        int left = child.getLeft() - params.leftMargin + leftPadding;
        int right = child.getRight() + params.rightMargin + rightPadding;
        int top = child.getBottom() + params.bottomMargin;
        int bottom = top + lineWidthPx;
        mPaint.setColor(color);

        c.drawRect(left, top, right, bottom, mPaint);
    }

    private void drawChildTopHorizontal(View child, Canvas c, RecyclerView parent, int color, int lineWidthPx, int startPaddingPx, int endPaddingPx) {
        int leftPadding = 0;
        int rightPadding = 0;

        if (startPaddingPx <= 0) {
            //padding<0当作==0处理
            //上下左右默认分割线的两头都出头一个分割线的宽度，避免十字交叉的时候，交叉点是空白
            leftPadding = -lineWidthPx;
        } else {
            leftPadding = startPaddingPx;
        }
        if (endPaddingPx <= 0) {
            rightPadding = lineWidthPx;
        } else {
            rightPadding = -endPaddingPx;
        }

        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                .getLayoutParams();
        int left = child.getLeft() - params.leftMargin + leftPadding;
        int right = child.getRight() + params.rightMargin + rightPadding;
        int bottom = child.getTop() - params.topMargin;
        int top = bottom - lineWidthPx;
        mPaint.setColor(color);

        c.drawRect(left, top, right, bottom, mPaint);

    }

    private void drawChildRightVertical(View child, Canvas c, RecyclerView parent, int color, int lineWidthPx, int startPaddingPx, int endPaddingPx) {
        int topPadding = 0;
        int bottomPadding = 0;

        if (startPaddingPx <= 0) {
            //padding<0当作==0处理
            //上下左右默认分割线的两头都出头一个分割线的宽度，避免十字交叉的时候，交叉点是空白
            topPadding = -lineWidthPx;
        } else {
            topPadding = startPaddingPx;
        }
        if (endPaddingPx <= 0) {
            bottomPadding = lineWidthPx;
        } else {
            bottomPadding = -endPaddingPx;
        }

        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                .getLayoutParams();
        int top = child.getTop() - params.topMargin + topPadding;
        int bottom = child.getBottom() + params.bottomMargin + bottomPadding;
        int left = child.getRight() + params.rightMargin;
        int right = left + lineWidthPx;
        mPaint.setColor(color);

        c.drawRect(left, top, right, bottom, mPaint);
    }


    private void drawChildLeftVertical(View child, Canvas c, RecyclerView parent, int color, int lineWidthPx, int startPaddingPx, int endPaddingPx) {
        int topPadding = 0;
        int bottomPadding = 0;

        if (startPaddingPx <= 0) {
            //padding<0当作==0处理
            //上下左右默认分割线的两头都出头一个分割线的宽度，避免十字交叉的时候，交叉点是空白
            topPadding = -lineWidthPx;
        } else {
            topPadding = startPaddingPx;
        }
        if (endPaddingPx <= 0) {
            bottomPadding = lineWidthPx;
        } else {
            bottomPadding = -endPaddingPx;
        }

        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                .getLayoutParams();
        int top = child.getTop() - params.topMargin + topPadding;
        int bottom = child.getBottom() + params.bottomMargin + bottomPadding;
        int right = child.getLeft() - params.leftMargin;
        int left = right - lineWidthPx;
        mPaint.setColor(color);
        c.drawRect(left, top, right, bottom, mPaint);
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        //item 的位置
        int itemPosition = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        int itemCount = parent.getAdapter().getItemCount();
        Divide divide = getDivider(itemPosition, itemCount);
        //上下左右的 padding 值
        int left = divide.getLeftSideLine().isHave() ? Dp2Px.convert(view.getContext(), divide.getLeftSideLine().getWidthDp()) : 0;
        int top = divide.getTopSideLine().isHave() ? Dp2Px.convert(view.getContext(), divide.getTopSideLine().getWidthDp()) : 0;
        int right = divide.getRightSideLine().isHave() ? Dp2Px.convert(view.getContext(), divide.getRightSideLine().getWidthDp()) : 0;
        int bottom = divide.getBottomSideLine().isHave() ? Dp2Px.convert(view.getContext(), divide.getBottomSideLine().getWidthDp()) : 0;
        outRect.set(left, top, right, bottom);
    }

    public abstract Divide getDivider(int itemPosition, int itemCount);

}
