package com.orange.lang;

import com.orange.lang.ast.ASTree;

/**
 * Created by Orange on 2/13/16.
 */
public class StoneException extends RuntimeException {
    public StoneException(String msg) {
        super(msg);
    }
    public StoneException(String msg, ASTree ast) {
        super(msg + " " + ast.location());
    }
}
