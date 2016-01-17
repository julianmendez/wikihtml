/*
 *
 * Copyright 2013 Julian Mendez
 *
 */

package de.tudresden.inf.lat.wikihtml.renderer.line;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import de.tudresden.inf.lat.wikihtml.common.HTMLTag;
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
class HorizontalLineRenderer implements Renderer {

	private final String htmlTextLine = HTMLTag.HR;
	private final String wikiTextPrefix;

	public HorizontalLineRenderer(String wikiTextPrefix) {
		Objects.requireNonNull(wikiTextPrefix);

		this.wikiTextPrefix = wikiTextPrefix;
	}

	@Override
	public String getDescription() {
		return this.wikiTextPrefix + " (horizontal line)";
	}

	public boolean isApplicable(ConversionToken token) {
		Objects.requireNonNull(token);

		return token.getType().equals(TokenType.WIKI_LINE) && token.getWikiText().startsWith(this.wikiTextPrefix);
	}

	@Override
	public List<ConversionToken> render(ConversionToken token) {
		Objects.requireNonNull(token);

		List<ConversionToken> ret = new ArrayList<>();
		if (isApplicable(token)) {
			String text = token.getWikiText();
			int start = 0;
			while ((start < text.length()) && text.substring(start).startsWith(this.wikiTextPrefix)) {
				start += this.wikiTextPrefix.length();
			}
			ret.add(new RenderedToken(WikiCons.NEW_LINE + this.wikiTextPrefix, this.htmlTextLine));
			if (start < text.length()) {
				String subText = text.substring(start);
				ret.add(new WikiLinePartToken(subText));
			}
		} else {
			ret.add(token);
		}

		return ret;
	}

}
