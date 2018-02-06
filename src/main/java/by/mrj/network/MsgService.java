package by.mrj.network;

import by.mrj.domain.Hashable;
import by.mrj.domain.types.Command;

import java.io.Serializable;

import static by.mrj.util.CryptoUtil.doubleSha256;

public class MsgService {

    // TODO
    public void sendMessage() {

    }

    // TODO
    public static <T extends Hashable & Serializable> Message makeMessage(T payload, Command command) {
        return Message.builder()
                .payload(payload)
                .command(command)
                .checksum(doubleSha256(payload.hash()))
                .build();
    }

    public boolean msgCheck(Message msg) {
        return true; // todo
    }
}
