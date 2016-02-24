package com.orange.lang.ast;

import com.orange.lang.Environment;
import com.orange.lang.StoneException;

import java.util.Iterator;

/**
 * Created by Orange on 2/15/16.
 */
public abstract class ASTree implements Iterable<ASTree> {
    public static final int TRUE = 1;   // for evaluation
    public static final int FALSE = 0;

    public abstract ASTree child(int i);
    public abstract int numChildren();
    public abstract Iterator<ASTree> children();
    public abstract String location();
    public abstract Object eval(Environment env);
    @Override
    public Iterator<ASTree> iterator() { return children(); }
}
