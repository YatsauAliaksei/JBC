package by.mrj.domain.trx;

import java.util.Set;

public class PrivateTransaction extends Transaction {

    //    private String
    private PrivateTransaction(int version, int inCount, Set<TrxIn> trxInputs, int outCount, Set<TrxOut> trxOutputs) {
        super(version, inCount, trxInputs, outCount, trxOutputs);
    }

    @Override
    public String hash() {
        return super.hash();
    }
}
