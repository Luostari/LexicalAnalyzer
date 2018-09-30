# LexicalAnalyzer
Simple lexical analyser (lexer) to brake up a string into lexeme

<b>LexicalAnalyzer</b> is a very light and simple but enough effective library for breaking up the string into the lexemes according to the dictionary. It was useful in some projects.
<p>For using it, you should create appropriate dictionary of lexemes. 
Lexemes are divided to two types:<br>
<i>Static lexemes</i> – operators and keywords;<br>
<i>Dynamic lexemes</i> – identifiers, numbers, words etc.</p>
<p>Also, you should provide a list of blank chars which mean nothing and will be skipped by analyzer and a list of dividing chars which follow after keywords and separate them from other lexemes.
For more information see examples and docs: https://www.luostari.com/lexicalanalyzer/docs/index.html</p>
