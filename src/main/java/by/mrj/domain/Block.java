package by.mrj.domain;

import by.mrj.util.CryptoUtil;
import lombok.Builder;
import lombok.ToString;
import lombok.Value;

import java.io.Serializable;
import java.util.Set;

@Value
@ToString(callSuper = true)
public class Block<T extends Asset> extends BlockHeader implements Hashable, Serializable {

    @Builder
    private Block(int version, String previousBlockHash, String merkleRoot, long timestamp, int assetsCount, Set<T> assets) {
        super(version, previousBlockHash, merkleRoot, timestamp, assetsCount);
        this.assets = assets;
    }

    private Set<T> assets;

    @Override
    public String hash() {
        return CryptoUtil.doubleSha256(getVersion() + getPreviousBlockHash() + getMerkleRoot() + getTimestamp());
    }
}
