import org.junit.Assert;
import org.junit.Test;
import scanner3000.*;

import java.util.*;

public class TestPortScanner extends Assert {


    List<Result> actualResults = Collections.synchronizedList(new ArrayList<>());
    List<Result> expectedResults = Collections.synchronizedList(new ArrayList<>());
    @Test
    public void twoHostTest() throws InterruptedException {



        expectedResults.add(new Result("google.com", "80", true));
        expectedResults.add(new Result("yandex.ru", "80", true));
        String[] hosts = {"google.com","yandex.ru"};
        String[] ports = {"80"};

        new PortScanner().start(hosts, ports, 1, actualResults);

        Thread.sleep(100);

        assertEquals(Arrays.asList(expectedResults),
                Arrays.asList(actualResults));
        actualResults.clear();
        expectedResults.clear();

    }

    @Test
    public void portRangeTest() throws InterruptedException {

        expectedResults.add(new Result("google.com", "80", true));
        expectedResults.add(new Result("google.com", "81", false));
        String[] hosts = {"google.com"};
        String[] ports = {"80-81"};

        new PortScanner().start(hosts, ports, 1, actualResults);

        Thread.sleep(100);

        assertEquals(Arrays.asList(expectedResults),
                Arrays.asList(actualResults));
        actualResults.clear();
        expectedResults.clear();
    }

    @Test
    public void portRangeFailTest() throws InterruptedException {

        expectedResults.add(new Result("google.com", "81", false));
        expectedResults.add(new Result("google.com", "82", false));
        String[] hosts = {"google.com"};
        String[] ports = {"81-82"};

        new PortScanner().start(hosts, ports, 1, actualResults);

        Thread.sleep(100);

        assertEquals(Arrays.asList(expectedResults),
                Arrays.asList(actualResults));
        actualResults.clear();
        expectedResults.clear();
    }




}
