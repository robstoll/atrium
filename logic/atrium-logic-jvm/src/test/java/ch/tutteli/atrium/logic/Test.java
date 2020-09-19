package ch.tutteli.atrium.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {

    public List<String> getStrings() {
        ArrayList<String> list = new ArrayList<>();
        list.add(null);
        list.add("hello");
        return list;
    }

    public String[] getStringArray() {
        String[] arr = new String[2];
        return getStrings().toArray(arr);
    }

    public String getString() {
        return null;
    }

    public Map<Integer, String> getNumbersWithString() {
        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, null);
        map.put(2, "hello");
        map.put(null, "tada");
        return map;
    }

    public Integer arg0() {
        return 0;
    }

    public Integer arg1(int a1) {
        return 1;
    }

    public Integer arg2(int a1, int a2) {
        return 2;
    }

    public Integer arg3(int a1, int a2, int a3) {
        return 3;
    }

    public Integer arg4(int a1, int a2, int a3, int a4) {
        return 4;
    }

    public Integer arg5(int a1, int a2, int a3, int a4, int a5) {
        return 5;
    }
}
