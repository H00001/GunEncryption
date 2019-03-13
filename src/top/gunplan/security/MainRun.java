package top.gunplan.security;


import net.jcip.annotations.ThreadSafe;
import top.gunplan.security.check.AnalyzingParamters;
import top.gunplan.security.check.OutputAdaptor;
import top.gunplan.security.util.GunIOUtils;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;


import static top.gunplan.security.util.FileNameChange.doChangeFileName;

/**
 * @author dosdrtt
 * @version 1.4.0
 * @date update time vld df ffr ggg
 */

@ThreadSafe
public final class MainRun {
    public static void main(String[] args) throws Exception {
        final AnalyzingParamters.Parameters paramters;
        if (OutputAdaptor.lengthCheck(args) != 0
                || OutputAdaptor.parameterVaildChcek(args) != 0
                || !OutputAdaptor.fileExitsChcek(args)) {
            return;
        } else {
            paramters = AnalyzingParamters.analizing(args);
        }
        String outputfilename = doChangeFileName(args[1]);
        final ExecutorService executorService = GunExecutors.newFixedThreadPool(paramters.getThreadsize());
        final CountDownLatch latch = new CountDownLatch(paramters.getThreadsize());

        executorService.submit(new GunSecWork(latch, paramters.getFinalblock(), paramters.getType(), paramters.getSalt()));

        for (int i = 0; i < paramters.getThreadsize() - 1; i++) {
            executorService.submit(new GunSecWork(latch, paramters.getbyteBlock(i), paramters.getType(), paramters.getSalt()));
        }

        executorService.shutdown();
        latch.await();

        for (int i = 0; i < paramters.getThreadsize(); i++) {
            GunIOUtils.writeFile(outputfilename, paramters.getbyteBlock(i));
        }
        if (paramters.getFinalblock() != null) {
            GunIOUtils.writeFile(outputfilename, paramters.getFinalblock());
        }

        System.out.println("export file name:" + outputfilename);
    }


}

