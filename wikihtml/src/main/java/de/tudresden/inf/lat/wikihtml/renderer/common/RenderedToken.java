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
public class RenderedToken implements ConversionToken {

	private final String htmlText;
	private final String wikiText;

	public RenderedToken(String wikiText, String htmlText) {
		if (wikiText == null) {
			throw new IllegalArgumentException("Null argument.");
		}
		if (htmlText == null) {
			throw new IllegalArgumentException("Null argument.");
		}

		this.wikiText = wikiText;
		this.htmlText = htmlText;
	}

	@Override
	public boolean equals(Object o) {
		boolean ret = (this == o);
		if (!ret && (o instanceof ConversionToken)) {
			ConversionToken other = (ConversionToken) o;
			ret = getType().equals(other.getType())
					&& getWikiText().equals(other.getWikiText())
					&& getHTMLText().equals(other.getHTMLText());
		}
		return ret;
	}

	@Override
	public String getHTMLText() {
		return this.htmlText;
	}

	@Override
	public TokenType getType() {
		return TokenType.HTML_TEXT;
	}

	@Override
	public String getWikiText() {
		return this.wikiText;
	}

	@Override
	public int hashCode() {
		return getType().hashCode() + (31 * getWikiText().hashCode());
	}

	@Override
	public String toString() {
		return getWikiText() + "[" + getType() + ": '" + getHTMLText() + "']";
	}

}
