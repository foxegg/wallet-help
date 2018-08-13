package com.wallethelp.utils;

import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Int128;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthCall;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class EthHelp {
    /*** 查询代币余额 */
    public static Long getTokenBalance(String fromAddress, String contractAddress) {
        Web3j web3j = Utils.getWeb3j();
        String methodName = "balanceOf";
        List inputParameters = new ArrayList<>();
        List outputParameters = new ArrayList<>();
        Address address = new Address(fromAddress);
        inputParameters.add(address);

        Function function = new Function(methodName, inputParameters, outputParameters);
        String data = FunctionEncoder.encode(function);
        Transaction transaction = Transaction.createEthCallTransaction(fromAddress, contractAddress, data);

        EthCall ethCall;
        Long balanceValue = 0l;
        try {
            ethCall = web3j.ethCall(transaction, DefaultBlockParameterName.LATEST).send();
            String getValue = ethCall.getValue();
            balanceValue = Long.parseLong(getValue.substring(2, getValue.length()), 16);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return balanceValue;
    }


    /*** 查询代币精度 */
    public static int getTokenDecimals(String fromAddress, String contractAddress) {
        Web3j web3j = Utils.getWeb3j();
        String methodName = "decimals";
        int name = 0;
        String fromAddr = fromAddress;
        List inputParameters = new ArrayList<>();
        List outputParameters = new ArrayList<>();

        Function function = new Function(methodName, inputParameters, outputParameters);

        String data = FunctionEncoder.encode(function);
        Transaction transaction = Transaction.createEthCallTransaction(fromAddr, contractAddress, data);

        EthCall ethCall;
        try {
            ethCall = web3j.ethCall(transaction, DefaultBlockParameterName.LATEST).sendAsync().get();
            String getValue = ethCall.getValue();
            name = Integer.parseInt(getValue.substring(2, getValue.length()), 16);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }



        return name;
    }

    /**
     * 查询代币属性
     * @param contractAddress
     * @param methodName 代币属性{精度:decimal, 名称:name, 符号:symbol, 发行总量:totalSupply}
     * @return
     */
    public static Object getTokenValue(String fromAddress, String contractAddress, String methodName, boolean isString) {
        Web3j web3j = Utils.getWeb3j();
        List inputParameters = new ArrayList<>();
        List outputParameters = new ArrayList<>();
        TypeReference typeReference = isString?new TypeReference<Utf8String>(){}:new TypeReference<Int128>(){};
        outputParameters.add(typeReference);

        Function function = new Function(methodName, inputParameters, outputParameters);

        String data = FunctionEncoder.encode(function);
        Transaction transaction = Transaction.createEthCallTransaction(fromAddress, contractAddress, data);

        EthCall ethCall;
        try {
            ethCall = web3j.ethCall(transaction, DefaultBlockParameterName.LATEST).sendAsync().get();
            List results = FunctionReturnDecoder.decode(ethCall.getValue(), function.getOutputParameters());
            return results.get(0);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
