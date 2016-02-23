package com.orange.lang.ast;

import java.util.Iterator;

/**
 * Created by Orange on 2/15/16.
 */
public abstract class ASTree implements Iterable<ASTree> {
    public abstract ASTree child(int i);
    public abstract int numChildren();
    public abstract Iterator<ASTree> children();
    public abstract String location();
    @Override
    public Iterator<ASTree> iterator() { return children(); }
}
