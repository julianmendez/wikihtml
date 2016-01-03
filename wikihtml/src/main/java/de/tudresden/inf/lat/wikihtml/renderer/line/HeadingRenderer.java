/*
 *
 * Copyright 2013 Julian Mendez
 *
 */

package de.tudresden.inf.lat.wikihtml.renderer.line;

import java.util.ArrayList;
import java.util.List;

import de.tudresden.inf.lat.wikihtml.common.WikiCons;
import de.tudresden.inf.lat.wikihtml.renderer.common.ConversionToken;
import de.tudresden.inf.lat.wikihtml.renderer.common.RenderedToken;
import de.tudresden.inf.lat.wikihtml.renderer.common.Renderer;
import de.tudresden.inf.lat.wikihtml.renderer.common.TokenType;
import de.tudresden.inf.lat.wikihtml.renderer.common.WikiLinePartToken;

/**
 * 
 * @author Julian Mendez
 * 
 */
class HeadingRenderer implements Renderer {

	private final String htmlTextBegin;
	private final String htmlTextEnd;
	private final String wikiTextBegin;
	private final String wikiTextEnd;

	public HeadingRenderer(String wikiTextBeginEnd, String htmlTextBegin,
			String htmlTextEnd) {
		if (wikiTextBeginEnd == null) {
			throw new IllegalArgumentException("Null argument.");
		}
		if (htmlTextBegin == null) {
			throw new IllegalArgumentException("Null argument.");
		}
		if (htmlTextEnd == null) {
			throw new IllegalArgumentException("Null argument.");
		}

		this.wikiTextBegin = wikiTextBeginEnd;
		this.wikiTextEnd = wikiTextBeginEnd;
		this.htmlTextBegin = htmlTextBegin;
		this.htmlTextEnd = htmlTextEnd;
	}

	@Override
	public String getDescription() {
		return this.wikiTextBegin + " Heading " + this.wikiTextEnd;
	}

	public boolean isApplicable(ConversionToken token) {
		if (token == null) {
			throw new IllegalArgumentException("Null argument.");
		}

		return token.getType().equals(TokenType.WIKI_LINE)
				&& token.getWikiText().startsWith(this.wikiTextBegin)
				&& token.getWikiText().trim().endsWith(this.wikiTextEnd);
	}

	@Override
	public List<ConversionToken> render(ConversionToken token) {
		if (token == null) {
			throw new IllegalArgumentException("Null argument.");
		}

		List<ConversionToken> ret = new ArrayList<>();
		if (isApplicable(token)) {
			String text = token.getWikiText().trim();
			String str = text.substring(this.wikiTextBegin.length(),
					text.length() - this.wikiTextEnd.length());
			ret.add(new RenderedToken(WikiCons.NEW_LINE + this.wikiTextBegin,
					this.htmlTextBegin));
			ret.add(new WikiLinePartToken(str));
			ret.add(new RenderedToken(this.wikiTextEnd, this.htmlTextEnd));
		} else {
			ret.add(token);
		}
		return ret;
	}

}
