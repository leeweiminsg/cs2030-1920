import java.util.Map;
import java.util.HashMap;

class KeyableMap<T, K, V extends Keyable<K>> implements Keyable<T> {
    protected T id;
    protected Map<K, V> map;

    public KeyableMap(T id) {
        this.id = id;
        map = new HashMap<K, V>();
    }

    public V get(K key) {
        return map.get(key);
    }

    public KeyableMap<T, K, V> put(V item) {
        map.put(item.getKey(), item);

        return this;
    }

    public T getKey() {
        return id;
    }

    @Override
    public String toString() {
        return (this.id + ": " + map.values()).replace("[", "{").replace("]", "}");
    }
}