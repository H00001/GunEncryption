package top.gunplan.security.dosecurity.impl;

import top.gunplan.security.dosecurity.BaseSecurity;


/**
 * @author dosdrtt
 * @date
 */
public class SecurityImpl implements BaseSecurity {
    /**
     *
     */
    public static class SecuritysInstance {
        private static BaseSecurity instance = new SecurityImpl();
        public static BaseSecurity getInstance() {
            return instance;
        }
    }

    @Override
    public boolean doEncryption(byte[] bytes, String... salt) {
        return doEncryption(bytes, 0, bytes.length, salt);
    }

    @Override
    public boolean undoEncryption(byte[] bytes, String... salt) {
        return undoEncryption(bytes, 0, bytes.length, salt);
    }

    @Override
    public boolean doEncryption(byte[] bytes, int sta, int len, String... salt) {
        if (salt.length == 0) {
            return false;
        }
        byte[] salts = salt[0].getBytes();
        for (int i = sta; i < len; i++) {
            bytes[i] = mover(bytes[i], salts[i & (salt[0].length())]);
        }
        return true;
    }

    @Override
    public boolean undoEncryption(byte[] bytes, int sta, int len, String... salt) {
        if (salt.length == 0) {
            return false;
        }
        byte[] salts = salt[0].getBytes();
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = movel(bytes[i], salts[i & (salt[0].length())]);
        }
        return true;
    }

    private byte movel(int bytein, int mov) {
        mov = mov & 7;
        return (byte) (bytein << mov | bytein >>> (8 - mov));
    }


    private byte mover(int bytein, int mov) {
        mov = mov & 7;
        return (byte) (bytein << mov | bytein >>> (8 - mov));
    }

}