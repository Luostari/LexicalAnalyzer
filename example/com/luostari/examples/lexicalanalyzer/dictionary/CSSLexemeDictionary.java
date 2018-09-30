package com.luostari.examples.lexicalanalyzer.dictionary;

import com.luostari.lexicalanalyzer.LexemeDictionary;
import com.luostari.lexicalanalyzer.StaticLexeme;
import com.luostari.lexicalanalyzer.DynamicLexeme;

public class CSSLexemeDictionary {
    static public LexemeDictionary build() {
        LexemeDictionary lexemeDictionary = new LexemeDictionary();
        lexemeDictionary.staticLexemes = new StaticLexeme[]{
                new StaticLexeme(".", false),
                new StaticLexeme("{", false),
                new StaticLexeme("}", false),
                new StaticLexeme(":", false),
                new StaticLexeme(";", false),
                new StaticLexeme(",", false),
                new StaticLexeme("(", false),
                new StaticLexeme(")", false),
                new StaticLexeme("client_shadow", true),
                new StaticLexeme("fill", true),
                new StaticLexeme("fill-texture", true),
                new StaticLexeme("stroke", true),
                new StaticLexeme("stroke-width", true),
                new StaticLexeme("stroke-linecap", true),
                new StaticLexeme("stroke-miterlimit", true),
                new StaticLexeme("opacity", true),
                new StaticLexeme("url", true)
        };

        lexemeDictionary.dynamicLexemes = new DynamicLexeme[]{
                new DynamicLexeme("number", "[+-]?([0-9]+([.][0-9]*)?|[.][0-9]+)"),
                new DynamicLexeme("identifier", "[a-zA-Z_][a-zA-Z0-9_-]*"),
                new DynamicLexeme("color", "[#][0-9a-fA-F]{6}")
        };

        lexemeDictionary.spaceChars = new char[]{' ', '\n', '\r', '\t'};
        lexemeDictionary.dividingChars = new char[]{' ', '\n', '\r', '\t', ':', '(', ')'};
        return lexemeDictionary;
    }
}
