package by.mrj.util;

import by.mrj.crypto.Ripemd160;

import java.nio.charset.StandardCharsets;
import com.google.common.hash.Hashing;

public class CryptoUtil {

    public static String doubleSha256(String toHash) {
        return sha256(sha256(toHash));
    }

    public static String sha256(String toHash) {
        return Hashing.sha256()
                .hashString(toHash, StandardCharsets.UTF_8)
                .toString();
    }

    public static String ripemd160(String toHash) {
        return Ripemd160.hash(CryptoUtil.sha256(toHash)); // used for address. In Bitcoin toHash is a PubK.
    }
}
