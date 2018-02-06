package by.mrj.util;

import by.mrj.network.Message;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class NetUtils {
    public static final String MAGIC = "MAGIC";

    public static byte[] serialize(Message object) { // xxx: Possible should work only with Message type. Some for below.
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(baos)) {

            oos.writeBytes(MAGIC); // first step check
            oos.writeObject(object);
            oos.flush();
            return baos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Serializable deserialize(byte[] bytes) {
        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes))) {
            checkMagic(ois);
            return (Serializable) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Message deserialize(InputStream is) {
        try (ObjectInputStream ois = new ObjectInputStream(is)) {
            checkMagic(ois);
            return (Message) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void checkMagic(ObjectInputStream in) throws IOException {
        byte[] buf = new byte[MAGIC.length()];
        in.read(buf, 0, buf.length);
        String exMagic = new String(buf);

        if (!MAGIC.equals(exMagic)) {
            throw new RuntimeException(""); // todo
        }
    }
}
