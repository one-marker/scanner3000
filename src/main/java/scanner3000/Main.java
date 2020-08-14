package scanner3000;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * The main entry point to the program
 */
public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    /**
     * The main entry point to the program. This is where the processing of user commands takes place.
     * Next, the command type is transferred to the Port Scanner, which will add the scan results to the list of results.
     * Next, this list of results will be recorded in a file.
     * @param args
     * @throws InterruptedException
     * @throws IOException
     */

    public static void main(String[] args) throws InterruptedException, IOException {

        //scan -h yande x.ru -p 80-100, 101 -t 1



        //connecting log4j settings
        Properties props = new Properties();
        props.load(new FileInputStream("src/main/resources/log4j.properties"));
        PropertyConfigurator.configure(props);




        List<Result> results = Collections.synchronizedList(new ArrayList<>());



        Scanner sc = new Scanner(System.in);

        System.out.println("Port scanner 3000:");


        while (true) {

            results.clear();
            String cmd = sc.nextLine();

            String[] hosts;
            String[] ports;
            int threads;

            try {
                hosts = Data.parseHosts(cmd);
                ports = Data.parsePorts(cmd);
                threads = Data.parseThreads(cmd);

                if (hosts[0].isEmpty() || ports[0].isEmpty()) {
                    if (hosts[0].isEmpty()) {
                        System.err.println("Enter hosts");
                    }
                    if (ports[0].isEmpty()) {
                        System.err.println("Enter ports");
                    }
                } else {

                    new PortScanner().start(hosts, ports, threads, results);
                    Data.toJson("output.json", results);

                }
            } catch (Exception e) {
                logger.error("Incorrect format!\nUse this format -> #scan -h hostname -p port -t k-threads");
                System.err.println("Incorrect format!\nUse this format -> #scan -h hostname -p port -t k-threads");
            }

        }

    }




}
