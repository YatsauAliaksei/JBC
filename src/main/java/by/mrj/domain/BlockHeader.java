package by.mrj.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@ToString
@EqualsAndHashCode
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Getter
public class BlockHeader {
    int version;
    String previousBlockHash;
    String merkleRoot;
    long timestamp; // Unix time. Should be well validated.
    int assetsCount;
}
