/*
 *
 * Copyright 2013 Julian Mendez
 *
 */

package de.tudresden.inf.lat.wikihtml.renderer.common;

/**
 * 
 * @author Julian Mendez
 * 
 */
public interface ConversionToken {

	String getHTMLText();

	TokenType getType();

	String getWikiText();

}
