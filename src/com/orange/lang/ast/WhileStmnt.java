package com.orange.lang.ast;

import com.orange.lang.Environment;

import java.util.List;

/**
 * Created by Orange on 2/21/16.
 */
public class WhileStmnt extends ASTList {
    public WhileStmnt(List<ASTree> list) {
        super(list);
    }

    public ASTree condition() {
        return child(0);
    }
    public ASTree body() {
        return child(1);
    }

    @Override
    public String toString() {
        return "(while " + condition() + " " + body() + ")";
    }
    @Override
    public Object eval(Environment env) {
        Object result = 0;
        for (;;) {
            Object c = ((ASTree)condition()).eval(env);
            if (c instanceof Integer && ((Integer)c).intValue() == FALSE)
                return result;
            else
                result = ((ASTree)body()).eval(env);
        }
    }
}
