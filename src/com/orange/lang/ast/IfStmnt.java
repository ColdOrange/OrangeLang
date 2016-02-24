package com.orange.lang.ast;

import com.orange.lang.Environment;

import java.util.List;

/**
 * Created by Orange on 2/21/16.
 */
public class IfStmnt extends ASTList {
    public IfStmnt(List<ASTree> list) {
        super(list);
    }

    public ASTree condition() {
        return child(0);
    }
    public ASTree thenBlock() {
        return child(1);
    }
    public ASTree elseBlock() {
        return numChildren() > 2 ? child(2) : null;
    }

    @Override
    public String toString() {
        return "(if " + condition() + " " + thenBlock() + " else " + elseBlock() + ")";
    }
    @Override
    public Object eval(Environment env) {
        Object c = ((ASTree)condition()).eval(env);
        if (c instanceof Integer && ((Integer)c).intValue() != FALSE) {
            return ((ASTree)thenBlock()).eval(env);
        } else {
            ASTree b = elseBlock();
            if (b == null) {
                return 0;
            } else {
                return ((ASTree)b).eval(env);
            }
        }
    }
}
