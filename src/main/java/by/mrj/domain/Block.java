package by.mrj.domain;

import by.mrj.domain.trx.Transaction;
import by.mrj.util.CryptoUtil;
import lombok.Builder;
import lombok.ToString;
import lombok.Value;

import java.io.Serializable;
import java.util.Set;

@Value
@ToString(callSuper = true)
public class Block extends BlockHeader implements Hashable, Serializable {

    private Set<Transaction> transactions;

    @Builder
    private Block(int version, String previousBlockHash, String merkleRoot, long timestamp, int assetsCount, Set<Transaction> transactions) {
        super(version, previousBlockHash, merkleRoot, timestamp, assetsCount);
        this.transactions = transactions;
    }

    @Override
    public String hash() {
        return CryptoUtil.doubleSha256(getVersion() + getPreviousBlockHash() + getMerkleRoot() + getTimestamp());
    }
}
