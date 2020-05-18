package scanner3000;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.FileWriter;
import java.io.IOException;

public class WriteFile {
    public static void toJson(String fileneame){
        Gson gson = new Gson();
        JsonObject rootObject = new JsonObject(); // создаем главный объект
        rootObject.add("Scan result", gson.toJsonTree(ScanResult.results));



        try(FileWriter writer = new FileWriter(fileneame, false))
        {
            writer.write(gson.toJson(rootObject));
            writer.flush();
        }
        catch(IOException ex){

            System.err.println(ex.getMessage());
        }
        finally {
            System.out.println("Written to file " + fileneame);
        }


    }

}
