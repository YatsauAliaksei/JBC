package by.mrj.domain.trx;

import by.mrj.domain.Hashable;
import by.mrj.util.CryptoUtil;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Set;

@Getter
@Builder
@ToString
public class Transaction implements Hashable {
    private int version;
    private int inCount;
    private Set<TrxIn> trxInputs;
    private int outCount;
    private Set<TrxOut> trxOutputs;

    @Override
    public String hash() {
        String txInsHash = trxInputs.stream()
                .map(TrxIn::hash)
                .reduce(String::concat)
                .orElseThrow(IllegalStateException::new);

        String txOutsHash = trxOutputs.stream()
                .map(TrxOut::hash)
                .reduce(String::concat)
                .orElseThrow(IllegalStateException::new);

        return CryptoUtil.doubleSha256(version + inCount + txInsHash + outCount + txOutsHash);
    }
}
