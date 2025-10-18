import java.util.concurrent.ConcurrentHashMap;

public class Registry {
    private static final ConcurrentHashMap<String, Customer> MAP = new ConcurrentHashMap<>();

    public static void save(String id, Customer c) { MAP.put(id, c); }
    public static Customer find(String id)         { return MAP.get(id); }
}