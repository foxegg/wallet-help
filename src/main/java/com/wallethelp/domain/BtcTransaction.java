package com.wallethelp.domain;

import java.util.List;

public class BtcTransaction {
    public String confirmations;
    public String block_height;
    public String block_hash;
    public String block_time;
    public String created_at;
    public String fee;
    public String hash;
    public String inputs_count;
    public String inputs_value;
    public String is_coinbase;
    public String is_double_spend;
    public String is_sw_tx;
    public String weight;
    public String vsize;
    public String witness_hash;
    public String lock_time;
    public String outputs_count;
    public String outputs_value;
    public String size;
    public String sigops;
    public String version;
    public List<BtcTransactionInput> inputs;
    public List<BtcTransactionOutput> outputs;
    public String balance_diff;
}
