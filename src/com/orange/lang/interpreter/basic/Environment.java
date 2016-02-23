package com.orange.lang.interpreter.basic;

/**
 * Created by Orange on 2/22/16.
 */
public interface Environment {
    void put(String name, Object value);
    Object get(String name);
}
