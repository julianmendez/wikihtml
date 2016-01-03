/*
 *
 * Copyright 2013 Julian Mendez
 *
 */

package de.tudresden.inf.lat.wikihtml.renderer.part;

import java.util.ArrayList;
import java.util.List;

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
class StyleRenderer implements Renderer {

	private final String htmlTextBegin;
	private final String htmlTextEnd;
	private final String wikiTextBegin;
	private final String wikiTextEnd;

	public StyleRenderer(String wikiTextBeginEnd, String htmlTextBegin,
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
		return this.wikiTextBegin + "style" + this.wikiTextEnd;
	}

	public boolean isApplicable(ConversionToken token) {
		return token.getType().equals(TokenType.WIKI_LINE_PART)
				&& (token.getWikiText().toLowerCase()
						.indexOf(this.wikiTextBegin) != -1);
	}

	@Override
	public List<ConversionToken> render(ConversionToken token) {
		if (token == null) {
			throw new IllegalArgumentException("Null argument.");
		}

		List<ConversionToken> ret = new ArrayList<>();
		if (isApplicable(token)) {
			String currentText = token.getWikiText();
			boolean found = true;
			while (found) {
				int beginPos = currentText.toLowerCase().indexOf(
						this.wikiTextBegin);
				if (beginPos == -1) {
					found = false;
				} else {
					{
						String previousText = currentText
								.substring(0, beginPos);
						ret.add(new WikiLinePartToken(previousText));
					}
					currentText = currentText.substring(beginPos);

					String subText = null;
					int endPos = currentText.toLowerCase().indexOf(
							this.wikiTextEnd, this.wikiTextBegin.length());
					if (endPos == -1) {
						found = false;
						subText = currentText.substring(this.wikiTextBegin
								.length());
						currentText = "";
					} else {
						subText = currentText.substring(
								this.wikiTextBegin.length(), endPos);
						currentText = currentText.substring(endPos
								+ this.wikiTextEnd.length());
					}

					ret.add(new RenderedToken(this.wikiTextBegin,
							this.htmlTextBegin));
					ret.add(new WikiLinePartToken(subText));
					ret.add(new RenderedToken(this.wikiTextEnd,
							this.htmlTextEnd));
				}
			}
			ret.add(new WikiLinePartToken(currentText));
		} else {
			ret.add(token);
		}
		return ret;
	}

}
