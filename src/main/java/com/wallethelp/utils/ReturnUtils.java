package com.wallethelp.utils;


import com.yanzhenjie.nohttp.Headers;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

public class ReturnUtils {
    public static final int SUCCEED = 200;
    public static final int FAIELD = 201;

    public static <T> void returnInfo(boolean succeed, T value, HttpResponseListener<T> httpListener) {
        Response response = new Response(){

            @Override
            public Request request() {
                return null;
            }

            @Override
            public int responseCode() {
                return succeed?SUCCEED:FAIELD;
            }

            @Override
            public boolean isSucceed() {
                return succeed;
            }

            @Override
            public boolean isFromCache() {
                return false;
            }

            @Override
            public Headers getHeaders() {
                return null;
            }

            @Override
            public Object get() {
                return value;
            }

            @Override
            public Exception getException() {
                return null;
            }

            @Override
            public Object getTag() {
                return null;
            }

            @Override
            public long getNetworkMillis() {
                return 0;
            }
        };
        if(succeed){
            httpListener.onSucceed(SUCCEED, response);
        }else{
            httpListener.onFailed(FAIELD, response);
        }
    }

    public static <T> void returnError(boolean succeed, String value, HttpResponseListener<T> httpListener) {
        Response response = new Response(){

            @Override
            public Request request() {
                return null;
            }

            @Override
            public int responseCode() {
                return succeed?SUCCEED:FAIELD;
            }

            @Override
            public boolean isSucceed() {
                return succeed;
            }

            @Override
            public boolean isFromCache() {
                return false;
            }

            @Override
            public Headers getHeaders() {
                return null;
            }

            @Override
            public Object get() {
                return value;
            }

            @Override
            public Exception getException() {
                return null;
            }

            @Override
            public Object getTag() {
                return null;
            }

            @Override
            public long getNetworkMillis() {
                return 0;
            }
        };
        if(succeed){
            httpListener.onSucceed(SUCCEED, response);
        }else{
            httpListener.onFailed(FAIELD, response);
        }
    }
}
