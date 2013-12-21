/*
 *
 * Copyright 2013 Julian Mendez
 *
 */

package de.tudresden.inf.lat.wikihtml.common;

/**
 * 
 * @author Julian Mendez
 * 
 */
public interface WikiCons {

	String A_BACKSLASH = "\\";
	String AMPERSAND = "&";
	String AMPERSAND_NUMBER_SIGN = "&#";
	String AMPERSAND_NUMBER_SIGN_U = "&&#x0023;";
	String AMPERSAND_U = "&#x0026;";
	String APOSTROPHE = "'";
	String APOSTROPHE_U = "&#x0027;";
	String ASTERISK = "*";
	String ASTERISK_E = A_BACKSLASH + ASTERISK;
	String ASTERISK_U = "&#x002A;";
	String BACKSLASH = A_BACKSLASH;
	String BACKSLASH_E = A_BACKSLASH + BACKSLASH;
	String BACKSLASH_U = "&#x005C;";
	String COLON = ":";
	String COLON_U = "&#x003A;";
	String COMMA = ",";
	String COMMA_U = "&#x002C;";
	String EQUAL_SIGN = "=";
	String EQUAL_SIGN_U = "&#x003D;";
	String GREATER_THAN_SIGN = ">";
	String GREATER_THAN_SIGN_U = "&#x003E;";
	String HYPHEN_MINUS = "-";
	String HYPHEN_MINUS_U = "&#x002D;";
	String LEFT_CURLY_BRACKET = "{";
	String LEFT_CURLY_BRACKET_E = A_BACKSLASH + LEFT_CURLY_BRACKET;
	String LEFT_CURLY_BRACKET_U = "&#x007B;";
	String LESS_THAN_SIGN = "<";
	String LESS_THAN_SIGN_U = "&#x003C;";
	String LINK_SPACE = " ";
	String LINK_UNDERSCORE = "_";
	String NEW_CELL = "|";
	String NEW_CELL_SAME_LINE = "||";
	String NEW_LINE = "\n";
	String NEW_ROW = "|-";
	String NUMBER_SIGN = "#";
	String NUMBER_SIGN_U = "&#x0023;";
	String RIGHT_CURLY_BRACKET = "}";
	String RIGHT_CURLY_BRACKET_E = A_BACKSLASH + RIGHT_CURLY_BRACKET;
	String RIGHT_CURLY_BRACKET_U = "&#x007D;";
	String SEMICOLON = ";";
	String SLASH = "/";
	String SLASH_U = "&#x002F;";
	String SPACE = " ";
	String TAB = "\t";
	String TABLE_ALT_COMMA_START = "{||,";
	String TABLE_ALT_END = "||}";
	String TABLE_ALT_SEMICOLON_START = "{||;";
	String TABLE_ALT_TAB_START = "{||";
	String TABLE_END = "|}";
	String TABLE_START = "{|";
	String TEMPLATE_BEGIN = LEFT_CURLY_BRACKET + LEFT_CURLY_BRACKET
			+ LEFT_CURLY_BRACKET;
	String TEMPLATE_END = RIGHT_CURLY_BRACKET + RIGHT_CURLY_BRACKET
			+ RIGHT_CURLY_BRACKET;
	String TILDE = "~";
	String TILDE_U = "&#x007E;";
	String VAR_BEGIN = LEFT_CURLY_BRACKET + LEFT_CURLY_BRACKET;
	String VAR_END = RIGHT_CURLY_BRACKET + RIGHT_CURLY_BRACKET;
	String VERTICAL_BAR = "|";
	String VERTICAL_BAR_E = A_BACKSLASH + VERTICAL_BAR;
	String VERTICAL_BAR_U = "&#x007C;";

}
