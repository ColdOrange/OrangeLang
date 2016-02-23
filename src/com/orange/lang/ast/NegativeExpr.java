package com.orange.lang.ast;

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
}
