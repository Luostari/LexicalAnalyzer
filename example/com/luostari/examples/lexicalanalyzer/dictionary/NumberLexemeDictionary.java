package com.luostari.examples.lexicalanalyzer.dictionary;

import com.luostari.lexicalanalyzer.DynamicLexeme;
import com.luostari.lexicalanalyzer.LexemeDictionary;
import com.luostari.lexicalanalyzer.StaticLexeme;

public class NumberLexemeDictionary {
    static public LexemeDictionary build() {
        LexemeDictionary lexemeDictionary = new LexemeDictionary();
        lexemeDictionary.staticLexemes = new StaticLexeme[]{
        };

        lexemeDictionary.dynamicLexemes = new DynamicLexeme[]{
                new DynamicLexeme("number", "[+-]?([0-9]+([.][0-9]*)?|[.][0-9]+)"),
        };

        lexemeDictionary.spaceChars = new char[]{' ', '\n', '\r', '\t', ','};
        lexemeDictionary.dividingChars = new char[]{};
        return lexemeDictionary;
    }
}
