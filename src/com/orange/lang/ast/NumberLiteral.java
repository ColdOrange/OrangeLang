package com.orange.lang.ast;

import com.orange.lang.Environment;
import com.orange.lang.Token;

/**
 * Created by Orange on 2/21/16.
 */
public class NumberLiteral extends ASTLeaf {
    public NumberLiteral(Token t) { super(t); }
    public int value() { return token.getNumber(); }
    @Override
    public Object eval(Environment env) { return value(); }
}
