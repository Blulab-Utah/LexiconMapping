import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GenerateCuiTree {

    static final String REST_URL = "http://data.bioontology.org";
    static final String API_KEY = "7e1d7619-0e40-4eea-859d-04641e8445d6";
    static final ObjectMapper mapper = new ObjectMapper();

    public static void main(String args[]) throws InterruptedException {
        JsonNode resources = jsonToNode(get(REST_URL + "/ontologies/SNOMEDCT/classes/http%3A%2F%2Fpurl.bioontology.org%2Fontology%2FSNOMEDCT%2F303071001"));
        if (resources.size() != 0) {
            String cui = String.valueOf(resources.get("cui"));
            String s = "CUI,Preferred_Label,Synonym\n";
            s+=cui+","+resources.get("prefLabel")+","+resources.get("synonym");
            System.out.println(s);
//            System.out.println("CUI: " + cui);
//            System.out.println("Preferred Label: " + resources.get("prefLabel"));
//            System.out.println("Synonym: " + resources.get("synonym") + "\n");

            JsonNode childrenResources = jsonToNode(get(resources.get("links").findValue("children").asText()));
            processCuis(childrenResources);
        }
    }

    private static void processCuis(JsonNode childrenResources) throws InterruptedException {
        if (null != childrenResources && childrenResources.size() != 0 && childrenResources.has("collection")) {
            JsonNode collection = childrenResources.get("collection");
            for (JsonNode child : collection) {
                System.out.println(child.get("cui")+","+child.get("prefLabel")+","+child.get("synonym"));
//                System.out.println(String.valueOf("CUI: " + child.get("cui")));
//                System.out.println(String.valueOf("Preferred Label: " + child.get("prefLabel")));
//                System.out.println(String.valueOf("Synonym: " + child.get("synonym")) + "\n");
                if (child.has("links") && child.get("links").has("children")) {
                    JsonNode newChild = jsonToNode(get(child.get("links").findValue("children").asText()));
                    processCuis(newChild);
                }
            }
        }
    }


    private static String get(String urlToGet) {
        URL url;
        HttpURLConnection conn;
        BufferedReader rd;
        String line;
        String result = "";
        try {
            url = new URL(urlToGet);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "apikey token=" + API_KEY);
            conn.setRequestProperty("Accept", "application/json");
            Thread.sleep(100L);
            rd = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            while ((line = rd.readLine()) != null) {
                result += line;
            }
            rd.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static JsonNode jsonToNode(String json) throws InterruptedException {
        JsonNode root = null;
        Thread.sleep(100L);
        try {
            root = mapper.readTree(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return root;
    }

}
