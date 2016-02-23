package com.orange.lang;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Orange on 2/13/16.
 */
public class Lexer {
    public static String regexPat
            = "\\s*"                                        // Empty
            + "((//.*)|"                                    // Annotation
            + "([0-9]+)|"                                   // Number
            + "(\"(\\\\\"|\\\\\\\\|\\\\n|[^\"])*\")|"       // String Literal
            + "[A-Z_a-z][A-Z_a-z0-9]*|"                     // Identifier
            + "==|<=|>=|&&|\\|\\||\\p{Punct})?";            // Punctuation
    private Pattern pattern = Pattern.compile(regexPat);
    private ArrayList<Token> queue = new ArrayList<Token>();
    private boolean hasMore;
    private LineNumberReader reader;

    public Lexer(Reader r) {
        hasMore = true;
        reader = new LineNumberReader(r);
    }


    public Token read() throws ParseException {
        if (fillQueue(0)) {
            return queue.remove(0);
        } else {
            return Token.EOF;
        }
    }
    public Token peek(int i) throws ParseException {
        if (fillQueue(i)) {
            return queue.get(i);
        } else {
            return Token.EOF;
        }
    }
    private boolean fillQueue(int i) throws ParseException {
        while (queue.size() <= i) {
            if (hasMore) {
                readLine();
            } else {
                return false;
            }
        }
        return true;
    }
    private void readLine() throws ParseException {
        String line;
        try {
            line = reader.readLine();
        } catch (IOException e) {
            throw new ParseException(e.toString());
        }
        if (line == null) {
            hasMore = false;
            return;
        }

        int lineNumber = reader.getLineNumber();
        Matcher matcher = pattern.matcher(line);
        matcher.useTransparentBounds(true).useAnchoringBounds(false);
        int pos = 0;
        int endPos = line.length();
        while (pos < endPos) {
            matcher.region(pos, endPos);
            if (matcher.lookingAt()) {
                addToken(lineNumber, matcher);
                pos = matcher.end();
            } else {
                throw new ParseException("bad token at line " + lineNumber);
            }
        }

        queue.add(new IdToken(lineNumber, Token.EOL));
    }
    protected void addToken(int lineNumber, Matcher matcher) {
        String m = matcher.group(1);
        if (m != null) {                                // Not Empty
            if (matcher.group(2) == null) {             // Not Annotation
                Token token;
                if (matcher.group(3) != null) {         // Is Number
                    token = new NumToken(lineNumber, Integer.parseInt(m));
                }
                else if (matcher.group(4) != null) {    // Is String Literal
                    token = new StrToken(lineNumber, toStringLiteral(m));
                }
                else {                                  // Is Identifier
                    token = new IdToken(lineNumber, m);
                }
                queue.add(token);
            }
        }
    }
    protected String toStringLiteral(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        int len = s.length() - 1;
        for (int i = 1; i < len; i++) {
            char c = s.charAt(i);
            if (c == '\\' && i + 1 < len) {
                int c2 = s.charAt(i + 1);
                if (c2 == '"' || c2 == '\\') {
                    c = s.charAt(++i);
                } else if (c2 == 'n') {
                    ++i;
                    c = '\n';
                }
            }
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }

    protected static class IdToken extends Token {
        private String id;

        protected IdToken(int line, String text) {
            super(line);
            id = text;
        }
        @Override
        public boolean isIdentifier() { return true; }
        @Override
        public String getText() { return id; }
    }
    protected static class NumToken extends Token {
        private int value;

        protected NumToken(int line, int val) {
            super(line);
            value = val;
        }
        @Override
        public boolean isNumber() { return true; }
        @Override
        public String getText() { return Integer.toString(value); }
        @Override
        public int getNumber() { return value; }
    }
    protected static class StrToken extends Token {
        private String literal;

        protected StrToken(int line, String str) {
            super(line);
            literal = str;
        }
        @Override
        public boolean isString() { return true; }
        @Override
        public String getText() { return literal; }
    }
}
