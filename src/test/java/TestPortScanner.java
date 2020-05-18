import org.junit.Assert;
import org.junit.Test;
import scanner3000.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class TestPortScanner {




    @Test
    public void testScan() throws InterruptedException {
        System.out.println("Test");

        List<Result> resultsCorrect = Collections.synchronizedList(new ArrayList<>());
        resultsCorrect.add(new Result("google.com", "80", true));
        resultsCorrect.add(new Result("yandex.ru", "80", true));
        List<Result> results = Collections.synchronizedList(new ArrayList<>());

        String[] hosts = {"google.com","yandex.ru"};
        String[] ports = {"80"};
        PortScanner.start(hosts, ports, 1, results);

        Thread.sleep(100);
        System.out.println(results);
        System.out.println(resultsCorrect);
        System.out.println(results.equals(resultsCorrect));

//        Iterator<Result> iterator = results.iterator();
//        Iterator<Result> iteratorCorrect = resultsCorrect.iterator();
//
//        while (iterator.hasNext()){
//            System.out.println(iterator.next().toString());
//            //System.out.println(iterator.next().equals(iteratorCorrect.next()));
//        }

    }
}
