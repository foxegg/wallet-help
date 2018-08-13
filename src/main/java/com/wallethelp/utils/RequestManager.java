package com.wallethelp.utils;

import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.download.DownloadListener;
import com.yanzhenjie.nohttp.download.DownloadQueue;
import com.yanzhenjie.nohttp.download.DownloadRequest;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;

import java.util.Date;

/**
 * Created by wxue on 18-5-3.
 */

public class RequestManager {
    private static RequestManager sInstance;
    private RequestQueue mRequestQueue;
    private DownloadQueue mDownloadQueue;

    public static RequestManager getInstance() {
        if (sInstance == null)
            synchronized (RequestManager.class) {
                if (sInstance == null)
                    sInstance = new RequestManager();
            }
        return sInstance;
    }

    private RequestManager() {
        mRequestQueue = NoHttp.newRequestQueue(5);
        mDownloadQueue = NoHttp.newDownloadQueue(1);
    }

    public static long lastRequestTime = new Date().getTime();
    //客户端发起请求限制最多1秒一次
    public static int MIN_SNAP = 3000;
    public <T> void request(boolean needWait, int what, Request<T> request, OnResponseListener<T> listener) {
        if(needWait){
            new Thread(()->{
                while ((new Date().getTime()-lastRequestTime)<MIN_SNAP){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lastRequestTime = new Date().getTime();
                mRequestQueue.add(what, request, listener);
            }).start();
        }else{
            mRequestQueue.add(what, request, listener);
        }
    }

    public void download(int what, DownloadRequest request, DownloadListener listener) {
        mDownloadQueue.add(what, request, listener);
    }

    /**
     * 取消这个sign标记的所有请求。
     * @param sign 请求的取消标志。
     */
    public void cancelBySign(Object sign) {
        mRequestQueue.cancelBySign(sign);
    }

    /**
     * 取消队列中所有请求。
     */
    public void cancelAll() {
        mRequestQueue.cancelAll();
    }
}
