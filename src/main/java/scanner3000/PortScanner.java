package scanner3000;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class PortScanner {

    private static final Logger logger = LoggerFactory.getLogger(PortScanner.class);

    private static int timeOut = 100;
    private static int poolSize = 1;

    public static void start(String[] hosts, String[] ports, int otherPoolSize, List<Result> results) throws InterruptedException {

        System.out.println("Start scaning...");
        logger.info("Start scaning...");


        poolSize = otherPoolSize;

        LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();
        for (String host:hosts) {

            for (String port:ports) {
                if (port.contains("-")){
                    int port1 = Integer.parseInt(port.substring(0,port.indexOf("-")));
                    int port2 = Integer.parseInt(port.substring(port.lastIndexOf("-")+1,port.length()));
                    for (int i = port1; i <= port2 ; i++) {
                        queue.put(new ScanThread(host,  Integer.toString(i), timeOut, results));
                    }
                } else {
                    queue.put(new ScanThread(host, port, timeOut, results));
                 }


            }
        }

        ThreadPoolExecutor executor = new ThreadPoolExecutor(poolSize, poolSize, timeOut,
                TimeUnit.MILLISECONDS, queue);


        executor.prestartAllCoreThreads();
        while (executor.getTaskCount()!=executor.getCompletedTaskCount()) {
            //System.err.println("count="+executor.getTaskCount()+","+executor.getCompletedTaskCount());
            //Thread.sleep(5000);
        }
        logger.info("Completed task count: " + executor.getCompletedTaskCount());
        System.out.println("Completed task count: " + executor.getCompletedTaskCount());


        executor.shutdown();


        System.out.println("Scanning successful!");
        logger.info("Scanning successful!");


    }


}