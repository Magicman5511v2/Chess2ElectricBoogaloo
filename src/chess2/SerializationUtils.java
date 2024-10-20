package chess2;

import java.io.*;

/**
 *
 * @author chatGPT made this one for me so no credit here
 */
public class SerializationUtils {
// Method to serialize an object to a byte array

    /**
     * used to create a clone
     *
     * @param obj
     * @return will convert given object to byte[]
     * @throws IOException
     */
    public static byte[] serialize(Object obj) throws IOException {
        try ( ByteArrayOutputStream bos = new ByteArrayOutputStream();  ObjectOutputStream out = new ObjectOutputStream(bos)) {
            out.writeObject(obj);
            return bos.toByteArray();
        }
    }

    /**
     * used to deserialize a byte array to an object
     *
     * @param bytes
     * @return a clone of the original object
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        try ( ByteArrayInputStream bis = new ByteArrayInputStream(bytes);  ObjectInputStream in = new ObjectInputStream(bis)) {
            return in.readObject();
        }
    }

    /**
     * used to create a clone
     *
     * @param obj Board specific
     * @return will convert given object to byte[]
     * @throws IOException
     */
    public static byte[] serialize(Board obj) throws IOException {
        try ( ByteArrayOutputStream bos = new ByteArrayOutputStream();  ObjectOutputStream out = new ObjectOutputStream(bos)) {
            out.writeObject(obj);
            return bos.toByteArray();
        }
    }
}
