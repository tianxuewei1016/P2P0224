package com.p2p0224.common;

import android.content.Context;
import android.os.Build;
import android.os.Looper;
import android.widget.Toast;

/**
 * 作者：田学伟 on 2017/6/21 10:13
 * QQ：93226539
 * 作用：
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private CrashHandler() {
    }

    ;
    private static CrashHandler crashHandler = new CrashHandler();

    public static CrashHandler getInstance() {
        return crashHandler;
    }

    private Context context;

    public void init(Context context) {
        //把当前的类设置成默认的处理未捕获异常
        Thread.setDefaultUncaughtExceptionHandler(this);
        this.context = context;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        /**
         * 1.提醒用户
         * 2.收集异常
         * 3.退出应用
         */
        new Thread() {
            @Override
            public void run() {
                super.run();
                /*
                * Looper.prepare() Looper.loop()  中间是在主线程中运行
                *
                * 一个线程只有一个looper
                * */
                Looper.prepare();
                Toast.makeText(context, "系统奔溃...", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }.start();

        collection(e);
        //这个是非正常退出
        //把当前所有的activity销毁
        AppManager.getInstance().removeAll();
        //杀死当前的进程
        android.os.Process.killProcess(android.os.Process.myPid());
        //参数,除了0以外都表是非正常的退出
        System.exit(0);//退出虚拟机
    }

    /**
     * 收集异常信息
     *
     * @param e
     */
    private void collection(Throwable e) {
        String version = Build.BOARD;

        //发送给服务器

    }
}
