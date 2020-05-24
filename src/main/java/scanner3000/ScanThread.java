package scanner3000;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;

/**
 * This class is used to implement scanning of a network section in a separate stream.
 */
class ScanThread implements Runnable{


    private List<Result> results;
    private String host;
    private String port;
    private int timeOut;

    /**
     * Constructor for creating a separate thread
     * @param host
     * @param port
     * @param timeOut
     * @param results
     */
    public ScanThread(String host, String port, int timeOut, List<Result> results) {
        this.host = host;
        this.port = port;
        this.timeOut = timeOut;
        this.results = results;
    }

    /**
     * Run network scan in a separate thread.
     * Scanning is done by establishing a connection.
     * If the connection could not be established, the port on the scanned host is closed
     */
    @Override
    public void run() {

        System.out.println( this.host );

        boolean status = false;
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(host, Integer.parseInt(port)), timeOut);
            status = true;

            socket.close();

        } catch (IOException e) {
            status = false;
        } finally {
            synchronized (this) {

                System.out.println(this.host + ":" + this.port + " [" + status + "]");
                this.results.add(new Result(host, port, status));

            }
        }


    }

}