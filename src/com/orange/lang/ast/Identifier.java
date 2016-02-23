package com.orange.lang.ast;

import com.orange.lang.Token;

/**
 * Created by Orange on 2/21/16.
 */
public class Identifier extends ASTLeaf {
    public Identifier(Token t) {
        super(t);
    }
    public String name() {
        return token.getText();
    }
}
