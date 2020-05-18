package scanner3000;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws InterruptedException, IOException {

        //scan -h evlentev.ru, 2232 -p 32400, 2320,32,32,56454,454,32400


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

                    PortScanner.start(hosts, ports, threads, results);
                    Data.toJson("output.json", results);

                }
            } catch (Exception e) {
                logger.error("Incorrect format!\nUse this format -> #scan -h hostname -p port -t k-threads");
                System.err.println("Incorrect format!\nUse this format -> #scan -h hostname -p port -t k-threads");
            }

        }

    }




}
