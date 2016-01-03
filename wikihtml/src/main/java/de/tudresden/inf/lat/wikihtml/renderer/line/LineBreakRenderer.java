/*
 *
 * Copyright 2013 Julian Mendez
 *
 */

package de.tudresden.inf.lat.wikihtml.renderer.line;

import java.util.ArrayList;
import java.util.List;

import de.tudresden.inf.lat.wikihtml.common.HTMLTag;
import de.tudresden.inf.lat.wikihtml.common.WikiCons;
import de.tudresden.inf.lat.wikihtml.renderer.common.ConversionToken;
import de.tudresden.inf.lat.wikihtml.renderer.common.RenderedToken;
import de.tudresden.inf.lat.wikihtml.renderer.common.Renderer;
import de.tudresden.inf.lat.wikihtml.renderer.common.TokenType;

/**
 * 
 * @author Julian Mendez
 * 
 */
public class LineBreakRenderer implements Renderer {

	private final String htmlLineBreak = HTMLTag.BR;

	public LineBreakRenderer() {
	}

	@Override
	public String getDescription() {
		return " (line break)";
	}

	public boolean isApplicable(ConversionToken token) {
		if (token == null) {
			throw new IllegalArgumentException("Null argument.");
		}

		return token.getType().equals(TokenType.WIKI_LINE)
				&& token.getWikiText().trim().isEmpty();
	}

	@Override
	public List<ConversionToken> render(ConversionToken token) {
		if (token == null) {
			throw new IllegalArgumentException("Null argument.");
		}

		List<ConversionToken> ret = new ArrayList<>();
		if (isApplicable(token)) {
			ret.add(new RenderedToken(WikiCons.NEW_LINE, this.htmlLineBreak));
		} else {
			ret.add(token);
		}

		return ret;
	}

}
