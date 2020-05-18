package scanner3000;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScanResult {

    public static List<Object> results = Collections.synchronizedList(new ArrayList<>());

    public static void print(){
        for (Object result: ScanResult.results) {
            System.out.println(result.toString());

        }
    }
}
