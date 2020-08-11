import org.junit.Test;

import java.util.HashMap;

public class IntegerTest {
    @Test
    public void testInteger() {
        int i = Integer.parseInt("");
        System.out.println(i);
    }

    @Test
    public void testMap() {
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("1", null);
        stringStringHashMap.put("2", null);
        stringStringHashMap.put("1", null);
        System.out.println(stringStringHashMap.size());
    }
}
