package com.wallethelp.domain;

import java.util.List;

public class EthReturnList{
    public int status;
    public String message;
    public String jsonrpc;
    public String id;
    public List<EthTransaction> result;
}
