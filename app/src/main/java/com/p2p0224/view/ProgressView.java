package com.p2p0224.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.p2p0224.utils.UIUtils;

/**
 * 作者：田学伟 on 2017/6/23 09:21
 * QQ：93226539
 * 作用：自定义控件:
 * 第一步:先考虑是先继承View还是ViewGroup,
 * 一般来说一个功能的实现(一个控件)就继承view
 * 一般来说二个控件组合以上继承viewGroup
 * 对某些控件进行扩展的时候就继承该控件
 * <p>
 * 代码适配:可以动态设置高度
 */

public class ProgressView extends View {

    private Paint paint;
    private int paintColor = Color.BLACK;
    private int strokeWidth = UIUtils.dp2px(20);
    private int height;
    private int width;

    public ProgressView(Context context) {
        super(context);

        init();
    }


    private void init() {
        paint = new Paint();
        paint.setColor(paintColor);
        paint.setStrokeWidth(strokeWidth);
        paint.setAntiAlias(true);
        /**
         *三种样式
         * stroke描边
         * fill填充
         * stroke and fill 既描边又填充
         */
        paint.setStyle(Paint.Style.STROKE);
    }

    public ProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        height = getHeight();
        width = getWidth();
    }

    /**
     * 画出三个部分
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画圆
        int cx = width / 2;
        int cy = height / 2;

        int radius = cx - strokeWidth / 2;
        canvas.drawCircle(cx, cy, radius, paint);
    }
}