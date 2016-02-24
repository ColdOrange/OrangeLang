package com.orange.lang.ast;

import com.orange.lang.Environment;
import com.orange.lang.StoneException;
import com.orange.lang.Token;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Orange on 2/15/16.
 */
public class ASTLeaf extends ASTree {
    private static ArrayList<ASTree> empty = new ArrayList<ASTree>();
    protected Token token;

    public ASTLeaf(Token t) {
        token = t;
    }

    @Override
    public ASTree child(int i) {
        throw new IndexOutOfBoundsException();
    }
    @Override
    public int numChildren() {
        return 0;
    }
    @Override
    public Iterator<ASTree> children() {
        return empty.iterator();
    }
    @Override
    public String location() {
        return "at line " + token.getLineNumber();
    }
    @Override
    public Object eval(Environment env) {
        throw new StoneException("cannot eval: " + toString(), this);
    }

    public String toString() {
        return token.getText();
    }

    public Token getToken() {
        return token;
    }
}
