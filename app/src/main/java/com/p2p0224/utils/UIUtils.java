package com.p2p0224.utils;

import android.content.Context;
import android.view.View;

import com.p2p0224.common.MyApplication;

/**
 * 作者：田学伟 on 2017/6/20 16:36
 * QQ：93226539
 * 作用：
 */

public class UIUtils {


    /*
    * 加载布局
    * */
    public static View inflate(int id) {
        return View.inflate(getContext(), id, null);
    }


    /*
    * 返回一个上下文
    * */
    private static Context getContext() {

        return MyApplication.getContext();
    }


    /*
    * 格式化字符串 - 占位字符
    * */
    public static String stringFormat(int id, String value) {
        String versionName = String.format(getString(id), value);
        return versionName;
    }

    /*
    * 从string文件获取字符串
    * */
    public static String getString(int id) {
        return getContext().getResources().getString(id);
    }

    //与屏幕分辨率相关的
    public static int dp2px(int dp) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (density * dp + 0.5);

    }

    public static int px2dp(int px) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (px / density + 0.5);
    }
}
