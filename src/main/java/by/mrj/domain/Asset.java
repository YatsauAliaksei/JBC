package by.mrj.domain;


import by.mrj.util.CryptoUtil;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Builder
@EqualsAndHashCode
public class Asset implements Hashable, Serializable {

    private String value;

    @Override
    public String hash() {
        return CryptoUtil.doubleSha256(value);
    }
}
