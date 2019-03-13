package top.gunplan.security.dosecurity.impl;

import top.gunplan.security.dosecurity.BaseSecurity;

public class AnnotherSecuritys implements BaseSecurity {

    @Override
    public boolean doEncryption(byte[] bytes, String... salt) {
        return false;
    }

    @Override
    public boolean undoEncryption(byte[] bytes, String... salt) {
        return false;
    }

    @Override
    public boolean doEncryption(byte[] bytes, int sta, int len, String... salt) {
        return false;
    }

    @Override
    public boolean undoEncryption(byte[] bytes, int sta, int len, String... salt) {
        return false;
    }
}
