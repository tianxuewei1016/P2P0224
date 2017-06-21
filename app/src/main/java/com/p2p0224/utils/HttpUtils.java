package com.p2p0224.utils;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

/**
 * 作者：田学伟 on 2017/6/21 14:37
 * QQ：93226539
 * 作用：网络请求的封装类
 */

public class HttpUtils {

    private AsyncHttpClient httpClient;

    private HttpUtils() {
        httpClient = new AsyncHttpClient();
    }

    private static HttpUtils httpUtils = new HttpUtils();

    public static HttpUtils getInstance() {
        return httpUtils;
    }

    public void get(String url, final OnHttpClientListener onHttpClientListener) {
        httpClient.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String content) {
                super.onSuccess(statusCode, content);
                if (onHttpClientListener != null) {
                    onHttpClientListener.onSuccess(content);
                }
            }

            @Override
            public void onFailure(Throwable error, String content) {
                super.onFailure(error, content);
                if (onHttpClientListener != null) {
                    onHttpClientListener.onFailure(content);
                }
            }
        });
    }

//    public void post(String url, final OnHttpClientListener onHttpClientListener){
//        httpClient.post(url,new AsyncHttpResponseHandler(){
//            @Override
//            public void onSuccess(int statusCode, String content) {
//                super.onSuccess(statusCode, content);
//                if(onHttpClientListener!=null) {
//                    onHttpClientListener.onSuccess(content);
//                }
//            }
//
//            @Override
//            public void onFailure(Throwable error, String content) {
//                super.onFailure(error, content);
//                if(onHttpClientListener!=null) {
//                    onHttpClientListener.onFailure(content);
//                }
//            }
//        });
//    }

    /**
     * 设置接口
     */
    public interface OnHttpClientListener {
        void onSuccess(String json);

        void onFailure(String message);
    }
}
