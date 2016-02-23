package com.orange.lang.ast;

import com.orange.lang.Token;

/**
 * Created by Orange on 2/21/16.
 */
public class StringLiteral extends ASTLeaf {
    public StringLiteral(Token t) {
        super(t);
    }
    public String value() {
        return token.getText();
    }
}
