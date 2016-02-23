package com.orange.lang.ast;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Orange on 2/15/16.
 */
public class ASTList extends ASTree {
    protected List<ASTree> children;

    public ASTList(List<ASTree> list) {
        children = list;
    }

    @Override
    public ASTree child(int i) {
        return children.get(i);
    }
    @Override
    public int numChildren() {
        return children.size();
    }
    @Override
    public Iterator<ASTree> children() {
        return children.iterator();
    }
    @Override
    public String location() {
        for (ASTree t: children) {
            String s = t.location();
            if (s != null) {
                return s;
            }
        }
        return null;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("(");
        String sep = "";
        for (ASTree t: children) {
            stringBuilder.append(sep);
            sep = " ";
            stringBuilder.append(t.toString());
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
