package com.p2p0224.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

/**
 * 作者：田学伟 on 2017/6/23 14:12
 * QQ：93226539
 * 作用：
 */

//public class MyScrollView extends ScrollView {
//    private View childView;
//    private int lastY;
//
//    public MyScrollView(Context context) {
//        super(context);
//    }
//
//    public MyScrollView(Context context, AttributeSet attrs) {
//        super(context, attrs);
//    }
//
//    /**
//     * 面试题:
//     * getY和getRawY的区别?
//     * getY指的是当前布局到父布局Y轴的距离
//     * getRawY指的是当前布局到屏幕之间Y轴的距离
//     *
//     * @param ev
//     * @return
//     */
//    /**
//     * Rect和RectF的区别?
//     * Rect存储int
//     * RectF存储float
//     */
//    private Rect rect = new Rect();
//    //用来标记动画是否完成
//    private boolean isAnStart = false;
//
//    @Override
//    public boolean onTouchEvent(MotionEvent ev) {
//        if (isAnStart && getChildCount() == 0) {
//            return super.onTouchEvent(ev);
//        }
//        int eventY = (int) ev.getY();
//
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                lastY = eventY;
//
//                break;
//            case MotionEvent.ACTION_MOVE:
//                int dy = eventY - lastY;
//                //存放view原始位置
//                if (rect.isEmpty()) {
//                    rect.set(childView.getLeft(), childView.getTop()
//                            , childView.getRight(), childView.getBottom());
//                }
//                //改变子布局的位置
//                childView.layout(childView.getLeft()
//                        , childView.getTop() + dy
//                        , childView.getRight()
//                        , childView.getBottom() + dy);
//                lastY = eventY;
//                break;
//            case MotionEvent.ACTION_UP:
//                if (!rect.isEmpty()) {
//                    int translateY = childView.getTop() - rect.top;
//                    TranslateAnimation animation
//                            = new TranslateAnimation(0, 0, 0, 1);
//                    animation.setDuration(200);
//                    animation.setAnimationListener(new Animation.AnimationListener() {
//                        @Override
//                        public void onAnimationStart(Animation animation) {
//
//                        }
//
//                        @Override
//                        public void onAnimationEnd(Animation animation) {
//                            isAnStart = false;
//                            childView.layout(rect.left, rect.top, rect.right, rect.bottom);
//                            //清除原有的标记
//                            rect.setEmpty();
//                        }
//
//                        @Override
//                        public void onAnimationRepeat(Animation animation) {
//
//                        }
//                    });
//                    childView.startAnimation(animation);
//                }
//                break;
//        }
//        return true;//事件都消费掉,true
//    }
//
//    private int lastX;
//
//    @Override
//    public boolean onInterceptHoverEvent(MotionEvent ev) {
//        boolean isOnIntercept = false;
//        int eventY = (int) ev.getY();
//        int eventX = (int) ev.getX();
//
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                lastY = eventY;
//                lastX = eventX;
//                break;
//            case MotionEvent.ACTION_MOVE:
//                //y轴的偏移量
//                int dy = Math.abs(eventY - lastY);
//                //x轴的偏移量
//                int dx = Math.abs(eventX - lastX);
//                //如果y轴的偏移量大于x轴的偏移量就拦截
//                if (dy > dx && dy > 20) {
//                    isOnIntercept = true;
//                }
//                lastX = eventX;
//                lastY = eventY;
//                break;
//        }
//        return super.onInterceptHoverEvent(ev);
//    }
//
//    /**
//     * 布局加载完成后回调的方法
//     */
//    @Override
//    protected void onFinishInflate() {
//        super.onFinishInflate();
//        //判断这个子view至少有一个
//        if (getChildCount() > 0) {
//            //获取第一个子View
//            childView = getChildAt(0);
//        }
//    }
//}

public class MyScrollView extends ScrollView {

    private View childView;
    private int lastY;

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private Rect rect = new Rect();
    private boolean isAnimtionEnd = true;

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (getChildCount() == 0 || !isAnimtionEnd) {
            return super.onTouchEvent(ev);
        }
        /*
        * getY(); 相对于父布局的Y值
        * getrawY(); 相对于屏幕的Y值
        * */
        int eventY = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //保存第一次触摸到的点
                lastY = eventY;

                break;
            case MotionEvent.ACTION_MOVE:
                if (isNeedMove()) {
                    int dy = eventY - lastY; //移动的量
                    //当我们没有记录的时候需要记录原来的位置
                    if (rect.isEmpty()) {
                        rect.set(childView.getLeft(), childView.getTop(),
                                childView.getRight(), childView.getBottom());
                    }
                    //记录一下原来的位置
                    childView.layout(
                            childView.getLeft(),
                            childView.getTop() + dy / 2,
                            childView.getRight(),
                            childView.getBottom() + dy / 2);
                }

                lastY = eventY;
                break;
            case MotionEvent.ACTION_UP:
                //当原来的位置有记录的时候并且动画是结束的时候再执行
                if (!rect.isEmpty() && isAnimtionEnd) {
                    //获取原来的高度和现在拉动位置的差
                    int translateY = childView.getBottom() - rect.bottom;
                    //平移动画所移动的距离
                    TranslateAnimation animation = new TranslateAnimation(0, 0, 0, -translateY);
                    animation.setDuration(200);
                    animation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            //当动画开始执行的时候 需要设置成false
                            isAnimtionEnd = false;
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            //清除动画
                            childView.clearAnimation();
                            //回到原来记录的位置
                            childView.layout(rect.left, rect.top, rect.right, rect.bottom);
                            //把原来记录的位置清除掉
                            rect.setEmpty();
                            isAnimtionEnd = true;
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    childView.startAnimation(animation);
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    /*
    * 拦截事件
    * 返回true 拦截
    * 返回false 不拦截
    *
    * */

    private int downy, lastx, downx;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean isOnIntercept = false;
        int eventx = (int) ev.getX();
        int eventy = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastY = downy = eventy;
                lastx = downx = eventx;
                break;
            case MotionEvent.ACTION_MOVE:
                int absx = Math.abs(eventx - downx);
                int absy = Math.abs(eventy - downy);
                if (absy > absx && absy >= 20) {
                    isOnIntercept = true;
                }
                lastY = eventy;
                lastx = eventx;
                break;
        }
        return isOnIntercept;
    }

    private boolean isNeedMove() {
        //scrollView的高度
        int scrollViewHeight = this.getMeasuredHeight();
        //子视图的高度
        int childHeight = childView.getMeasuredHeight();

        int dy = childHeight - scrollViewHeight;
        //拿到滑动的距离  往下滑动是变小 往上滑动是变大
        int scrollY = getScrollY();

        if (scrollY <= 0 || scrollY >= dy) {
            return true;
        }
        return false;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        //这个方法是在布局加载完成后调用的
        //判断
        if (getChildCount() > 0) {
            childView = getChildAt(0);
        }
    }
}
