package scanner3000;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Data {
    private static final Logger logger = LoggerFactory.getLogger(Data.class);



    public static void toJson(String fileneame, List<Result> results){
        Gson gson = new Gson();
        JsonObject rootObject = new JsonObject();
        rootObject.add("Scan result", gson.toJsonTree(results));

        try(FileWriter writer = new FileWriter(fileneame, false))
        {
            writer.write(gson.toJson(rootObject));
            writer.flush();
        }
        catch(IOException ex){
            logger.error(ex.getMessage());
            System.err.println(ex.getMessage());
        }
        finally {
            logger.info("Written to file " + fileneame);
            System.out.println("Written to file " + fileneame);
        }

    }
    public static void printResults(List<Object> results){
        for (Object result: results) {
            System.out.println(result.toString());
        }

    }
    public static String[] parseHosts(String cmd) throws Exception{
        return cmd.substring(cmd.indexOf("-h") + 2, cmd.indexOf("-p") - 1).replaceAll(" ", "").split(",");
    }
    public static String[] parsePorts(String cmd) throws Exception{
        if(!cmd.contains("-t")){
            cmd = cmd.concat(" -t 1");
        }
        return cmd.substring(cmd.indexOf("-p") + 2, cmd.indexOf("-t") - 1).replaceAll(" ", "").split(",");
    }
    public static int parseThreads(String cmd) throws Exception{
        if(!cmd.contains("-t")){
            cmd = cmd.concat(" -t 1");
        }
        return Integer.parseInt(cmd.substring(cmd.indexOf("-t") + 2).replaceAll(" ", ""));
    }

}
