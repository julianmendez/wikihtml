/*
 *
 * Copyright 2013 Julian Mendez
 *
 */

package de.tudresden.inf.lat.wikihtml.renderer.part;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
class SimpleHyperlinkRenderer implements Renderer {

	private final String htmlTextBegin;
	private final String htmlTextEnd;
	private final String htmlTextMiddle;
	private final String wikiTextBegin;
	private final String wikiTextEnd;

	public SimpleHyperlinkRenderer(String wikiTextBegin, String wikiTextEnd, String htmlTextBegin,
			String htmlTextMiddle, String htmlTextEnd) {
		Objects.requireNonNull(wikiTextBegin);
		Objects.requireNonNull(wikiTextEnd);
		Objects.requireNonNull(htmlTextBegin);
		Objects.requireNonNull(htmlTextMiddle);
		Objects.requireNonNull(htmlTextEnd);

		this.wikiTextBegin = wikiTextBegin;
		this.wikiTextEnd = wikiTextEnd;
		this.htmlTextBegin = htmlTextBegin;
		this.htmlTextMiddle = htmlTextMiddle;
		this.htmlTextEnd = htmlTextEnd;
	}

	@Override
	public String getDescription() {
		return this.wikiTextBegin + "simplelinks" + this.wikiTextEnd;
	}

	public boolean isApplicable(ConversionToken token) {
		return token.getType().equals(TokenType.WIKI_LINE_PART)
				&& (token.getWikiText().toLowerCase().indexOf(this.wikiTextBegin) != -1);
	}

	@Override
	public List<ConversionToken> render(ConversionToken token) {
		Objects.requireNonNull(token);

		List<ConversionToken> ret = new ArrayList<>();
		if (isApplicable(token)) {
			String currentText = token.getWikiText();
			boolean found = true;
			while (found) {
				int beginPos = currentText.toLowerCase().indexOf(this.wikiTextBegin);
				if (beginPos == -1) {
					found = false;
				} else {
					{
						String previousText = currentText.substring(0, beginPos);
						ret.add(new WikiLinePartToken(previousText));
					}
					currentText = currentText.substring(beginPos);

					int linkEnd = currentText.toLowerCase().indexOf(this.wikiTextEnd);
					String linkText = null;

					if (linkEnd == -1) {
						linkText = currentText.substring(this.wikiTextBegin.length());
						currentText = "";
					} else {
						linkText = currentText.substring(this.wikiTextBegin.length(), linkEnd);
						currentText = currentText.substring(linkEnd);
					}

					ret.add(new RenderedToken(this.wikiTextBegin, this.htmlTextBegin));
					ret.add(new RenderedToken(linkText, linkText.toLowerCase()));
					ret.add(new RenderedToken("", this.htmlTextMiddle));
					ret.add(new RenderedToken("", linkText));
					ret.add(new RenderedToken("", this.htmlTextEnd));
				}
			}
			ret.add(new WikiLinePartToken(currentText));
		} else {
			ret.add(token);
		}
		return ret;
	}

}
