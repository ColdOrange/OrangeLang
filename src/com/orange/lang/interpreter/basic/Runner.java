package com.orange.lang.interpreter.basic;

import javassist.gluonj.util.Loader;

/**
 * Created by Orange on 2/22/16.
 */
public class Runner {
    public static void main(String[] args) throws Throwable {
        Loader.run(BasicInterpreter.class, args, BasicEvaluator.class);
    }
}
