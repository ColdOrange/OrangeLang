package com.orange.lang.ast;

import com.orange.lang.Environment;

import java.util.List;

/**
 * Created by Orange on 2/21/16.
 */
public class BlockStmnt extends ASTList {
    public BlockStmnt(List<ASTree> list) {
        super(list);
    }

    @Override
    public Object eval(Environment env) {
        Object result = 0;
        for (ASTree t: this) {
            if (! (t instanceof NullStmnt)) {
                result = ((ASTree)t).eval(env);
            }
        }
        return result;
    }
}
