package scanner3000;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;

class ScanThread implements Runnable{


    private List<Result> results;
    private String host;
    private String port;
    private int timeOut;


    public ScanThread(String host, String port, int timeOut, List<Result> results) {
        this.host = host;
        this.port = port;
        this.timeOut = timeOut;
        this.results = results;
    }

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