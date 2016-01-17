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
		Objects.requireNonNull(token);

		return token.getType().equals(TokenType.WIKI_LINE) && token.getWikiText().trim().isEmpty();
	}

	@Override
	public List<ConversionToken> render(ConversionToken token) {
		Objects.requireNonNull(token);

		List<ConversionToken> ret = new ArrayList<>();
		if (isApplicable(token)) {
			ret.add(new RenderedToken(WikiCons.NEW_LINE, this.htmlLineBreak));
		} else {
			ret.add(token);
		}

		return ret;
	}

}
