package by.mrj.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import static by.mrj.util.CryptoUtil.doubleSha256;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString
public class UniqueTypedAsset extends Asset {
    private String type;
    private String uuid;

    @Builder
    private UniqueTypedAsset(float amount, String type, String uuid) {
        super(amount);
        this.type = type;
        this.uuid = uuid;
    }

    @Override
    public String hash() {
        return doubleSha256(super.hash() + type + uuid);
    }
}
