/*
 * Copyright 2015 Yan Zhenjie
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wallethelp.utils;

import com.yanzhenjie.nohttp.Logger;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Response;

import java.lang.reflect.ParameterizedType;

/**
 * Created by wxue on 18-5-3.
 */
public class HttpResponseListener<T> implements OnResponseListener<T> {
    private HttpListener<T> callback;
    public void setCallback(HttpListener<T> callback){
        this.callback = callback;
    }

    @Override
    public void onStart(int what) {

    }

    @Override
    public void onSucceed(int what, Response<T> response) {
        Logger.d(" onSucceed()--responseCode = " + response.responseCode());
        if(callback != null){
            callback.onSucceed(what,response);
        }
    }

    @Override
    public void onFailed(int what, Response<T> response) {
        Logger.d(" onFailed()--responseCode = " + response.responseCode());
        Exception exception = response.getException();
        Logger.e("request error：" + exception.getMessage());
        if(callback != null){
            callback.onFailed(what,response);
        }
    }

    @Override
    public void onFinish(int what) {

    }

    public Class<T> getTClass()
    {
        Class<T> tClass = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return tClass;
    }
}
