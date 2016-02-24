package com.orange.lang.ast;

import com.orange.lang.Environment;
import com.orange.lang.StoneException;

import java.util.List;

/**
 * Created by Orange on 2/21/16.
 */
public class NegativeExpr extends ASTList {
    public NegativeExpr(List<ASTree> list) {
        super(list);
    }
    public ASTree operand() {
        return child(0);    // "-" is separated
    }
    @Override
    public String toString() {
        return "-" + operand();
    }
    @Override
    public Object eval(Environment env) {
        Object value = ((ASTree)operand()).eval(env);
        if (value instanceof Integer) {
            return new Integer(-((Integer)value).intValue());
        } else {
            throw new StoneException("bad type for -", this);
        }
    }
}
