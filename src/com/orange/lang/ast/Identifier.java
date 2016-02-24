package com.orange.lang.ast;

import com.orange.lang.Environment;
import com.orange.lang.StoneException;
import com.orange.lang.Token;

/**
 * Created by Orange on 2/21/16.
 */
public class Identifier extends ASTLeaf {
    public Identifier(Token t) { super(t); }
    public String name() { return token.getText(); }
    @Override
    public Object eval(Environment env) {
        Object value = env.get(name());
        if (value == null) {
            throw new StoneException("undefined name: " + name(), this);
        } else {
            return value;
        }
    }
}
