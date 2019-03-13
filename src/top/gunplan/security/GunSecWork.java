package top.gunplan.security;

import top.gunplan.security.check.AnalyzingParamters;
import top.gunplan.security.dosecurity.impl.SecurityImpl;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author dosdrtt
 */
public class GunSecWork implements Runnable {

    private CountDownLatch latch;
    private final byte[] bytes;
    private final int sta;
    private final int len;
    private final AnalyzingParamters.Type type;
    private final String salt;

    public GunSecWork(CountDownLatch latch, byte[] bytes, AnalyzingParamters.Type type, String salt) {
        this(latch, bytes, 0, bytes.length, type, salt);
    }


    public GunSecWork(CountDownLatch latch, byte[] bytes, int sta, int len, AnalyzingParamters.Type type, String salt) {
        this.latch = latch;
        this.bytes = bytes;
        this.len = len;
        this.sta = sta;
        this.type = type;
        this.salt = salt;
    }

    @Override
    public void run() {
        if (this.bytes != null) {
            if (type == AnalyzingParamters.Type.EN) {
                SecurityImpl.SecuritysInstance.getInstance().doEncryption(bytes, sta, len, salt);
                if (this.latch != null) {
                    this.latch.countDown();
                }
            } else {

                SecurityImpl.SecuritysInstance.getInstance().undoEncryption(bytes, sta, len, salt);
                if (this.latch != null) {
                    this.latch.countDown();
                }
            }
        }
    }
}
