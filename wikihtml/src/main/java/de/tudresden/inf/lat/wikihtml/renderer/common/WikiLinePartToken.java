/*
 *
 * Copyright 2013 Julian Mendez
 *
 */

package de.tudresden.inf.lat.wikihtml.renderer.common;

import java.util.Objects;

/**
 * 
 * @author Julian Mendez
 * 
 */
public class WikiLinePartToken implements ConversionToken {

	private final String wikiText;

	public WikiLinePartToken(String wikiText) {
		Objects.requireNonNull(wikiText);

		this.wikiText = wikiText;
	}

	@Override
	public boolean equals(Object o) {
		boolean ret = (this == o);
		if (!ret && (o instanceof ConversionToken)) {
			ConversionToken other = (ConversionToken) o;
			ret = getType().equals(other.getType()) && getWikiText().equals(other.getWikiText())
					&& getHTMLText().equals(other.getHTMLText());
		}
		return ret;
	}

	@Override
	public String getHTMLText() {
		return "";
	}

	@Override
	public TokenType getType() {
		return TokenType.WIKI_LINE_PART;
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
		return getWikiText() + "[" + getType() + "]";
	}

}
