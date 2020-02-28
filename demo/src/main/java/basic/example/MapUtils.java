package basic.example;

import java.util.HashMap;
import java.util.Map;

public class MapUtils {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put(null, "Singh");
        map.put("Shailendra", "ITG");

        map.replace("Shailendra", "OOO");



        map.entrySet().forEach(e -> System.out.println(e.getKey()+":"+e.getValue()));
    }
}
