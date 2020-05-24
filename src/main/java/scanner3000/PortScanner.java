package scanner3000;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * PortScanner is a class that implements multi-threaded scanning.
 */
public class PortScanner {

    private static final Logger logger = LoggerFactory.getLogger(PortScanner.class);

    /**
     * This is the time in milliseconds that is allocated for connecting to a host on a specific port, otherwise the port on this host will be marked as blocked.
     */
    private int timeOut = 100;

    /**
     * Thread Pool Size
     */
    private int poolSize = 1;

    /**
     * This method starts scanning the network with the specified parameters.
     * Multi-threaded scanning is implemented by splitting all tasks into separate subtasks <strong>ScanThread</strong> that can work in separate threads.
     *  <strong>ScanThread</strong> are placed in the LinkedBlockingQueue queue.
     * This queue is passed to the thread pool, the size of which can vary depending on the otherPoolSize parameter.
     * @param hosts Hosts for scanning
     * @param ports Ports for scanning
     * @param otherPoolSize Thread Pool Size. This parameter shows how many threads we allocate to scan the network.
     * @param results
     * @throws InterruptedException If an exception is thrown then failed to put the task in LinkedBlockingQueue<Runnable>
     */
    public void start(String[] hosts, String[] ports, int otherPoolSize, List<Result> results) throws InterruptedException {

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