package com.luostari.examples.lexicalanalyzer;

//import com.luostari.examples.lexicalanalyzer;

public class LexicalanalyzerTest{
	public static void main(String[] args)    {
		MyHandler myHandler = new MyHandler();
		String in = "12.05, 345.45, 25 ";
		System.out.println("String with words and numbers: " + in);
		System.out.println("");		
		myHandler.parseNumber(in);
		System.out.println("");		
		in = ".streets{\nclient_shadow:0,0,4,0.25,#FFFFFF;\nfill:#444444;fill-texture:url(asphalt);\n}\n.floor{\nfill:#ecf2f9;opacity:0.10;\n}";
		System.out.println("");		
		System.out.println("String represents some simple CSS: " + in);
		System.out.println("");		
		myHandler.parseCSS(in);		
		System.out.println("");		
		in = "12.05, bla_bla_bla 345.45, 25 ";
		System.out.println("String with unknown lexeme: " + in);
		myHandler.parseNumberWithUnknownLexeme(in);
	}
}

