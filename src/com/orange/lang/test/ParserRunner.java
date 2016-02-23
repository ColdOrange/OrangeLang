package com.orange.lang.test;

import com.orange.lang.*;
import com.orange.lang.ast.ASTree;

/**
 * Created by Orange on 2/21/16.
 */
public class ParserRunner {
    public static void main(String[] args) throws ParseException {
        Lexer l = new Lexer(new CodeDialog());
        BasicParser basicParser = new BasicParser();
        while (l.peek(0) != Token.EOF) {
            ASTree ast = basicParser.parse(l);
            System.out.println("=> " + ast.toString());
        }
    }
}
