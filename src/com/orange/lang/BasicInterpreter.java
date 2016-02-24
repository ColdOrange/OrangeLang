package com.orange.lang;

import com.orange.lang.ast.ASTree;
import com.orange.lang.ast.NullStmnt;

/**
 * Created by Orange on 2/22/16.
 */
public class BasicInterpreter {
    public static void main(String[] args) throws ParseException {
        run(new BasicParser(), new BasicEnv());
    }

    public static void run(BasicParser parser, BasicEnv env) throws ParseException {
        Lexer lexer = new Lexer(new CodeDialog());

        while (lexer.peek(0) != Token.EOF) {
            ASTree ast = parser.parse(lexer);
            if (! (ast instanceof NullStmnt)) {
                Object r = ((ASTree)ast).eval(env);
                System.out.println("=> " + r);
            }
        }
    }
}
