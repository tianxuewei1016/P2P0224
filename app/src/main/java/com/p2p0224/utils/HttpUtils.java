package com.p2p0224.utils;

import com.loopj.android.http.AsyncHttpClient;

/**
 * 作者：田学伟 on 2017/6/21 14:37
 * QQ：93226539
 * 作用：
 */

public class HttpUtils {

    private AsyncHttpClient client;

    private HttpUtils() {
        client = new AsyncHttpClient();
    }

    private static HttpUtils httpUtils = new HttpUtils();

    public static HttpUtils getInstance() {
        return httpUtils;
    }

}
