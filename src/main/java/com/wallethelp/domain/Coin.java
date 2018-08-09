package com.wallethelp.domain;

import java.util.Objects;

public class Coin {
    //{精度:decimal, 名称:name, 符号:symbol, 发行总量:totalSupply}
	private String token;
    private String name;
    private String symbol;
    private Integer decimals;
    private String totalSupply;

    public Coin() {
    }

    public Coin(String name, String symbol, Integer decimals, String totalSupply) {
        this.name = name;
        this.symbol = symbol;
        this.decimals = decimals;
        this.totalSupply = totalSupply;
    }

    public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Integer getDecimals() {
        return decimals;
    }

    public void setDecimals(Integer decimals) {
        this.decimals = decimals;
    }

    public String getTotalSupply() {
        return totalSupply;
    }

    public void setTotalSupply(String totalSupply) {
        this.totalSupply = totalSupply;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coin userType = (Coin) o;
        return Objects.equals(token, userType.token)&&
                Objects.equals(name, userType.name) &&
                Objects.equals(symbol, userType.symbol)&&
                Objects.equals(decimals, userType.decimals) &&
                Objects.equals(totalSupply, userType.totalSupply);
    }

    @Override
    public String toString() {
        return token + "," + name+","+symbol+","+decimals+","+totalSupply;
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, name, symbol, decimals, totalSupply);
    }
}
