package redis;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CopyOnWriteMap<K,V> implements Map<K,V>,Cloneable {

    private volatile Map<K,V> internalMap;

    public CopyOnWriteMap(){
        internalMap = new HashMap<>();
    }
    public CopyOnWriteMap(int initialCapacity){
        internalMap = new HashMap<K,V>(initialCapacity);
    }

    public CopyOnWriteMap(Map<K, V> data) {

        internalMap = new HashMap<K, V>(data);

    }
    @Override
    public V put(K key,V value){
        synchronized (this){
            Map<K,V> newMap = new HashMap<>(internalMap);
            V val = newMap.put(key,value);
            internalMap = newMap;
            return val;
        }
    }
    @Override
    public V remove(Object o) {
        synchronized (this){
            Map<K,V> newMap = new HashMap<>(internalMap);
            V val = newMap.remove(keySet());
            internalMap = newMap;
            return val;
        }
    }



    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(Object o) {
        return false;
    }

    @Override
    public boolean containsValue(Object o) {
        return false;
    }

    @Override
    public V get(Object o) {
        return null;
    }




    @Override
    public void putAll(Map<? extends K, ? extends V> map) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }
}
