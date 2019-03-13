package top.gunplan.security.check;

import java.io.File;

public final class OutputAdaptor {
    public static int lengthCheck(String[] args) {
        if (args == null) {
            System.err.println("pkease input option -s or -d");
            return -1;
        } else if (args.length == 0) {
            System.err.println("pkease input option -s or -d");
            return -2;
        } else if (args.length == 1) {
            if ("-h".equals(args[0])) {
                System.out.println("this is a tools for data security\n it can make the data security" +
                        "\n please use it with -s or -d \n and the file path and the salt");
                return 2;
            }
            else
            {
                System.err.println("parameters value is not enough");
                return -3;
            }
        } else if (args.length < 3) {
            System.err.println("parameters value is not enough");
            return -3;
        }
        return 0;
    }

    public static int parameterVaildChcek(String[] args) {
        if (!"-d".equals(args[0]) && !"-s".equals(args[0]) && !"-h".equals(args[0])) {
            System.err.println("1th parameter error");
            return -4;
        } else if (args[1].length() == 0 || args[2].length() == 0) {
            return -5;
        } else {
            return 0;
        }
    }


    public static boolean fileExitsChcek(String[] args) {

        File file = new File(args[1]);
        if (!file.exists()) {
            System.err.println("file not found :" + args[1]);
            return false;
        } else if (file.length() <= 1) {
            System.err.println("file is empty :" + args[1]);
            return false;
        } else {
            return true;
        }
    }
}
