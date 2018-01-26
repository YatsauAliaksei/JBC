package by.mrj.domain.trx;

import by.mrj.domain.Hashable;
import by.mrj.util.CryptoUtil;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Getter
@Builder
@ToString
public class Transaction implements Hashable {
    int version;
    int inCount;
    Set<TrxIn> trxInputs;
    int outCount;
    Set<TrxOut> trxOutputs;

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
