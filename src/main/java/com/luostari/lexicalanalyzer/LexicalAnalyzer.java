/*
	LexicalParser is java library
    Copyright (C) 2018 Vladimir Mezentsev, mezentsevva@gmail.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package com.luostari.lexicalanalyzer;

import java.util.regex.Matcher;

/**
 * Lexical analyzer (lexer) brakes up input string into lexeme in accordance with dictionaries
 * It needs lists of:
 * <p><b>static lexeme</b> - operators and keywords</p>
 * <p><b>dynamic lexeme</b> - identifiers, numbers, words etc.</p>
 * <p><b>array of dividing characters</b> - array of characters dividing keywords. They are necessary
 * to separate keywords and identifiers. For example keyword <i>array</i> and identifier
 * <i>arrayOfSomething</i></p>
 * <p><b>array of blank characters</b> - array of insignificant characters. Lexer will skip them.</p>
 */
public class LexicalAnalyzer {
    private LexemeDictionary lexemeDictionary;
    private String in;
    private int currentPosition;
    private boolean isSafeMode = true;
    private Handler handler;
    private StringBuilder unknown = new StringBuilder();

    /**
     * Constructor
     * @param lexemeDictionary - lexeme dictionary. See an example to create lexeme dictionary
     *                         correctly
     */
    public LexicalAnalyzer(LexemeDictionary lexemeDictionary) {
        this.lexemeDictionary = lexemeDictionary;
    }

    /**
     * Set handler which will be get the results
     * @param handler - handler
     */
    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    /**
     * Indicate that unknown lexeme will throw an exception
     */
    public void setSafeMode() {
        this.isSafeMode = true;
    }

    /**
     * Indicate that unknown lexeme will be skipped and returned to handler with type "unknown"
     */
    public void setUnsafeMode() {
        this.isSafeMode = false;
    }

    /**
     * It parses input string and call handler's method <b>nextLexeme</b> for each lexeme.
     * It throws exception if meets unknown lexeme and it's safe mode.
     * @param in - input string
     * @throws LexicalAnalyzerException - throw when laxer meets unknown lexeme and it's safe mode
     */
    public void parse(String in) throws LexicalAnalyzerException {
        if (handler == null || lexemeDictionary == null)
            return;
        this.in = in;
        currentPosition = 0;
        Lexeme lexeme;
        while (!endOfString()) {
            skipSpaces();
            if (endOfString()) break;
            lexeme = getNextLexeme();
            if (lexeme!=null) {
                if (unknown.length() > 0) {
                    handler.nextLexeme(unknown.toString(), "unknown");
                    unknown = new StringBuilder();
                }
                handler.nextLexeme(lexeme.value, lexeme.type);
            }
            else if (!isSafeMode) {
                unknown.append(in.charAt(currentPosition));
                currentPosition++;
            }
            else throw new LexicalAnalyzerException("Next lexeme was not founded in position " + currentPosition);
        }
    }

    private void skipSpaces() {
        while (!endOfString() && lexemeDictionary.isSpaceChar(currentChar())) {
            currentPosition++;
        }
    }

    private boolean endOfString() {
        return currentPosition>=in.length();
    }

    private char currentChar() {
        return in.charAt(currentPosition);
    }

    private Lexeme getNextLexeme() {
        for (int i = 0; i < lexemeDictionary.staticLexemes.length; i++) {
            String nextLexeme = lexemeDictionary.staticLexemes[i].name;
            boolean isKeyword = lexemeDictionary.staticLexemes[i].isKeyword;
            int offset = currentPosition + nextLexeme.length();
            boolean isEOF = offset == in.length();
            boolean isOutOfBounds = offset > in.length();
            if (!isOutOfBounds && nextLexeme.equals(in.substring(currentPosition, offset))) {
                if (isKeyword) {
                    if (isEOF || lexemeDictionary.isDividingChar(in.charAt(offset))) {
                        currentPosition = offset;
                        return new Lexeme(nextLexeme, "keyword");
                    }
                } else {
                    currentPosition = offset;
                    return new Lexeme(nextLexeme, "operator");
                }
            }
        }

        for (int i = 0; i < lexemeDictionary.dynamicLexemes.length; i++) {
            String nextLexeme = isMatch(lexemeDictionary.dynamicLexemes[i]);
            if (nextLexeme != null) {
                return new Lexeme(nextLexeme, lexemeDictionary.dynamicLexemes[i].name);
            }
        }
        return null;
    }

    private String isMatch(DynamicLexeme dynamicLexeme) {
        Matcher matcher = dynamicLexeme.pattern.matcher(in);
        if (matcher.find(currentPosition) && matcher.start() == currentPosition) {
            currentPosition = matcher.end();
            return matcher.group();
        }
        return null;
    }

    private class Lexeme {
        String value;
        String type;
        Lexeme(String value, String type) {
            this.value = value;
            this.type = type;
        }
    }
}
