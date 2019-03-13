package top.gunplan.security.check;


import com.sun.istack.internal.NotNull;
import top.gunplan.security.util.GunIOUtils;

import java.util.Collections;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 */
public class AnalyzingParamters {
    public static Parameters analizing(String[] args) {
        return new Parameters(args);
    }

    public enum Type {
        /**
         *
         */
        EN, DE
    }

    public static class Parameters {
        private final String salt;
        private final Type type;
        private final byte[] filebytes;
        private final int threadsize;
        private byte[][] dealunit;
        private byte[] finalblock = null;
        private int blocksize;

        public byte[] getFinalblock() {
            return finalblock;
        }

        public Parameters(String[] args) {
           // ConcurrentMap
        //    Collections.unmodifiableNavigableMap()
            this(args[2], "-s".equals(args[0]) ? Type.EN : Type.DE, args[1], Runtime.getRuntime().availableProcessors() * 2);
        }

        public Parameters(String salt, Type type, @NotNull byte[] filebytes, int threadsize) {
            this.salt = salt;
            this.type = type;
            this.filebytes = filebytes;
            this.threadsize = threadsize;
            blocksize = filebytes.length / threadsize;
            dealunit = new byte[threadsize][blocksize];
            if (filebytes.length > threadsize) {
                for (int i = 0; i < threadsize; i++) {
                    System.arraycopy(filebytes, i * blocksize, dealunit[i], 0, blocksize);
                }
                if ((filebytes.length & (threadsize - 1)) != 0) {
                    finalblock = new byte[filebytes.length - threadsize * blocksize];
                    System.arraycopy(filebytes, threadsize * blocksize, finalblock, 0, filebytes.length - threadsize * blocksize);
                }
            } else {
            }
        }

        public Parameters(String salt, Type type, String filename, int threadsize) {
            this(salt, type, GunIOUtils.readFile(filename), threadsize);

        }

        public String getSalt() {
            return salt;
        }

        public Type getType() {
            return type;
        }


        public int getThreadsize() {
            return threadsize;
        }

        public byte[] getbyteBlock(int i) {
            if (i < threadsize) {
                return dealunit[i];
            } else {
                throw new RuntimeException("Out of block size");
            }
        }
    }
}
