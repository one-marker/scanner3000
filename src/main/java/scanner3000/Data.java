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


    /**
     *
     * @param fileneame
     * @param results
     */
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

    /**
     * This method prints scan results to the console.
     * @param results The input is a reference to a list that has a special data type Result.
     */
    public static void printResults(List<Result> results){
        for (Object result: results) {
            System.out.println(result.toString());
        }

    }
    /**
     * The method selects all hosts from the string and puts them in an array of strings
     * Takes an input String with a command cmd.
     * @param cmd This String has format <b>scan -h hostname -p port -t k-threads</b>
     * @return Returns an array of strings Strings[]
     * @throws Exception If the string cannot be converted to an array, this exception is thrown
     */
    public static String[] parseHosts(String cmd) throws Exception{
        return cmd.substring(cmd.indexOf("-h") + 2, cmd.indexOf("-p") - 1).replaceAll(" ", "").split(",");
    }
    /**
     * The method selects all ports from the string and puts them in an array of strings
     * Takes an input String with a command cmd.
     * @param cmd This String has format <b>scan -h hostname -p port -t k-threads</b>
     * @return Returns an array of strings Strings[]
     * @throws Exception If the string cannot be converted to an array, this exception is thrown
     */
    public static String[] parsePorts(String cmd) throws Exception{
        if(!cmd.contains("-t")){
            cmd = cmd.concat(" -t 1");
        }
        return cmd.substring(cmd.indexOf("-p") + 2, cmd.indexOf("-t") - 1).replaceAll(" ", "").split(",");
    }
    /**
     * The method selects the specified number of threads in the String marked with -t and returns Integer
     * Takes an input String with a command cmd.
     * @param cmd This String has format <b>scan -h hostname -p port -t k-threads</b>
     * @return Returns an array of strings Strings[]
     * @throws Exception If the string cannot be converted to an array, this exception is thrown
     */
    public static int parseThreads(String cmd) throws Exception{
        if(!cmd.contains("-t")){
            cmd = cmd.concat(" -t 1");
        }
        return Integer.parseInt(cmd.substring(cmd.indexOf("-t") + 2).replaceAll(" ", ""));
    }

}
