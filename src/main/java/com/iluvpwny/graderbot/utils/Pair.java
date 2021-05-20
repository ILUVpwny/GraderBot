package com.iluvpwny.graderbot.utils;

public class Pair<K,V> {
    private V value;
    private K key;

    public Pair(K key, V value) {
        this.value = value;
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public K getKey() {
        return key;
    }
}
