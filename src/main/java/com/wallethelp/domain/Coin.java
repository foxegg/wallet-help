package com.wallethelp.domain;

public class Coin {
    //{精度:decimal, 名称:name, 符号:symbol, 发行总量:totalSupply}
    public String token;
    public String name;
    public String symbol;
    public Integer decimals;
    public String totalSupply;
    public String imgUrl;

    @Override
    public String toString() {
        return token + "," + name+","+symbol+","+decimals+","+totalSupply+","+imgUrl;
    }


}
