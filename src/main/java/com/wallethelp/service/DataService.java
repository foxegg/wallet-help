package com.wallethelp.service;

import android.util.Log;

import com.wallethelp.domain.BtcReturn;
import com.wallethelp.domain.CoinExchangeObject;
import com.wallethelp.domain.CoinPrice;
import com.wallethelp.domain.CoinReturnObject;
import com.wallethelp.domain.EthErc20ReturnList;
import com.wallethelp.domain.SelfObjectReturn;
import com.wallethelp.utils.HttpResponseListener;
import com.wallethelp.utils.ReturnUtils;
import com.wallethelp.utils.Utils;
import com.yanzhenjie.nohttp.rest.OnResponseListener;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class DataService {
	//*************以太方************************************************************************************************
	//0xe75ad3aab14e4b0df8c5da4286608dabb21bd864
	/**
	 * 以太坊官网查询余额
	 * @param address
	 * @param httpListener
	 * @return
	 */
	public <T> void ethGetBalance(String address, String contractaddress, HttpResponseListener<T> httpListener) {
		// 我们需要进行请求的地址：
		String urlStr = Utils.ETH_URL +"?"
				+"&module=account"
				+"&address=" + address
				+"&tag=latest";
				//+"&apikey=" + Utils.ETH_APIKEY;
		if(contractaddress!=null && contractaddress.length()>0){
			urlStr += "&action=tokenbalance";
			urlStr += "&contractaddress="+contractaddress;
		}else{
			urlStr += "&action=balance";
		}
		Utils.instance.getHttpsInfo(urlStr, httpListener);

    }

	/**
	 * 分页查询交易记录
	 * @param address
	 * @param contractaddress
	 * @param page
	 * @param offset
	 * @param httpListener
	 * @return
	 */
	public <T> void ethGetTxlist(String address, String contractaddress, int page, int offset, HttpResponseListener<T> httpListener) {
		// 我们需要进行请求的地址：
		String urlStr = Utils.ETH_URL +"?"
				+"&module=account"
				+"&address=" + address
				+"&page=" + page
				+"&offset=" + offset
				+"&sort=asc";
				//+"&apikey=" + Utils.ETH_APIKEY;
		if(contractaddress!=null && contractaddress.length()>0){
			urlStr += "&action=tokentx";
			urlStr += "&contractaddress="+contractaddress;
		}else{
			urlStr += "&action=txlist";
		}
		Utils.instance.getHttpsInfo(urlStr, httpListener);
	}

	//*************比特币现金*********************************************************************************************
	/**
	 * 比特币现金查询余额
	 * @param address
	 * @return
	 */
	public <T> void bchGetBalance(String address, HttpResponseListener<T> listener) {
		String urlStr = Utils.BCH_URL +"/address/"+address;
		Utils.instance.getHttpsInfo(urlStr, listener);
	}

	/**
	 * 交易记录
	 * @param address
	 * @param page
	 * @param offset
	 * @return
	 */
	public <T> void bchGetTxlist(String address, int page, int offset, HttpResponseListener<T> listener) {
		String urlStr = Utils.BCH_URL +"/address/"+address+"/tx"
				+"?page=" + page
				+"&pagesize=" + offset;
		Utils.instance.getHttpsInfo(urlStr, listener);
	}

	public <T> void bchGetUnspent(String address, int page, int offset, HttpResponseListener<T> listener) {
		String urlStr = Utils.BCH_URL +"/address/"+address+"/unspent"
				+"?page=" + page
				+"&pagesize=" + offset;
		Utils.instance.getHttpsInfo(urlStr, listener);
	}

	//*************比特币************************************************************************************************
	/**
	 * 比特币查询余额
	 * @param address
	 * @return
	 */
	public <T> void btcGetBalance(String address, HttpResponseListener<T> listener) {
		String urlStr = Utils.BTC_URL +"/address/"+address;
		Utils.instance.getHttpsInfo(urlStr, listener);
	}

	/**
	 * 交易记录
	 * @param address
	 * @param page
	 * @param offset
	 * @return
	 */
	public <T> void btcGetTxlist(String address, int page, int offset, HttpResponseListener<T> listener) {
		String urlStr = Utils.BCH_URL +"/address/"+address+"/tx"
				+"?page=" + page
				+"&pagesize=" + offset;
		Utils.instance.getHttpsInfo(urlStr, listener);
	}

	public <T> void btcGetUnspent(String address, int page, int offset, HttpResponseListener<T> listener) {
		String urlStr = Utils.BCH_URL +"/address/"+address+"/unspent"
				+"?page=" + page
				+"&pagesize=" + offset;
		Utils.instance.getHttpsInfo(urlStr, listener);
	}

    /**
     * 模糊查询代币
     * @param nameOrSymbol
     * @return
     */
    public void getErc20Coins(String nameOrSymbol, HttpResponseListener<EthErc20ReturnList> listener) {
		String urlStr = Utils.ACUTEANGLE_URI + "/get_erc20_coins?nameOrSymbol=" +nameOrSymbol;
		Utils.instance.getHttpsInfo(urlStr, listener);
    }

	/**
	 * 获取GasPrice
	 * @param listener
	 * @param <T>
	 */
	public <T> void getGasPrice(HttpResponseListener<T> listener) {
		String urlStr = Utils.ETH_URL+"?module=proxy&action=eth_gasPrice";
		Utils.instance.getHttpsInfo(urlStr, listener);
	}

	/**
	 * 获取以太坊交易详情
	 * @param hash
	 * @param listener
	 * @param <T>
	 */
	public <T> void getEthTransactionByHash(String hash, HttpResponseListener<T> listener) {
		String urlStr = Utils.ETH_URL+"?module=proxy&action=eth_getTransactionByHash&txhash="+hash;
		Utils.instance.getHttpsInfo(urlStr, listener);
	}

	/**
	 * 获取比特币交易详情
	 * @param hash
	 * @param listener
	 * @param <T>
	 */
	public <T> void getBtcTransactionByHash(String hash, HttpResponseListener<T> listener) {
		String urlStr = Utils.BTC_URL+"/tx/"+hash+"?verbose=3";
		Utils.instance.getHttpsInfo(urlStr, listener);
	}

	/**
	 * 通过简称获取交易信息
	 * @param symbol
	 * @param listener
	 */
    public void getCoinPriceBySymbol(String symbol, HttpResponseListener<CoinReturnObject> listener) {
		String urlStr = Utils.ACUTEANGLE_URI+ "/get_coinprice_by_symbol?symbol=" +symbol;
		Utils.instance.getHttpsInfo(urlStr, listener);
    }

	/**
	 * 通过名称获取交易信息
	 * @param name
	 * @param listener
	 */
	public void getCoinPriceByName(String name, HttpResponseListener<CoinReturnObject> listener) {
		String urlStr = Utils.ACUTEANGLE_URI+ "/get_coinprice_by_name?name=" +name.replace(" ", "-");
		Utils.instance.getHttpsInfo(urlStr, listener);
	}

	/**
	 * 获取汇率
	 * @param listener
	 */
    public void getCoinExchange(HttpResponseListener<CoinExchangeObject> listener) {
		String urlStr = Utils.ACUTEANGLE_URI+ "/get_coinprice_exchange";
		Utils.instance.getHttpsInfo(urlStr, listener);
    }

	public void getRawhex(String hash, HttpResponseListener<String> listener) {
		String urlStr = Utils.BTC_RAWHEX_URL+ "/"+hash+".rawhex";

		new Thread(){
			@Override
			public void run() {
				super.run();
				Document doc = null;
				try {
					doc = Jsoup.connect(urlStr).timeout(5000).userAgent("Mozilla").maxBodySize(0).get();
					Elements elements = doc.getElementsByTag("body");
					ReturnUtils.returnInfo(true, elements.get(0).html(), listener);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
}
