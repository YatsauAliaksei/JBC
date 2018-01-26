package by.mrj.domain;


import by.mrj.util.CryptoUtil;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@Builder
@EqualsAndHashCode
public class Asset implements Hashable, Serializable {

    private int version;
    private String address;

    @Override
    public String hash() {
        return CryptoUtil.doubleSha256(address);
    }
}
