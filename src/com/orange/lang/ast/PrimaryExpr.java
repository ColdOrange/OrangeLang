package com.orange.lang.ast;

import java.util.List;

/**
 * Created by Orange on 2/21/16.
 */
public class PrimaryExpr extends ASTList {
    public PrimaryExpr(List<ASTree> list) {
        super(list);
    }
    public static ASTree create(List<ASTree> list) {
        return list.size() == 1 ? list.get(0) : new PrimaryExpr(list);
    }
}
