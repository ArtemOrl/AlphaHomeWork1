package com.alpha.MultiMap;

import java.io.Serializable;
import java.util.*;

public class MultiMap<K, V> extends HashMap<K, V> implements Cloneable, Serializable  {



        static final long serialVersionUID = 123456789L;
        private HashMap<K, List<V>> map;
        private int repeatCount;

    public MultiMap(int repeatCount) {
        this.repeatCount = repeatCount;
        map = new HashMap<>();
    }

// 1) int size() — должен возвращать количество значений в нашей коллекции.
        @Override
        public int size() {
        return values().size();
    }

//    2) V put(K key, V value) — должен добавить элемент value по ключу key. Если в мапе такой ключ уже есть,
//    и количество значений по этому ключу меньше, чем repeatCount — то добавь элемент value в конец листа
//    в объекте map. Если по такому ключу количество значений равняется repeatCount — то удали из листа в
//    объекте map элемент с индексом ноль, и добавь в конец листа value. Метод должен возвращать значение
//    последнего добавленного элемента по ключу key (но не значение, которое мы сейчас добавляем).
//    Если по ключу key значений еще нет — верни null.
        @Override
        public V put(K key, V value) {
        V valueOld = null;
        List<V> list = null;
        if (map.size()==0 || !map.containsKey(key)) {
            list = new ArrayList<>();
            list.add(value);
            map.put(key, list);
        }
        else{

            for (Map.Entry entry: map.entrySet()) {
                if (entry.getKey().equals(key)){

                    list = (List<V>) entry.getValue();

                    if (list==null) list = new ArrayList<>();

                    if (repeatCount > list.size()){
                        list.add(value);
                        map.put(key, list);
                    } else if (list.size() == repeatCount){
                        valueOld = list.get(list.size()-1);
                        list.remove(0);
                        list.add(value);
                    }
                }
            }
        }
        if (valueOld==null) return null;
        return valueOld;
    }

//    3) V remove(Object key) — должен удалить элемент по ключу key. Если по этому ключу хранится несколько
//    элементов — должен удаляться элемент из листа с индексом ноль. Если по какому-то ключу хранится лист
//    размером ноль элементов — удали такую пару ключ : значение. Метод должен возвращать элемент, который ты удалил.
//    Если в мапе нет ключа key — верни null.
        @Override
        public V remove(Object key) {
        List<V> list = null;
        for (Map.Entry entry: map.entrySet()) {
            if (key.equals(entry.getKey())){
                list = (List<V>) entry.getValue();
                if (list.size()==0) {
                    map.remove(entry.getKey());
                    return (V) key;
                }
                else if (list.size()!=0){
                    V value = list.get(0);
                    list.remove(0);
                    map.put((K)key, list);
                    return value;
                }
            }
        }
        return null;
    }

//  4) Set<K> keySet() — должен вернуть сет всех ключей, которые есть в мапе map.
        @Override
        public Set<K> keySet() {
        return map.keySet();
    }
//  5) Collection<V> values() — должен вернуть ArrayList<V> всех значений.
//  Порядок значений в листе не имеет значения.
        @Override
        public Collection<V> values() {
        ArrayList<V> values = new ArrayList<>();
        for (List<V> value : map.values()) {
            values.addAll(value);
        }
        return values;
    }
//  6) boolean containsKey(Object key) — должен вернуть true,
//  если в мапе присутствует ключ key, иначе вернуть false.
        @Override
        public boolean containsKey(Object key) {
        for (K k : map.keySet()) {
            if (k.equals(key)) return true;
        }
        return false;
    }
//  7) boolean containsValue(Object value) — должен вернуть true, если в мапе присутствует значение value,
//     иначе вернуть false.
        @Override
        public boolean containsValue(Object value) {
        ArrayList<V> values = (ArrayList<V>) values();
        if (values.contains(value)) return true;
        return false;
    }

        @Override
        public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            for (V v : entry.getValue()) {
                sb.append(v);
                sb.append(", ");
            }
        }
        String substring = sb.substring(0, sb.length() - 2);
        return substring + "}";
    }
}
