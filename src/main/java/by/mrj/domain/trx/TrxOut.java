package by.mrj.domain.trx;

import by.mrj.domain.Asset;
import by.mrj.domain.Hashable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

import static by.mrj.util.CryptoUtil.doubleSha256;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Getter
@Builder
@ToString
@EqualsAndHashCode
public class TrxOut implements Hashable, Serializable {
    Asset value;
    int scriptLength;
    String lockingScript;

    @Override
    public String hash() {
        return doubleSha256(value.hash() + scriptLength + lockingScript);
    }
}
