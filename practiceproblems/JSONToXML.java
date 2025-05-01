import org.json.JSONObject;
import org.json.XML;

public class JSONToXML {
    public static void main(String[] args) {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("name", "AAA");
        jsonObj.put("age", 22);
        jsonObj.put("email", "aaa@gmail.com");

        String xml = XML.toString(jsonObj);
        System.out.println(xml);
    }
}