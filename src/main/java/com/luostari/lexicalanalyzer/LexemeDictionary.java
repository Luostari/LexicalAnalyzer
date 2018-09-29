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

/**
 * It contains lists of lexemes and methods to determine is char space or dividing.
 * See the example to create lexeme dictionary correctly
 */
public class LexemeDictionary {
    public StaticLexeme[] staticLexemes;
    public DynamicLexeme[] dynamicLexemes;
    public char[] spaceChars;
    public char[] dividingChars;

    /**
     * It determines if char is space or not
     * @param ch char
     * @return true if char is space, false otherwise
     */
    public boolean isSpaceChar(char ch) {
        return (indexOf(spaceChars, ch) >-1);
    }

    /**
     * It determines if char is delimiter or not
     * @param ch char
     * @return true if char is delimiter, false otherwise
     */
    public boolean isDividingChar(char ch) {
        return (indexOf(dividingChars, ch)>-1);
    }

    private int indexOf(char[] arr, char ch) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == ch) return i;
        }
        return -1;
    }
}
