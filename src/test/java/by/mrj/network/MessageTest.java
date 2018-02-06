package by.mrj.network;

import by.mrj.domain.UniqueTypedAsset;
import by.mrj.domain.trx.TrxOut;
import by.mrj.domain.types.Command;
import by.mrj.util.NetUtils;

import java.io.Serializable;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MessageTest {

    @Test
    public void serialization() {
        UniqueTypedAsset asset = UniqueTypedAsset.builder()
                .amount(1)
                .build();
        TrxOut trxOut = TrxOut.builder()
                .value(asset)
                .build();

        Message message = MsgService.makeMessage(trxOut, Command.HANDSHAKE);

        byte[] bytes = NetUtils.serialize(message);

        Serializable o = NetUtils.deserialize(bytes);

        assertThat(message).isEqualTo(o);
    }

}