package top.gunplan.security.util;

public class FileNameChange {
    public static String doChangeFileName(String filename) {
        if (filename.contains("\\.")) {
            return (filename.split("\\."))[0] + "_" + System.currentTimeMillis() + "." + filename.split("\\.")[1];
        } else {
            return filename + "_" + System.currentTimeMillis();
        }
    }
}
