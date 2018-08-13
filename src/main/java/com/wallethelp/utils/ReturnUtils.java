package com.wallethelp.utils;


import com.yanzhenjie.nohttp.Headers;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

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
