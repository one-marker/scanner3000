package scanner3000;

public class Result {

    private String hostname;
    private String port;
    private boolean status;

    public Result(String hostname, String port, boolean status) {
        this.hostname = hostname;
        this.port = port;
        this.status = status;
    }

    public String toString(){
        return this.hostname + ":" + this.port + " [" + this.status + "]";
    }


}
