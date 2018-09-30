package com.luostari.examples.lexicalanalyzer;

import com.luostari.lexicalanalyzer.Handler;
import com.luostari.lexicalanalyzer.LexicalAnalyzer;
import com.luostari.lexicalanalyzer.LexicalAnalyzerException;
import com.luostari.lexicalanalyzer.LexemeDictionary;
import com.luostari.examples.lexicalanalyzer.dictionary.NumberLexemeDictionary;
import com.luostari.examples.lexicalanalyzer.dictionary.CSSLexemeDictionary;

public class MyHandler implements Handler {

    public void parseNumber(String in) {
		LexemeDictionary dictionary = NumberLexemeDictionary.build();
		parse(in, dictionary, false);
    }
	
    public void parseNumberWithUnknownLexeme(String in) {
		LexemeDictionary dictionary = NumberLexemeDictionary.build();
		System.out.println("");		
		System.out.println("Safe mode with exception");
		parse(in, dictionary, false);
		System.out.println("");		
		System.out.println("Unsafe mode with unknown lexeme");
		parse(in, dictionary, true);
    }
	

    public void parseCSS(String in) {
		LexemeDictionary dictionary = CSSLexemeDictionary.build();
		parse(in, dictionary, false);
    }
	
	private void parse(String in, LexemeDictionary dictionary, boolean unsafeMode) {
        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer(dictionary);
		if (unsafeMode) lexicalAnalyzer.setUnsafeMode();
        lexicalAnalyzer.setHandler(this);
        try {
            lexicalAnalyzer.parse(in);
        } catch (LexicalAnalyzerException e) {
            e.printStackTrace();
        }		
	}
	
   @Override
    public void nextLexeme(String value, String type) {
		System.out.println("type = " + type + " value = " + value);
	}
}
