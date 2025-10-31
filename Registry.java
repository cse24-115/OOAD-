import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

public class Registry {
    private static final ConcurrentHashMap<String, Customer> MAP = new ConcurrentHashMap<>();

    /** Save or update a customer by ID */
    public static void save(String id, Customer c) {
        MAP.put(id, c);
    }

    /** Find a customer by ID */
    public static Customer find(String id) {
        return MAP.get(id);
    }

    /** Get all registered customers */
    public static Collection<Customer> getAllCustomers() {
        return MAP.values();
    }

    /** Remove a customer (optional) */
    public static void remove(String id) {
        MAP.remove(id);
    }

    /** Check if a customer exists */
    public static boolean exists(String id) {
        return MAP.containsKey(id);
    }
}
