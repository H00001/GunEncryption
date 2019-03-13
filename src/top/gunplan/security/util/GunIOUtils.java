package top.gunplan.security.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;

final public class GunIOUtils {
    public static int writeFile(String filename, byte[] bin) {
        try {
            final FileOutputStream out = new FileOutputStream(filename,true);
            out.write(bin);
            out.close();
        }
        catch (Exception exp)
        {
            return exp.hashCode();
        }
        return 0;
    }

    public static byte[] readFile(String filename) {
        try {
            final FileInputStream in = new FileInputStream(filename);
            byte[] bytes = new byte[in.available()];
            in.read(bytes);
            in.close();
            return bytes;
        } catch (Exception exp) {
            throw new RuntimeException(exp);
        }


    }

}
