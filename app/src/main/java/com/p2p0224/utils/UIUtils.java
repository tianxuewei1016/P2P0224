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
    /**
     * 加载布局
     * @param id
     * @return
     */
    public static View inflate(int id) {
        return View.inflate(getContext(), id, null);
    }

    /**
     * 返回一个上下文
     * @return
     */
    private static Context getContext() {
        return MyApplication.getContext();
    }
}
