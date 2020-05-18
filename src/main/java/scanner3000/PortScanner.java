package scanner3000;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class PortScanner {

    private static int timeOut = 100;
    private static int poolSize = 1;

    public static void start(String[] hosts, String[] ports, int otherPoolSize) throws InterruptedException {

        poolSize = otherPoolSize;
        CountDownLatch countDownLatch = new CountDownLatch(hosts.length * ports.length);


        LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();
        for (String host:hosts) {

            for (String port:ports) {
                if (port.contains("-")){
                    int port1 = Integer.parseInt(port.substring(0,port.indexOf("-")));
                    int port2 = Integer.parseInt(port.substring(port.lastIndexOf("-")+1,port.length()));
                    for (int i = port1; i <= port2 ; i++) {
                        queue.put(new ScanThread(host,  Integer.toString(i), timeOut, countDownLatch));
                    }
                } else {
                    queue.put(new ScanThread(host, port, timeOut, countDownLatch));
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
        System.out.println("Completed task count: " + executor.getCompletedTaskCount());

        executor.shutdown();










    }


}