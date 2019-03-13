package top.gunplan.security.dosecurity;


/**
 * @author dosdrtt
 */
public interface BaseSecurity {





    boolean doEncryption(byte[] bytes, String... salt);


    boolean undoEncryption(byte[] bytes, String... salt);


    boolean doEncryption(byte[] bytes, int sta, int len, String... salt);


    boolean undoEncryption(byte[] bytes, int sta, int len, String... salt);

}
