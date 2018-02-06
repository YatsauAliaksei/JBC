package by.mrj.domain;


import by.mrj.util.CryptoUtil;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@ToString
public abstract class Asset implements Hashable, Serializable {

    private float amount; // precision should be checked and restricted.

    @Override
    public String hash() {
        return CryptoUtil.doubleSha256("" + amount);
    }
}
