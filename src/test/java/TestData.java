import org.junit.Assert;
import org.junit.Test;
import scanner3000.Data;

public class TestData extends Assert {

    String cmd = "scan -h evlentev.ru, yadndex.ru -p 32400-32402, 80 -t 1";

    @Test
    public void testHostParser() throws Exception{

        String[] hostCorrect = {"evlentev.ru", "yadndex.ru"};
        String[] hosts = Data.parseHosts(cmd);
        assertEquals(hostCorrect, hosts);
    }
    @Test
    public void testPostParser() throws Exception {
        String[] portCorrect = {"32400-32402", "80"};
        String[] ports = Data.parsePorts(cmd);
        assertEquals(portCorrect, ports);
    }
    @Test
    public void testThreadParser() throws Exception{
        int threadCorrect = 1;
        int thread = Data.parseThreads(cmd);
        assertEquals(threadCorrect,thread);

    }
}
