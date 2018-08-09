package com.wallethelp.utils;

import com.google.gson.Gson;
import com.yanzhenjie.nohttp.Headers;
import com.yanzhenjie.nohttp.Logger;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.StringRequest;

/**
 * Created by wxue on 18-5-21.
 */

public class JavaBeanRequest<T> extends Request<T> {
    /**
     * 要解析的JavaBean。
     */
    private Class<T> clazz;

    public void setClazSon(Class<T> clazSon) {
        this.clazSon = clazSon;
    }

    private Class<T> clazSon;
    private Gson gson;
    private boolean encrypted;

    public JavaBeanRequest(String url, Class<T> clazz) {
        this(url, RequestMethod.GET, clazz);
    }

    public JavaBeanRequest(String url, RequestMethod requestMethod, Class<T> clazz) {
        super(url, requestMethod);
        this.clazz = clazz;
        gson = new Gson();
    }

    public void setEncrypted(boolean encrypted){
        this.encrypted = encrypted;
    }

    @Override
    public T parseResponse(Headers responseHeaders, byte[] responseBody) throws Exception {
        String response = StringRequest.parseResponseString(responseHeaders, responseBody);
        if(encrypted){
            response = EncryptUtils.decrypt(response);
        }
        Logger.e("request = " + this.url() + "\nresponse = " + response.toString());
        // 这里如果数据格式错误，或者解析失败，会在失败的回调方法中返回 ParseError 异常。
        return gson.fromJson(response, clazz);
    }
}

