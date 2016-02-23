package com.orange.lang.ast;

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
}
