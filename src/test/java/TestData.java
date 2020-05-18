import org.junit.Test;
import scanner3000.Data;

public class TestData {

    String cmd = "scan -h evlentev.ru, yadndex.ru -p 32400-32402, 80 -t 2";

    @Test
    public void testHostParser(){

        String[] hostCorrect = {"evlentev.ru", "yadndex.ru"};
        try {

            String[] hosts = Data.parseHosts(cmd);
            if (hostCorrect.length == hosts.length) {
                for (int i = 0; i < hostCorrect.length ; i++) {

                    if (hostCorrect[i].equals(hosts[i])){
                        System.out.println("wait " + hostCorrect[i] + " result " + hosts[i] + " [TRUE]");
                    } else {
                        System.out.println("wait " + hostCorrect[i] + " result " + hosts[i] + " [FALSE]");
                    }


                }
            } else {
                System.out.println("Array sizes have different lengths");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testPostParser(){
        String[] portCorrect = {"32400-32402", "80"};
        try {

            String[] ports = Data.parsePorts(cmd);

            if (portCorrect.length == ports.length) {
                for (int i = 0; i < portCorrect.length ; i++) {

                    if (portCorrect[i].equals(ports[i])){
                        System.out.println("wait " + portCorrect[i] + " result " + ports[i] + " [TRUE]");
                    } else {
                        System.out.println("wait " + portCorrect[i] + " result " + ports[i] + " [FALSE]");
                    }
                }
            } else {
                System.out.println("Array sizes have different lengths");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testThreadParser(){
        int threadCorrect = 2;
        try {

            int thread = Data.parseThreads(cmd);

            if (threadCorrect == thread) {
                System.out.println("wait " + threadCorrect + " result " + thread + " [TRUE]");

            } else {
                System.out.println("wait " + threadCorrect + " result " + thread + " [FALSE]");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
