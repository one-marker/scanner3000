package scanner3000;

import java.util.Objects;

public class Result {

    private String hostname;
    private String port;
    private boolean status;

    /**
     *
     * @param hostname
     * @param port
     * @param status
     */
    public Result(String hostname, String port, boolean status) {
        this.hostname = hostname;
        this.port = port;
        this.status = status;
    }

    public String toString(){
        return this.hostname + ":" + this.port + " [" + this.status + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result result = (Result) o;
        return status == result.status &&
                hostname.equals(result.hostname) &&
                port.equals(result.port);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hostname, port, status);
    }
}
