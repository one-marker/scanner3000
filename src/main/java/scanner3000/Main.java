package scanner3000;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        int threads;
        //scan -h evlentev.ru, 2232 -p 32400, 2320,32,32,56454,454,32400

        Scanner sc = new Scanner(System.in);
        //Writer.out("Port scanner 3000:");
        System.out.println("Port scanner 3000:");

      //  Object

        while (true) {

            ScanResult.results.clear();
            String cmd = sc.nextLine();

            String[] hosts;
            String[] ports;

            try {
                hosts = cmd.substring(cmd.indexOf("-h") + 2, cmd.indexOf("-p") - 1).replaceAll(" ", "").split(",");
                if(!cmd.contains("-t")){
                    cmd = cmd.concat(" -t 1");
                }
                ports = cmd.substring(cmd.indexOf("-p") + 2, cmd.indexOf("-t") - 1).replaceAll(" ", "").split(",");
                threads = Integer.parseInt(cmd.substring(cmd.indexOf("-t") + 2).replaceAll(" ", ""));


                if (hosts[0].isEmpty() || ports[0].isEmpty()) {
                    if (hosts[0].isEmpty()) {
                        System.err.println("Enter hosts");
                    }
                    if (ports[0].isEmpty()) {
                        System.err.println("Enter ports");
                    }
                } else {

                    System.out.println("Start scaning...");

                    PortScanner.start(hosts, ports, threads);

                    System.out.println("Scanning successful!");


                    WriteFile.toJson("output.json");

                }
            } catch (Exception e) {
                System.err.println("Incorrect format!\nUse this format -> #scan -h hostname -p port -t k-threads");
            }

        }

    }




}
