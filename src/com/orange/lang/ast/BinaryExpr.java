package com.orange.lang.ast;

import com.orange.lang.Environment;
import com.orange.lang.StoneException;

import java.util.List;

/**
 * Created by Orange on 2/21/16.
 */
public class BinaryExpr extends ASTList {
    public BinaryExpr(List<ASTree> list) {
        super(list);
    }

    public ASTree left() {
        return child(0);
    }
    public String operator() {
        return ((ASTLeaf)child(1)).getToken().getText();
    }
    public ASTree right() {
        return child(2);
    }

    @Override
    public Object eval(Environment env) {
        String op = operator();
        if ("=".equals(op)) {
            Object right = ((ASTree)right()).eval(env);
            return computeAssign(env, right);
        } else {
            Object left = ((ASTree)left()).eval(env);
            Object right = ((ASTree)right()).eval(env);
            return computOp(left, op, right);
        }
    }
    protected Object computeAssign(Environment env, Object right) {
        ASTree left = left();
        if (left instanceof Identifier) {
            env.put(((Identifier) left).name(), right);
            return right;
        } else {
            throw new StoneException("bad assignment", this);
        }
    }
    protected Object computOp(Object left, String op, Object right) {
        if (left instanceof Integer && right instanceof Integer) {
            return computeNumber((Integer)left, op, (Integer)right);
        } else {
            if (op.equals("+")) {
                return String.valueOf(left) + String.valueOf(right);
            } else if (op.equals("==")) {
                if (left == null) {
                    return right == null ? TRUE : FALSE;
                } else {
                    return left.equals(right) ? TRUE : FALSE;
                }
            } else {
                throw new StoneException("bad type", this);
            }
        }
    }
    protected Object computeNumber(Integer left, String op, Integer right) {
        int a = left.intValue();
        int b = right.intValue();
        if (op.equals("+"))
            return a + b;
        else if (op.equals("-"))
            return a - b;
        else if (op.equals("*"))
            return a * b;
        else if (op.equals("/"))
            return a / b;
        else if (op.equals("%"))
            return a % b;
        else if (op.equals("=="))
            return a == b ? TRUE : FALSE;
        else if (op.equals(">"))
            return a > b ? TRUE : FALSE;
        else if (op.equals("<"))
            return a < b ? TRUE : FALSE;
        else
            throw new StoneException("bad operator", this);
    }
}
