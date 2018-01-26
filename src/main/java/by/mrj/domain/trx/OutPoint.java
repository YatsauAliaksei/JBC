package by.mrj.domain.trx;

import by.mrj.domain.Hashable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import static by.mrj.util.CryptoUtil.doubleSha256;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Getter
@Builder
public class OutPoint implements Hashable {
    String hash;
    int index;

    @Override
    public String hash() {
        return doubleSha256(hash + index);
    }
}
