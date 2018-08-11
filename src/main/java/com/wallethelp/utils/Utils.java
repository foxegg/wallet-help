package com.wallethelp.utils;

import com.yanzhenjie.nohttp.rest.Request;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class Utils {

    public static final Utils instance = new Utils();

    //token获取地址
    /*public static final String TOKEN_URL = "https://etherscan.io/tokens?p=";
    public static final String TOKEN_INFO_URL = "https://etherscan.io/token/";
    public static final String TOKEN_INFO_URL_COMMENTS = "#comments";
    public static final String TOKEN_START_STRING = "/token/generic-tokenholders2?a=";*/
    //35535197-cce8-4767-876f-35b84eff583c
    //currency-exchange-rates

    public static Web3j getWeb3j() {
        return Web3j.build(new HttpService("https://mainnet.infura.io/nIY6ZbMsh5X79PBLX1Te"));
    }

    public static class CoinTypes{
        //比特币
        public static final String BTC = "btc";
        //以太坊
        public static final String ETH = "eth";
        //比特币现金
        public static final String BCH = "bch";

    }
    //比特币api
    public static final String BTC_URL = "https://chain.api.btc.com/v3";

    public static final String BTC_RAWHEX_URL = "https://btc.com";
    //以太坊官方api
    public static final String ETH_URL = "http://api.etherscan.io/api";
    //public static final String ETH_APIKEY = "1W7EUDWQP2AVUEGRS8HACSEHQ2JDAAJP8R";

    //比特币现金api
    public static final String BCH_URL = "https://bch-chain.api.btc.com/v3";

    public static final String ACUTEANGLE_URI = "http://store.acuteangle.cn/wallet";


    /**
     * 发起请求。
     * @param what      what.
     * @param request   请求对象。
     * @param <T>       想请求到的数据类型。
     */
    public <T> void request(boolean needWait, int what, Request<T> request, HttpResponseListener<T> responseListener) {
        request.setCancelSign(this);
        RequestManager.getInstance().request(needWait, what, request, responseListener);
    }

    /**
     * 获取aac，eth币个数
     */
    public <T> void getHttpsInfo(String urlStr, HttpResponseListener<T> mResponseListener) {
        JavaBeanRequest<T> request = new JavaBeanRequest<T>(urlStr, mResponseListener.getTClass());
        request(!urlStr.startsWith(Utils.ACUTEANGLE_URI),1, request, mResponseListener);
    }

    private static SSLContext getSLLContext() {
        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType)  {}

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) {}

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            }}, new SecureRandom());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sslContext;
    }
}
