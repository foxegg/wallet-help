package com.wallethelp.controller;

import com.wallethelp.domain.CoinExchangeObject;
import com.wallethelp.domain.CoinReturnObject;
import com.wallethelp.domain.EthErc20ReturnList;
import com.wallethelp.service.DataService;
import com.wallethelp.utils.HttpResponseListener;
import com.wallethelp.utils.ReturnUtils;
import com.wallethelp.utils.Utils;

public class CoinController {
	private static DataService contractService;
	static {
		contractService = new DataService();
	}

	/**
	 * 获取余额
	 * @param coinType(币简称 定义在Utils.CoinTypes)
	 * @param address(账户公钥)
	 * @param contractaddress(合约地址)
	 * @param httpListener
	 * @param <T>
	 */
	public static <T> void getBalance(String coinType, String address, String contractaddress, HttpResponseListener<T> httpListener) {
		if (coinType != null && coinType.length() > 0) {
			if (Utils.CoinTypes.BTC.equals(coinType)) {
				contractService.btcGetBalance(address, httpListener);
			} else if (Utils.CoinTypes.ETH.equals(coinType)) {
				contractService.ethGetBalance(address, contractaddress, httpListener);
			} else if (Utils.CoinTypes.BCH.equals(coinType)) {
				contractService.bchGetBalance(address, httpListener);
			}
		}else{
			ReturnUtils.returnError(false, "参数错误", httpListener);
		}
	}

	/**
	 * 获取交易记录
	 * @param coinType(币简称 定义在Utils.CoinTypes)
	 * @param address(账户公钥)
	 * @param contractaddress(合约地址)
	 * @param page(大于0)
	 * @param offset(1-50)
	 * @param httpListener(结果反馈监听)
	 * @param <T>
	 */
	public static <T>  void getTxlist(String coinType, String address, String contractaddress, int page, int offset, HttpResponseListener<T> httpListener) {
		if (page<1 || offset > 50 || offset < 1) {
			ReturnUtils.returnError(false, "page从1开始,offset取值范围1到50", httpListener);
		}
		if (coinType != null && coinType.length() > 0) {
			if (Utils.CoinTypes.BTC.equals(coinType)) {
				contractService.btcGetTxlist(address, page, offset, httpListener);
			} else if (Utils.CoinTypes.ETH.equals(coinType)) {
				contractService.ethGetTxlist(address, contractaddress, page, offset, httpListener);
			} else if (Utils.CoinTypes.BCH.equals(coinType)) {
				contractService.bchGetTxlist(address, page, offset, httpListener);
			}
		}else{
			ReturnUtils.returnError(false, "参数错误", httpListener);
		}
	}

	/**
	 * 获取未消费记录
	 * @param coinType(btc,bth)
	 * @param address
	 * @param page(大于0)
	 * @param offset(1-50)
	 * @param httpListener(结果反馈监听)
	 * @param <T>
	 */
	public static <T> void getUnspent(String coinType, String address, int page, int offset, HttpResponseListener<T> httpListener) {
		if (page<1 || offset > 50 || offset < 1) {
			ReturnUtils.returnError(false, "page从1开始,offset取值范围1到50", httpListener);
		}
		if (coinType != null && coinType.length() > 0) {
			if (Utils.CoinTypes.BTC.equals(coinType)) {
				contractService.btcGetUnspent(address, page, offset, httpListener);
			} else if (Utils.CoinTypes.BCH.equals(coinType)) {
				contractService.bchGetUnspent(address, page, offset, httpListener);
			}
		}else{
			ReturnUtils.returnError(false, "参数错误", httpListener);
		}
	}

	/**
	 * 获取GasPrice
	 * @param httpListener(结果反馈监听)
	 * @param <T>
	 */
	public static <T> void getGasPrice(HttpResponseListener<T> httpListener) {
		contractService.getGasPrice(httpListener);
	}

	public static <T> void getTransactionByHash(String coinType, String hash, HttpResponseListener<T> httpListener) {
		if (coinType != null && coinType.length() > 0) {
			if (Utils.CoinTypes.ETH.equals(coinType)) {
				contractService.getEthTransactionByHash(hash, httpListener);
			} else if (Utils.CoinTypes.BTC.equals(coinType)) {
				contractService.getBtcTransactionByHash( hash, httpListener);
			}
		}else{
			ReturnUtils.returnError(false, "参数错误", httpListener);
		}

	}

	/**
	 * 查询代币
	 * @param nameOrSymbol
	 * @param httpListener
	 */
	public static void getErc20Coins(String nameOrSymbol, HttpResponseListener<EthErc20ReturnList> httpListener) {
		contractService.getErc20Coins(nameOrSymbol, httpListener);
	}

	/**
	 *通过名称查询内容 名称:"+name+"简称:"+symbol+"市值:"+marketCap+"价格:"+price+"流通供给:"+circulatingSupply+"24小时交易量:"+volume24h
	 *                +"1小时涨跌幅:"+increase1h+"24小时涨跌幅:"+increase24h+"7小时涨跌幅:"+increase7d
	 * @param name
	 * @param httpListener
	 */
	public static void getCoinPriceByName(String name, HttpResponseListener<CoinReturnObject> httpListener) {
		contractService.getCoinPriceByName(name, httpListener);
	}

	/**
	 *通过简称查询内容 名称:"+name+"简称:"+symbol+"市值:"+marketCap+"价格:"+price+"流通供给:"+circulatingSupply+"24小时交易量:"+volume24h
	 *                +"1小时涨跌幅:"+increase1h+"24小时涨跌幅:"+increase24h+"7小时涨跌幅:"+increase7d
	 * @param symbol
	 * @param httpListener
	 */
	public static void getCoinPriceBySymbol(String symbol, HttpResponseListener<CoinReturnObject> httpListener) {
		contractService.getCoinPriceBySymbol(symbol, httpListener);
	}

	/**
	 * 获取汇率
	 * @param httpListener
	 */
	public static void getCoinExchange(HttpResponseListener<CoinExchangeObject> httpListener) {
		contractService.getCoinExchange(httpListener);
	}

	/**
	 * 获取Rawhex
	 * @param httpListener
	 */
	public static void getRawhex(String hash, HttpResponseListener<String> httpListener) {
		contractService.getRawhex(hash, httpListener);
	}

	/**
	 * 广播比特币交易
	 * @param rawhex
	 * @param httpListener
	 */
	public static void btcTxPublish(String rawhex, HttpResponseListener<String> httpListener) {
		contractService.btcTxPublish(rawhex, httpListener);
	}

	/**
	 * 广播以太坊交易
	 * @param hex
	 * @param httpListener
	 */
	public static void ethTxPublish(String hex, HttpResponseListener<String> httpListener) {
		contractService.ethTxPublish(hex, httpListener);
	}
}
